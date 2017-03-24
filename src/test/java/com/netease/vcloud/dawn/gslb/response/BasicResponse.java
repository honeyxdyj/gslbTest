package com.netease.vcloud.dawn.gslb.response;

/*
 * 接口响应参数中的通用参数，根据项目适当调整
 */
public class BasicResponse {

	private int httpCode;
	private int code;
	private String msg;

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
