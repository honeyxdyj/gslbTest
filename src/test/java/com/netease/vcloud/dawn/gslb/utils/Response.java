package com.netease.vcloud.dawn.gslb.utils;

public class Response {
	
	int status;
	String responseStr;
	
	public Response(int responseStatus, String responseStr){
		this.status = responseStatus;
		this.responseStr = responseStr;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getResponseStr() {
		return responseStr;
	}

	public void setResponseStr(String responseStr) {
		this.responseStr = responseStr;
	}

}
