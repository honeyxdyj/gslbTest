package com.netease.vcloud.dawn.gslb.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Format;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpHelper {

	private static final Logger logger = Logger.getLogger(HttpHelper.class);

	private HttpClient httpClient;

	private String host;

	private String accept = "application/json;charset=utf-8";

	public HttpHelper(String rpc) {
		this.host = rpc;
		httpClient = new DefaultHttpClient();
	}

	public HttpHelper(String rpc, String accept) {
		this.host = rpc;
		this.accept = accept;
		httpClient = new DefaultHttpClient();
	}

	
	public HttpResponse post(String action, String uip, JsonObject body, String authorization) throws IOException {

		HttpPost httpPost = new HttpPost(getCompleteURL(action));

		httpPost.addHeader("Content-Type", accept);
		
		if(uip != null)
			httpPost.addHeader("UIP", uip);
		
		if(authorization != null)
			httpPost.addHeader("Authorization", authorization);

		logger.info("body:" + body);
		if (body != null)
			httpPost.setEntity(new StringEntity(body.toString(), "UTF-8"));

		HttpResponse httpResponse = httpClient.execute(httpPost);

		return httpResponse;
	}
	
	
	public HttpResponse get(String action) throws IOException {		

		HttpGet httpGet = new HttpGet(getCompleteURL(action));
				
		httpGet.addHeader("Content-Type", accept);

		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		return httpResponse;
	}	

	private String getCompleteURL(String action) {

		StringBuilder sb = new StringBuilder("http://" + host + "/");
		sb.append(action);
		logger.info("URL: " + sb.toString());
		return sb.toString();

	}

	/*
	 * return response string
	 */
	public static String processResponseCode(HttpResponse httpResponse) {
		String responseStr = null;
		try {
			int statusCode = httpResponse.getStatusLine().getStatusCode();

			HttpEntity entity = httpResponse.getEntity();
			ByteArrayOutputStream arrayStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			InputStream is = entity.getContent();
			int len;
			while ((len = is.read(buffer)) > 0) {
				arrayStream.write(buffer, 0, len);
			}
			responseStr = new String(arrayStream.toByteArray(), "UTF-8");
			logger.info(statusCode);
			logger.info(httpResponse.getStatusLine());
			logger.info(responseStr);
			logger.info(jsonFormatter(responseStr));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseStr;
	}

	/*
	 * json格式化
	 */
	public static String jsonFormatter(String uglyJSONString) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(uglyJSONString);
		String prettyJsonString = gson.toJson(je);

		return prettyJsonString;
	}

}