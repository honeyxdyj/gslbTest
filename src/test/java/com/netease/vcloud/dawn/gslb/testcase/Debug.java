package com.netease.vcloud.dawn.gslb.testcase;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.netease.vcloud.dawn.gslb.api.CommonApiImpl;
import com.netease.vcloud.dawn.gslb.response.PullUrlParam;
import com.netease.vcloud.dawn.gslb.response.PullUrlResponse;
import com.netease.vcloud.dawn.gslb.response.PushTypeResponse;
import com.netease.vcloud.dawn.gslb.response.PushUrlResponse;
import com.netease.vcloud.dawn.gslb.utils.CommonString;
import com.netease.vcloud.dawn.gslb.utils.Response;

public class Debug {
	private static final Logger log = Logger.getLogger(Debug.class);

	CommonApiImpl commonApi = new CommonApiImpl();
	
	/*
	 * 	GSLB获取推流类型API
	 */
	@Test(groups = { "onlinetest" })
	public void getPushTypeOnline() throws Exception {

		log.info("===========getPushType========");

		//帝联 rtmp
		String pushUrlsDL="rtmp://pdl7524ed51.live.126.net/live/f39d0ce6c5b54052bb94f3358a564b63?wsSecret=e0de370c067a056f1d5269eb7c36e493&wsTime=1482286879";
		
		String[] pullUrlsDL = {
				"http://flvdl7524ed51.live.126.net/live/f39d0ce6c5b54052bb94f3358a564b63.flv?netease=flvdl7524ed51.live.126.net",
				"http://pullhlsdl7524ed51.live.126.net/live/f39d0ce6c5b54052bb94f3358a564b63/playlist.m3u8",
				"rtmp://vdl7524ed51.live.126.net/live/f39d0ce6c5b54052bb94f3358a564b63"
				};
		
		//网宿 webrtc
		String pushUrlsWS="rtmp://p1.live.126.net/live/802b5d3636f446648978e4cc225c4cf2?wsSecret=32e56d404d392c77f67e20e0570ad6e0&wsTime=1472215565";
		
		String[] pullUrlsWS = {
				"http://v1.live.126.net/live/802b5d3636f446648978e4cc225c4cf2.flv?netease=1.live.126.net",
				"http://pullhls1.live.126.net/live/802b5d3636f446648978e4cc225c4cf2/playlist.m3u8",
				"rtmp://v1.live.126.net/live/802b5d3636f446648978e4cc225c4cf2"
				};
		
		String[] urls = pullUrlsWS;
		for(int i=0; i<urls.length; i++){
			
			String pullUrl = urls[i];		
			
			PushTypeResponse pushTypeRes = commonApi.getPushType(pullUrl, null);
			
			log.info("getPullUrl: " + pushTypeRes.getHttpCode());			
			log.info("cdnType: " + pushTypeRes.getPushType());		

			Assert.assertEquals(200, CommonString.CODE_OK);
		}
		

	}
	
	/* UIP：
	 * 中国 浙江 杭州 netease.com 电信/联通/移动: 114.113.197.131
	 * 浙江 杭州 电信:125.118.248.16
	 * 浙江 杭州 移动:39.174.63.18
	 * 陕西 西安 教育网 58.195.128.14
	 * 上海 电信 114.80.166.240 
	 * 浙江 嘉兴 电信 60.163.62.16
	 * 江苏 南京 移动 221.130.48.0
	 */
	@Test(groups = { "onlineTest" })
	public void getPushUrlOnline() throws IOException {

		log.info("===========getPushUrlTencentVcloud========");

		int number = 0;
		try {
			
			//rtmp
			String pushUrl_rtmp = "";
			//webrtc
			String pushUrl_webrtc = "rtmp://p1.live.126.net/live/802b5d3636f446648978e4cc225c4cf2?wsSecret=32e56d404d392c77f67e20e0570ad6e0&wsTime=1472215565";
	
			//直推
			String pushUrl_ws="rtmp://p2e938040.live.126.net/live/d22b53f3308647b2bb3fd9c00f10eb04?wsSecret=b60558bdd38d6b8ff0e3df7d5032ccb1&wsTime=1482995003";
			String pushUrl_dl="rtmp://pdl75249abe.live.126.net/live/fb422e7c79584d09bdf6ca9a92555e33?wsSecret=d83a6d190ed73a0475beda62dfd3cb0f&wsTime=1482995052";
			
			PushUrlResponse pushUrlRes = commonApi.getPushUrl(pushUrl_webrtc, null, "58.195.128.14");

			Assert.assertEquals(pushUrlRes.getHttpCode(), CommonString.CODE_OK);

			log.info("=======================done=====================");
		
		} catch (AssertionError e) {
			log.error("AssertionError:", e);
			Assert.fail(e.toString());
		}

	}
	
