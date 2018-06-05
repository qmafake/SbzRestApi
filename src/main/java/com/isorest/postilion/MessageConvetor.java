package com.isorest.postilion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.mapping.Subclass;
import org.json.JSONObject;

import com.isorest.domain.Field;
import com.isorest.domain.IsoRestRequest;
import com.isorest.domain.StewardBankApiResponse;
import com.isorest.domain.SubIso;

import postilion.realtime.sdk.message.bitmap.Iso8583;
import postilion.realtime.sdk.message.bitmap.Iso8583Post.Bit;
import postilion.realtime.sdk.message.bitmap.Iso8583Post.PrivBit;
import postilion.realtime.sdk.message.bitmap.Iso8583.MsgType;
import postilion.realtime.sdk.message.bitmap.Iso8583Post;
import postilion.realtime.sdk.util.DateTime;
import postilion.realtime.sdk.util.XPostilion;
import postilion.realtime.sdk.util.convert.Pack;

public class MessageConvetor<T> {

	private static Logger logger = Logger.getLogger(MessageConvetor.class);

	public MessageConvetor() {		
	}

	public Iso8583 constructNetworkSignOn() throws XPostilion {

		Iso8583 msg = new Iso8583();
		DateTime date = new DateTime();

		Date dt = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("MMdd");

		msg.putMsgType(MsgType._0800_NWRK_MNG_REQ);
		msg.putField(7, date.get("MMddhhmmss"));
		msg.putField(11, "888888");
		msg.putField(12, date.get("HHmmss"));
		// msg.putField(13, date.get("MMd"));
		msg.putField(13, fmt.format(dt));
		msg.putField(70, "001");
		msg.putField(100, "91911911777");
		return msg;
	}

	public byte[] new2byteHeaderPacket(byte data[]) {
		int len = data.length;
		byte buf[] = new byte[len + 2];
		// byte buf[] = new byte[len ]; //byte[] comes with two extra spaces already
		buf[0] = (byte) (len >> 8 & 0xff);
		buf[1] = (byte) (len & 0xff);
		System.arraycopy(data, 0, buf, 2, len);
		return buf;
	}

	public byte[] constructAIRmsgToPostilion(IsoRestRequest req) throws XPostilion {

		Iso8583Post msg = new Iso8583Post();

		List<Field> fieldsList = req.getField();
		SubIso  privFields = req.getIsomsg();
		List<Field> privFieldsList = privFields.getField();

		for (Field field : fieldsList){

			logger.info("field: " + field.getId() + " - " + field.getValue());
		}
		
		for (Field field : privFieldsList){

			logger.info("field: " + field.getId() + " - " + field.getValue());
		}

		String switchKey = "SwitchKey-".concat( fieldsList.get(2).getValue() );
		String cardAccptor = "1 Howe Street E004     Cape Town    WCZA";

		putDefaultFields(msg);
		msg.putField( Bit._003_PROCESSING_CODE, "010000" );
		msg.putField(Bit._004_AMOUNT_TRANSACTION, Pack.resize("20", 12, '0', false));

		msg.putField(Bit._043_CARD_ACCEPTOR_NAME_LOC, Pack.resize(switchKey, 40, ' ', true));

		msg.putField(Bit._102_ACCOUNT_ID_1, "11111111111111" ); 

		//		System.out.println(this.getClass().getSimpleName() + ": Request ISO message\n" + msg.toString());
		logger.info("Request ISO message\n" + msg.toString());		

		return new2byteHeaderPacket(msg.toMsg());
	}

	
	private void putDefaultFields(Iso8583Post msg) throws XPostilion {

		DateTime date = new DateTime();

		msg.setMessageType("0200");
//		msg.putMsgType(MsgType._0400_ACQUIRER_REV_REQ);
//		msg.setMessageType(postilion.realtime.sdk.message.bitmap.Iso8583.MsgType._0200_TRAN_REQ);
		//		msg.putField(2, "9999888888888882");		
		msg.putField(Bit._007_TRANSMISSION_DATE_TIME, date.get("MMddhhmmss"));
		msg.putField(Bit._011_SYSTEMS_TRACE_AUDIT_NR, "000001");
		msg.putField(Bit._012_TIME_LOCAL, date.get("HHmmss") );

		Date dt = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("MMdd");		
		msg.putField(13, fmt.format(dt));

		msg.putField(Bit._022_POS_ENTRY_MODE, "000");
		msg.putField(Bit._025_POS_CONDITION_CODE, "00");		
		msg.putField(Bit._041_CARD_ACCEPTOR_TERM_ID, Pack.resize("11111111", 8, ' ', true) );
		msg.putField(Bit._042_CARD_ACCEPTOR_ID_CODE, Pack.resize("111111111111111", 15, ' ', true) );
		msg.putField(Bit._044_ADDITIONAL_RSP_DATA, "Tomcat 8.0 Transaction");

		msg.putField(Bit._049_CURRENCY_CODE_TRAN, "840");
		msg.putField(Bit._100_RECEIVING_INST_ID_CODE, "246812" );   
		msg.putField(Bit._123_POS_DATA_CODE, "210101604144101" );
					msg.putPrivField(PrivBit._002_SWITCH_KEY, date.get("MMddhhmmss"));
	}	
	
