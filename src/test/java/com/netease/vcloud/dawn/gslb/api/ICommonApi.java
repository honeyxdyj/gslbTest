package com.netease.vcloud.dawn.gslb.api;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.netease.vcloud.dawn.gslb.response.PullUrlResponse;
import com.netease.vcloud.dawn.gslb.response.PushUrlResponse;
import com.netease.vcloud.dawn.gslb.utils.Response;

public interface ICommonApi {	
	
	PushUrlResponse getPushUrl(String pushUrl, JsonObject sdkParas, String uip) throws IOException;
	PullUrlResponse getPullUrl(String pullUrl, JsonObject sdkParas, String uip) throws IOException;
	
	Response createRoom(String authorization, JsonObject reqParam) throws IOException;
	Response destroyRoom(String authorization, JsonObject reqParam) throws IOException;
	
	
}