	@Test(groups = { "yjtest" })
	public void getPushUrlWithMapper() throws IOException {

		log.info("===========getPushUrlTencentVcloud========");

		int number = 0;
		try {
			
			//rtmp
			String pushUrl_rtmp = "rtmp://p2e96200b.live.126.net/live/42c29b5c78524d3086f20fa31e522e71?wsSecret=c4e7e4c10bd691794ab6a85ced00163b&wsTime=1482132014";
			//帝联主CDN 开防盗链
			String pushUrl_rtmp_MCDN = "rtmp://pdl7524ed51.live.126.net/live/f39d0ce6c5b54052bb94f3358a564b63?wsSecret=d1f2bd6c3effccfa99f07a40eb70c2c1&wsTime=1482838456";
			
			//webrtc
			String pushUrl_webrtc = "rtmp://p1.live.126.net/live/802b5d3636f446648978e4cc225c4cf2?wsSecret=32e56d404d392c77f67e20e0570ad6e0&wsTime=1472215565";
			//网宿主CDN
			String pushUrl_webrtc_MCDN = "rtmp://pee4aa285.live.126.net/live/663c0b5e268846b49d67d0e4c6f23627?wsSecret=5af8b7d3a889c91615023d1d6aae7a93&wsTime=1482803730";
			
			//直推
			String pushUrl_ws="rtmp://p2e94d4fd.live.126.net/live/14b1ceb0c9c6461ca373529d1b907809?wsSecret=ca2e60c90e368592d01fbdf2c6157fea&wsTime=1482840312";
			String pushUrl_dl="rtmp://pdl7524ed51.live.126.net/live/639cff31fe314e44b3ed4c8f3b6b841f?wsSecret=0a99068b8c17fd0c721bdbf60ad1eaa5&wsTime=1482840476";
			
			PushUrlResponse pushUrlRes = commonApi.getPushUrl(pushUrl_rtmp_MCDN, null, null);

			Assert.assertEquals(pushUrlRes.getHttpCode(), CommonString.CODE_OK);


			log.info("=======================done=====================");
		
		} catch (AssertionError e) {
			log.error("AssertionError:", e);
			Assert.fail(e.toString());
		}

	}
	
//	@Test(groups = { "debug" }, invocationCount=1, threadPoolSize=1)
	public void getPushUrl() throws IOException, InterruptedException {

		log.info("===========getPushUrl========");

		int j = 0;
		try {

			for (int i = 0; i < 1; i++) {

				String pushUrl = CommonString.getConfig("pushUrl");
				PushUrlResponse pushUrlRes = commonApi.getPushUrl(pushUrl, null, null);
				// log.info("roomservers: " + pushUrlRes.getRoomservers());
				// log.info("token: " + pushUrlRes.getToken());

//				Assert.assertEquals(pushUrlRes.getHttpCode(), CommonString.CODE_OK);
				// String[] test = pushUrlRes.getToken().split(":");
				//
				// for(int i = 0 ; i < test.length; i ++){
				// log.info(test[i]);
				// }

				if (pushUrlRes.getToken() != null)
					j++;

				log.info("==========================" + j);
				
				TimeUnit.MILLISECONDS.sleep(50);
			}

		} catch (AssertionError e) {
			log.error("AssertionError:", e);
			Assert.fail(e.toString());
		}

	}

//	@Test(groups = { "debug" })//, invocationCount=200, threadPoolSize=20)
	public void createRoom() throws IOException {

		log.info("===========createRoom========");

		try {

			for (int i = 0; i < 1; i++) {

				String pushUrl = CommonString.getConfig("pushUrl");
				PushUrlResponse pushUrlRes = commonApi.getPushUrl(pushUrl, null, null);
				log.info("roomservers: " + pushUrlRes.getRoomservers());
				log.info("token: " + pushUrlRes.getToken());

				Assert.assertEquals(200, CommonString.CODE_OK);

				JsonObject reqParam = new JsonObject();
				long startTs = System.currentTimeMillis();

//				String roomName = "qatest-" + CommonString.getRandom() + CommonString.getRandomString(5);

				// String roomName = "qatest-92";

				reqParam.addProperty("mainCdnPushUrl", CommonString.getConfig("mainCdnPushUrl"));
				reqParam.addProperty("cdnType", CommonString.getConfig("cdnType"));
				reqParam.addProperty("roomServerId", CommonString.roomServerId_1);
				reqParam.addProperty("startTs", startTs);
				reqParam.addProperty("roomType", 0);

				String authorization = "live "
						+ CommonString.getConfig("accesskey")
						+ ":"
						+ CommonString.base64UrlSafeEncode(CommonString.hmacSha1(reqParam.toString(),
								CommonString.getConfig("secrectkey")));
				log.info("authorization: " + authorization);

				Response response = commonApi.createRoom(authorization, reqParam);
//				Assert.assertEquals(response.getStatus(), CommonString.CODE_OK);

				// destroyRoom

//				reqParam.remove("mainCdnPushUrl");
//				reqParam.addProperty("roomType", 1);
				reqParam.addProperty("endTs", System.currentTimeMillis());
				authorization = "live "
						+ CommonString.getConfig("accesskey")
						+ ":"
						+ CommonString.base64UrlSafeEncode(CommonString.hmacSha1(reqParam.toString(),
								CommonString.getConfig("secrectkey")));
				log.info("authorization: " + authorization);
				
//				response = commonApi.destroyRoom(authorization, reqParam);
				
				Assert.assertEquals(response.getStatus(), CommonString.CODE_OK);
//				reqParam.remove("endTs");
//				 response = commonApi.destroyRoom(authorization, reqParam);
//				 response = commonApi.destroyRoom(authorization, reqParam);
				// reqParam.remove("endTs");
				//
				// authorization = "live " + CommonString.getConfig("accesskey")
				// + ":" +
				// CommonString.base64UrlSafeEncode(CommonString.hmacSha1(reqParam.toString(),
				// CommonString.getConfig("secrectkey")));
				// log.info("authorization: " + authorization);
				// response = commonApi.createRoom(authorization, reqParam);
				// Assert.assertEquals(response.getStatus(),
				// CommonString.CODE_OK);

			}

		} catch (AssertionError e) {
			log.error("AssertionError:", e);
			Assert.fail(e.toString());
		}
	}

	
	@Test(groups = { "debug" })
	public void getPullUrl() throws IOException {

		log.info("============getPullUrl============");

//		String pullUrl = CommonString.getConfig("pullUrl");		
		String pullUrl = "http://v2e96200b.live.126.net/live/24cec6e4cf774d079544af590bcdae44.flv?netease=v2e96200b.live.126.net";
		
		PullUrlResponse pullUrlRes = commonApi.getPullUrl(pullUrl, null, null);
		log.info("getPullUrl: " + pullUrlRes.getPullUrls());
		log.info("getPullUrl: " + pullUrlRes.getHttpCode());
		
		Iterator<PullUrlParam> it = pullUrlRes.getPullUrls().iterator();
		
		while(it.hasNext()){
			
			PullUrlParam res = it.next();
			
			log.info("getUrl: " + res.getUrl());
			log.info("cdnType: " + res.getCdnType());
			log.info("resolved: " + res.isResolved());
		}

		Assert.assertEquals(200, CommonString.CODE_OK);

	}
	
