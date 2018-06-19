package com.isorest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StewardResponseBody {
	
	private String postilionReqMsgType;	
	
	private String postilionRespMsgType;	
		
	private String postilionRRN;
	
//	@JsonProperty (value="retrieval_ref_nr")
	private String postilionRespCode;
	
	
	public String getPostilionRRN() {
		return postilionRRN;
	}

	public void setPostilionRRN(String rrn) {
		this.postilionRRN = rrn;
	}
	
	public String getPostilionRespCode() {
		return postilionRespCode;
	}

	public void setPostilionRespCode(String postilion_resp_code) {
		this.postilionRespCode = postilion_resp_code;
	}
	
	public String getPostilionReqMsgType() {
		return postilionReqMsgType;
	}

	public void setPostilionReqMsgType(String postilionReqMsgType) {
		this.postilionReqMsgType = postilionReqMsgType;
	}

	public String getPostilionRespMsgType() {
		return postilionRespMsgType;
	}

	public void setPostilionRespMsgType(String msgType) {
		this.postilionRespMsgType = msgType;
	}
	
	
	
}
