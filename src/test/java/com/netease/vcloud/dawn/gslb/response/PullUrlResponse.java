package com.netease.vcloud.dawn.gslb.response;

import java.util.List;

public class PullUrlResponse extends BasicResponse {

	private String requestId;	

	private List<PullUrlParam> pullUrls;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public List<PullUrlParam> getPullUrls() {
		return pullUrls;
	}

	public void setPullUrls(List<PullUrlParam> pullUrls) {
		this.pullUrls = pullUrls;
	}

}
