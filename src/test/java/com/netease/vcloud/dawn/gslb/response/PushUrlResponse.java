package com.netease.vcloud.dawn.gslb.response;

import java.lang.reflect.Array;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PushUrlResponse extends BasicResponse {

	private String requestId;
	private String[] roomservers;
	private String pushUrl;
	private String cdnType;
	private String token;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String[] getRoomservers() {
		return roomservers;
	}

	public void setRoomservers(String[] roomservers) {
		this.roomservers = roomservers;
	}

	public String getPushUrl() {
		return pushUrl;
	}

	public void setPushUrl(String pushUrl) {
		this.pushUrl = pushUrl;
	}

	public String getCdnType() {
		return cdnType;
	}

	public void setCdnType(String cdnType) {
		this.cdnType = cdnType;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
