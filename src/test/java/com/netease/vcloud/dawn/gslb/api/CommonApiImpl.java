package com.netease.vcloud.dawn.gslb.api;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.netease.vcloud.dawn.gslb.response.PullUrlResponse;
import com.netease.vcloud.dawn.gslb.response.PushTypeResponse;
import com.netease.vcloud.dawn.gslb.response.PushUrlResponse;
import com.netease.vcloud.dawn.gslb.utils.RequestAction;
import com.netease.vcloud.dawn.gslb.utils.Response;
import com.netease.vcloud.dawn.gslb.utils.UtilsForGslbTest;

public class CommonApiImpl implements ICommonApi {

	UtilsForGslbTest clientUtils = new UtilsForGslbTest();
	
	//获取推流地址
	public PushUrlResponse getPushUrl(String pushUrl, JsonObject sdkParas, String uip) throws IOException{
		// TODO Auto-generated method stub

		JsonObject reqParam = new JsonObject();
		if (pushUrl != null)
			reqParam.addProperty("pushUrl", pushUrl);

		if (sdkParas != null)
			reqParam.addProperty("sdkParas", sdkParas.toString());

		Response response = clientUtils.commonAPI(RequestAction.GET_PUSH_URL, uip, reqParam, null);
		
		PushUrlResponse pushUrlRes = new Gson().fromJson(response.getResponseStr(), PushUrlResponse.class);
		pushUrlRes.setHttpCode(response.getStatus());
		
		return pushUrlRes;
	}

	//获取拉流地址
	public PullUrlResponse getPullUrl(String pullUrl, JsonObject sdkParas, String uip) throws IOException {
		JsonObject reqParam = new JsonObject();
		if (pullUrl != null)
			reqParam.addProperty("pullUrl", pullUrl);

		if (sdkParas != null)
			reqParam.addProperty("sdkParas", sdkParas.toString());

		Response response = clientUtils.commonAPI(RequestAction.GET_PULL_URL, uip, reqParam, null);
		PullUrlResponse pullUrlRes = new PullUrlResponse();
		pullUrlRes.setHttpCode(response.getStatus());

		PullUrlResponse pushUrlRes = new Gson().fromJson(response.getResponseStr(), PullUrlResponse.class);
		pushUrlRes.setHttpCode(response.getStatus());
	
		return pushUrlRes;
	}
	
	//获取推流类型（for统计平台）
	public PushTypeResponse getPushType(String pullUrl, String uip) throws IOException {
		JsonObject reqParam = new JsonObject();
		if (pullUrl != null)
			reqParam.addProperty("pullUrl", pullUrl);

		Response response = clientUtils.commonAPI(RequestAction.GET_PUSH_TYPE, uip, reqParam, null);
		PushTypeResponse pullUrlRes = new PushTypeResponse();
		pullUrlRes.setHttpCode(response.getStatus());

		PushTypeResponse pushUrlRes = new Gson().fromJson(response.getResponseStr(), PushTypeResponse.class);
		pushUrlRes.setHttpCode(response.getStatus());
	
		return pushUrlRes;
	}

	@Override
	public Response createRoom(String authorization, JsonObject reqParam) throws IOException {

		Response response = clientUtils.commonAPI(RequestAction.CREATE_ROOM, null, reqParam, authorization);

		return response;
	}

	@Override
	public Response destroyRoom(String authorization, JsonObject reqParam) throws IOException {

		Response response = clientUtils.commonAPI(RequestAction.DESTROY_ROOM, null, reqParam, authorization);

		return response;
	}

}
