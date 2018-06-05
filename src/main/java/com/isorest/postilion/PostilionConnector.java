package com.isorest.postilion;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;

import com.isorest.domain.IsoRestRequest;

import postilion.realtime.sdk.message.bitmap.Iso8583Post;
import postilion.realtime.sdk.util.XPostilion;

public class PostilionConnector implements Runnable {
	
	private static Logger loggr = Logger.getLogger(MessageConvetor.class);
	

	@Override
	public void run() {


	}

	public byte[] processTranReqFromClient(IsoRestRequest isoRestRequest) {

		MessageConvetor<Object> mc = new MessageConvetor<Object>();
		byte[] _msg_to_transmit = null;

		try {
			_msg_to_transmit = mc.constructAIRmsgToPostilion(isoRestRequest);
		} catch (XPostilion e) {
			
			e.printStackTrace();
		}

		Iso8583Post msg_rsp = new Iso8583Post();

		return _msg_to_transmit;
	}
	
	
	
	/**
	 * This method - receives a byte array msg object to transmit to Postilion.
	 * @param _msg_to_transmit
	 * @throws XPostilion
	 * @returns An Iso858Post msg response
	 */
	public Iso8583Post processTranReqToPostilion(byte[] _msg_to_transmit) {

		SocketConn sc = new SocketConn();
		Socket socket = sc.getConnection();			
		OutputStream out2 = null;
		InputStream is = null;
		Iso8583Post msg_rsp = new Iso8583Post();
		long startTime = System.currentTimeMillis();
		long rspTime = 0L;
		try
		{
			/**---------------------------------------------------------------------- Client Request */																													//showMsgDetail(_msg_to_transmit); System.out.print("\n");			 
			out2 = socket.getOutputStream();			
			out2.write(_msg_to_transmit);		//write msg to Postilion Postbridge port.	

			/**---------------------------------------------------------------------- Server Response */		
			is = socket.getInputStream();		

			byte[] resultBuff = new byte[0];
			byte[] buff = new byte[Api2PostilionConfig.expectedRespLen];
			int k = -1; 

			socket.setSoTimeout(1000 * Api2PostilionConfig.readTimeOut); 	 	/* 1000*5 => 5 seconds read timeout. If read operation has
																		       blocked for >5 seconds a SocketTimeOutException occurs */	

			while((k = is.read(buff, 0, buff.length)) > -1) {				//Read server response						
				byte[] tbuff = new byte[resultBuff.length + k]; 			//temp buffer size = bytes already read + bytes last read
				System.arraycopy(resultBuff, 0, tbuff, 0, resultBuff.length); //copy previous bytes
				System.arraycopy(buff, 0, tbuff, resultBuff.length, k);  	//copy current lot
				resultBuff = tbuff; 										//call the temp buffer as your result buff

				if (is.available() == 0) break;
			}

			rspTime = System.currentTimeMillis() - startTime;

			if (resultBuff.length > 2)								//just doubly make sure that there is a result
			{				
				byte[] tbuff = new byte[resultBuff.length-2]; 		/*I will save the response without the 1st 2 bytes 
																	  which are the msg header (body length) */
				System.arraycopy(resultBuff, 2, tbuff, 0, resultBuff.length-2);
				//	showMsgDetail(tbuff);										
				msg_rsp.fromMsg(tbuff, 0);
				System.out.println(msg_rsp.toString());		

				loggr.info("Postilion Response\n" + msg_rsp.toString());
			}			
			out2.flush();  out2.close();  is.close();
		} 
		catch (SocketTimeoutException e1) {

			loggr.error( e1.getMessage() );

//			new MessageValidator().throw_ER0008_ReadTimeOutError();					
		}
		catch (Exception e) {
			loggr.error(this.getClass().getSimpleName()
					+ " Error occurred in msg transmission to/from Postilion:\n"
					+ e.getMessage()
					+e.getCause() 
					+e.getStackTrace());
			e.printStackTrace();
		}		
		finally{
			sc.closeSocket(socket);
			loggr.info("Transmission ended and connection closed | RESPONSE TIME(ms):"   + (rspTime!=0L ? rspTime : "N/A") );
		}

		return msg_rsp;
	}
	
	
}