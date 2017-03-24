package com.netease.vcloud.dawn.gslb.response;


public class PushTypeResponse extends BasicResponse {

	private String requestId;	
	
	private String pushType;	

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	public String getPushType() {
		return pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}



}
