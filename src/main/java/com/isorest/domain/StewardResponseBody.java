package com.isorest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StewardResponseBody {
	
	@JsonProperty (value="retrieval_ref_nr")
	private String rrn;
	
	private String postilion_resp_code;
	
	
	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	
	public String getPostilion_resp_code() {
		return postilion_resp_code;
	}

	public void setPostilion_resp_code(String postilion_resp_code) {
		this.postilion_resp_code = postilion_resp_code;
	}
	
	
}
