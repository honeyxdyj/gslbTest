package com.netease.vcloud.dawn.gslb.response;


public class PullUrlParam {

	
	private String url;
	private String cdnType;
	private boolean resolved;

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCdnType() {
		return cdnType;
	}

	public void setCdnType(String cdnType) {
		this.cdnType = cdnType;
	}

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

	
}