	@Test(groups = { "debug" })
	public void getPullUrlCdnRandom() throws IOException {

		log.info("============getPullUrlCdnRandom============");

		int count = 0;
		int times = 1;
		
		//多CDN
		//String pullUrl = "http://v2e962b25.live.126.net/live/d5026af940a248408e49a2162b25b68c.flv?netease=v2e962b25.live.126.net";
		
		//单CDN
		String pullUrl = "http://v2e96200b.live.126.net/live/6eb04648cbc84c3492273ae095057f77.flv?netease=v2e96200b.live.126.net";
		
		for(int i=0; i<times; i++){
			PullUrlResponse pullUrlRes = commonApi.getPullUrl(pullUrl, null, null);
			log.info("getPullUrl: " + pullUrlRes.getPullUrls());
			log.info("getPullUrl: " + pullUrlRes.getHttpCode());
			
			Iterator<PullUrlParam> it = pullUrlRes.getPullUrls().iterator();
			
			PullUrlParam res = it.next();

			while(it.hasNext()){
				
				log.info("getUrl: " + res.getUrl());
				log.info("cdnType: " + res.getCdnType());
				log.info("resolved: " + res.isResolved());
				
				res = it.next();
			}
			
			if(res.getCdnType().equals("dnion")) 
				count++;

			Assert.assertEquals(200, CommonString.CODE_OK);
		}
		
		log.info("主CDN前置的概率为："+count+"/"+times);

	}
	