	private void putDefaultFields_(Iso8583Post msg) throws XPostilion {

		DateTime date = new DateTime();

		msg.setMessageType("0200");
//		msg.putMsgType(MsgType._0400_ACQUIRER_REV_REQ);
//		msg.setMessageType(postilion.realtime.sdk.message.bitmap.Iso8583.MsgType._0200_TRAN_REQ);
		//		msg.putField(2, "9999888888888882");		
		msg.putField(Bit._007_TRANSMISSION_DATE_TIME, date.get("MMddhhmmss"));
		msg.putField(Bit._011_SYSTEMS_TRACE_AUDIT_NR, "000001");
		msg.putField(Bit._012_TIME_LOCAL, date.get("HHmmss") );

		Date dt = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("MMdd");		
		msg.putField(13, fmt.format(dt));

		msg.putField(Bit._022_POS_ENTRY_MODE, "000");
		msg.putField(Bit._025_POS_CONDITION_CODE, "00");		
		msg.putField(Bit._041_CARD_ACCEPTOR_TERM_ID, Pack.resize("Tomcat", 8, ' ', true) );
		msg.putField(Bit._042_CARD_ACCEPTOR_ID_CODE, Pack.resize("Tomcat_8.0", 15, ' ', true) );

		msg.putField(Bit._049_CURRENCY_CODE_TRAN, "840");
		msg.putField(Bit._100_RECEIVING_INST_ID_CODE, "246812" );   
		msg.putField(Bit._123_POS_DATA_CODE, "210101604144101" );
					msg.putPrivField(PrivBit._002_SWITCH_KEY, date.get("MMddhhmmss"));
	}	

	@SuppressWarnings("rawtypes")
	public StewardBankApiResponse createStewardBankApiResponse(Iso8583Post msg_rsp) throws XPostilion {

		logger.info("Creating Steward Bank Api Response ...");
		
		StewardBankApiResponse stewardBankApiResponse = new StewardBankApiResponse();
		//			String alphaCurrCode = SupportedCurrencies.currencyMap.get( msg_rsp.getField(Bit._049_CURRENCY_CODE_TRAN) );

		String alphaCurrCode = msg_rsp.getField(Bit._044_ADDITIONAL_RSP_DATA).split("#")[0] ;

		
		JSONObject jsonResp = new JSONObject();
		jsonResp.put("one", "two");
		jsonResp.put("three", "four");
		
		stewardBankApiResponse.setStatusCode("00");
		stewardBankApiResponse.setMessage("Tran All Good");
		stewardBankApiResponse.setResponseBody( (T) jsonResp);

		return stewardBankApiResponse;
	}

	//		private SignedAmount processCalcCharge(String charge, int msgtype) throws XFieldUnableToConstruct {
	//
	//			charge = charge.replaceAll("[^\\d.]", ""); 		//replace all non digit and non comma (USD 2.32) becomes (2.32)
	//			charge =String.valueOf(Math.round(Float.parseFloat(charge)*100));
	//			charge =Pack.resize(charge, 8, '0', false);
	//			SignedAmount samt;
	//			if (isReversalMsgType(msgtype))
	//				samt = new SignedAmount("C",charge);
	//			else
	//				samt = new SignedAmount("D",charge);
	//
	//			return samt;
	//		}

	private boolean isReversalMsgType(int msgType) {

		if (msgType==Iso8583Post.MsgType._0420_ACQUIRER_REV_ADV
				|| msgType==Iso8583Post.MsgType._0400_ACQUIRER_REV_REQ
				|| msgType==Iso8583Post.MsgType._0421_ACQUIRER_REV_ADV_REP
				|| msgType==Iso8583Post.MsgType._0401_ACQUIRER_REV_REQ_REP)
			return true;
		return false;
	}

	static byte data[] = { 0x30, 0x32, 0x30, 0x30, (byte) 0xF2, 0x36, 0x04,
			0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x00, 0x00, 0x31, 0x39, 0x36, 0x30, 0x31, 0x32, 0x33, 0x37, 0x36,
			0x31, 0x31, 0x30, 0x30, 0x30, 0x30, 0x31, 0x35, 0x38, 0x31, 0x34,
			0x38, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30,
			0x33, 0x38, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x34, 0x32,
			0x37, 0x31, 0x33, 0x30, 0x38, 0x35, 0x39, 0x30, 0x30, 0x32, 0x32,
			0x34, 0x37, 0x30, 0x35, 0x30, 0x34, 0x32, 0x37, 0x31, 0x35, 0x31,
			0x30, 0x35, 0x30, 0x31, 0x34, 0x31, 0x31, 0x30 };

}
