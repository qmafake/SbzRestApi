package com.iso.rest;

import java.io.IOException;

//import java.util.logging.Logger;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isorest.domain.IsoRestRequest;
import com.isorest.domain.StewardBankApiResponse;
import com.isorest.domain.StewardResponseBody;

import com.isorest.helper.*; //TODO: blah blah

import postilion.realtime.sdk.message.bitmap.Iso8583.Bit;
import postilion.realtime.sdk.message.bitmap.Iso8583Post;
import postilion.realtime.sdk.util.XPostilion;

/**
 *
 * @author Artwell Mamvura
 */
@RestController
public class IsoRestController {

	//	private static final Logger log = Logger.getLogger(IsoRestController.class.getName());
	private static Logger logger = Logger.getLogger(IsoRestController.class);

	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "testing";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/v1/rest/iso/converter", method = RequestMethod.POST)
	public StewardBankApiResponse createIso(@RequestBody IsoRestRequest isoRestRequest) {
		//Build ISO respective Request 
		//This is a sample Response:
		//If field 39 of the ISO Response from Postilion is 00 then message = "SUCCESS"
		//If there is a timeout message = "FAILED" trigger a reversal
		//if field 39 value <> 00 then message ="FAILED"
		//Fill in Logic to convert request to ISO

		StewardBankApiResponse stewardBankApiResponse = new StewardBankApiResponse();

		logger.info( "JSON Request From Client: ");
		jsonPrettyPrinter(isoRestRequest) ;

		/** START# E2E transaction ----------------------------------------------- */
		Api2PostilionConfig.init();
		
		PostilionConnector pc = new PostilionConnector(); 	

		byte[] iso8583msg_req = pc.processTranReqFromClient(isoRestRequest);
		Iso8583Post iso8583msg_resp = pc.processTranReqToPostilion(iso8583msg_req);


		StewardResponseBody stewardResponseBody = new StewardResponseBody();
		stewardResponseBody.setPostilionReqMsgType("0200");
		
		try { //TODO: Error handle a no response case

			if (iso8583msg_resp.getResponseCode() == null)
			{
				stewardBankApiResponse.setMessage("ERROR OCCURED");
				
				stewardBankApiResponse.setStatusCode("500");
								
				stewardResponseBody.setPostilionRRN("N/A");
				stewardResponseBody.setPostilionRespCode("N/A");				
				stewardResponseBody.setPostilionRespMsgType("N/A");
				
				stewardBankApiResponse.setResponseBody(stewardResponseBody);
				
				return stewardBankApiResponse;
			}
			
			if ( iso8583msg_resp.getResponseCode().equals("00")){
				
				stewardBankApiResponse.setMessage("SUCCESS");
				stewardBankApiResponse.setStatusCode("200");
				
				stewardResponseBody.setPostilionRRN(iso8583msg_resp.getField(Bit._037_RETRIEVAL_REF_NR));
				stewardResponseBody.setPostilionRespCode(iso8583msg_resp.getResponseCode());
				stewardResponseBody.setPostilionRespMsgType(iso8583msg_resp.getMessageType());
				
				stewardBankApiResponse.setResponseBody(stewardResponseBody);	
				
				if (stewardResponseBody.getPostilionRespMsgType().equals("0430")){
					stewardBankApiResponse.setMessage("REVERSED"); 	//Change status if a successful reversal was sent					
				}
				
			}
			else{
				stewardBankApiResponse.setMessage("FAILED");
				stewardBankApiResponse.setStatusCode("500");
				
				stewardResponseBody.setPostilionRRN(iso8583msg_resp.getField(Bit._037_RETRIEVAL_REF_NR));
				stewardResponseBody.setPostilionRespCode(iso8583msg_resp.getResponseCode());
				stewardResponseBody.setPostilionRespMsgType(iso8583msg_resp.getMessageType());
				
				stewardBankApiResponse.setResponseBody(stewardResponseBody);	
			}
			
		} catch (XPostilion e) {
			logger.error("Problem getting Response code from Postilion response message " + e.getMessage());
			e.printStackTrace();
		}

		//Thread msgThread = new Thread( new PostilionConnector(testMsg) ); msgThread.start(); TODO: Use threads
		/**END# E2E transaction ----------------------------------------------- */

		return stewardBankApiResponse;
	}

	private void jsonPrettyPrinter( Object obj) {

		ObjectMapper mapper = new ObjectMapper();
		//		mapper.setSerializationInclusion(Inclusion.NON_DEFAULT);
		//		setSerializationInclusion(JsonInclude.Include.NON_NULL)

		try {
			logger.info("Pretty JSON: \n" +mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj) );
		} catch (JsonGenerationException e1) {

			e1.printStackTrace();
		} catch (JsonMappingException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
	}
}