	@Test(groups = { "yjtest" })
	public void getPullUrlAllTypes() throws IOException {

		log.info("============getPullUrlCdnRandom============");
		
		//多CDN
//		String[] pullUrls = {"http://v2e962b25.live.126.net/live/d5026af940a248408e49a2162b25b68c.flv?netease=v2e962b25.live.126.net",
//				"http://pullhls2e962b25.live.126.net/live/d5026af940a248408e49a2162b25b68c/playlist.m3u8",
//				"rtmp://v2e962b25.live.126.net/live/d5026af940a248408e49a2162b25b68c"
//				};
		
		//单CDN
//		String[] pullUrls = {"http://v2e96200b.live.126.net/live/6eb04648cbc84c3492273ae095057f77.flv?netease=v2e96200b.live.126.net",
//				"http://pullhls2e96200b.live.126.net/live/6eb04648cbc84c3492273ae095057f77/playlist.m3u8",
//				"rtmp://v2e96200b.live.126.net/live/6eb04648cbc84c3492273ae095057f77"
//				};
		
		String[] pullUrls = {
				"http://vee4aa285.live.126.net/live/663c0b5e268846b49d67d0e4c6f23627.flv?netease=vee4aa285.live.126.net",
				"http://pullhlsee4aa285.live.126.net/live/663c0b5e268846b49d67d0e4c6f23627/playlist.m3u8",
				"rtmp://vee4aa285.live.126.net/live/663c0b5e268846b49d67d0e4c6f23627"
				};
		
		for(int i=0; i<pullUrls.length; i++){
			PullUrlResponse pullUrlRes = commonApi.getPullUrl(pullUrls[i], null, null);
			log.info("getPullUrl: " + pullUrlRes.getPullUrls());
			log.info("getPullUrl: " + pullUrlRes.getHttpCode());
			
			Iterator<PullUrlParam> it = pullUrlRes.getPullUrls().iterator();
			
			PullUrlParam res = it.next();

			while(it.hasNext()){
				
				log.info("getUrl: " + res.getUrl());
				log.info("cdnType: " + res.getCdnType());
				log.info("resolved: " + res.isResolved());
				
				res = it.next();
			}

			Assert.assertEquals(200, CommonString.CODE_OK);
		}
		
	}


	/*
	 * 	GSLB获取推流类型API
	 */
	@Test(groups = { "debug" }, invocationCount=1, threadPoolSize=1)
	public void getPushType() throws Exception {

		log.info("===========getPushType========");

		//帝联 rtmp
		String pushUrlsDL="rtmp://pdl7524ed51.live.126.net/live/f39d0ce6c5b54052bb94f3358a564b63?wsSecret=e0de370c067a056f1d5269eb7c36e493&wsTime=1482286879";
		
		String[] pullUrlsDL = {
				"http://flvdl7524ed51.live.126.net/live/f39d0ce6c5b54052bb94f3358a564b63.flv?netease=flvdl7524ed51.live.126.net",
				"http://pullhlsdl7524ed51.live.126.net/live/f39d0ce6c5b54052bb94f3358a564b63/playlist.m3u8",
				"rtmp://vdl7524ed51.live.126.net/live/f39d0ce6c5b54052bb94f3358a564b63"
				};
		
		//网宿 webrtc
		String pushUrlsWS="rtmp://pee4aa285.live.126.net/live/663c0b5e268846b49d67d0e4c6f23627?wsSecret=652d84db830efc71d44740eba1b8c1aa&wsTime=1482287101";
		
		String[] pullUrlsWS = {
				"http://vee4aa285.live.126.net/live/663c0b5e268846b49d67d0e4c6f23627.flv?netease=vee4aa285.live.126.net",
				"http://pullhlsee4aa285.live.126.net/live/663c0b5e268846b49d67d0e4c6f23627/playlist.m3u8",
				"rtmp://vee4aa285.live.126.net/live/663c0b5e268846b49d67d0e4c6f23627"
				};
		
		//String[] urls = pullUrlsWS;
		String[] urls = {"rtmp://v1.live.126.net/live/802b5d3636f446648978e4cc225c4cf2"};
		for(int i=0; i<urls.length; i++){
			
			String pullUrl = urls[i];		
			
			PushTypeResponse pushTypeRes = commonApi.getPushType(pullUrl, null);
			
			log.info("getPullUrl: " + pushTypeRes.getHttpCode());			
			log.info("cdnType: " + pushTypeRes.getPushType());		

			Assert.assertEquals(200, CommonString.CODE_OK);
		}
		

	}
}
