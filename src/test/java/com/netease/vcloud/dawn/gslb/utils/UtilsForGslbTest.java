package com.netease.vcloud.dawn.gslb.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;

import com.google.gson.JsonObject;

public class UtilsForGslbTest {
	
	public Response commonAPI(String action, String uip, JsonObject body, String authorization) throws IOException{
		
		HttpHelper httpHelper = new HttpHelper(CommonString.getConfig("glsb_host"));
		HttpResponse httpResponse = httpHelper.post(action, uip, body, authorization);
			
		int status = httpResponse.getStatusLine().getStatusCode();
		String responseStr = HttpHelper.processResponseCode(httpResponse);
		Response response = new Response(status, responseStr);
		return response;
		
	}

}
