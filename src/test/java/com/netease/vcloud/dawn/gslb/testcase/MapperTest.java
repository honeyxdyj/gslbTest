package com.netease.vcloud.dawn.gslb.testcase;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.netease.vcloud.dawn.gslb.api.CommonApiImpl;
import com.netease.vcloud.dawn.gslb.response.PushUrlResponse;
import com.netease.vcloud.dawn.gslb.utils.CommonString;

public class MapperTest {
	private static final Logger log = Logger.getLogger(Debug.class);

	CommonApiImpl commonApi = new CommonApiImpl();

	/*
	 * 测试点：顺序匹配，第一条匹配后不再匹配后面的规则 mapper.cof规则配置如下 
	 * {"country": "中国", "province": * "浙江","city": "杭州", "isp": "电信", "region":["zxq"]}, 
	 * {"country": "中国", "province": "浙江","city": "*", "isp": "*", "region":["qa"]},
	 * 
	 * 为方便统计，zxq配置为webrtc方式，qa源站为不存在，即走rtmp方式
	 * 
	 * UIP：
	 * 中国 浙江 杭州 netease.com 电信/联通/移动: 114.113.197.131
	 * 浙江 杭州 电信:125.118.248.16
	 * 浙江 杭州 移动:39.174.63.18
	 * 陕西 西安 教育网 58.195.128.14
	 * 上海 电信 114.80.166.240 
	 * 浙江 嘉兴 电信 60.163.62.16
	 * 江苏 南京 移动 221.130.48.0
	 */
	@Test(groups = { "mapper" })
	public void getPushUrlWithMapper() throws IOException {

		log.info("===========getPushUrlWithMapper========");

		int number = 0;
		try {

			for (int i = 0; i < 1; i++) {

				//String pushUrl = CommonString.getConfig("pushUrl");
				//PushUrlResponse pushUrlRes = commonApi.getPushUrl(pushUrl, null, "219.137.222.1");
				
				//String pushUrl = "rtmp://p2e96200b.live.126.net/live/6eb04648cbc84c3492273ae095057f77?wsSecret=6802aa4d6763633cb5eb589954ace3d4&wsTime=1479808175";
				String pushUrl = "rtmp://p2e96200b.live.126.net/live/42c29b5c78524d3086f20fa31e522e71?wsSecret=c4e7e4c10bd691794ab6a85ced00163b&wsTime=1482132014";
				
				PushUrlResponse pushUrlRes = commonApi.getPushUrl(pushUrl, null, "60.163.62.16");

				Assert.assertEquals(pushUrlRes.getHttpCode(), CommonString.CODE_OK);

				if (pushUrlRes.getToken() != null)
					number++;

				log.info("==========================返回webrtc方式次数： " + number);
			}

		} catch (AssertionError e) {
			log.error("AssertionError:", e);
			Assert.fail(e.toString());
		}

	}

	/*
	 * 支持某个客户下面一定比例的频道采用webrtc的方式推流,配置如下wl.conf：
	 * {"domain":"1","channel":"*","percent":10}
	 */
	//@Test(groups = { "mapper" })
	public void getPushUrlWithMapperPercent() throws IOException {

		log.info("===========getPushUrlWithMapperPercent========");

		String[] url = {
				"rtmp://p1.live.126.net/live/fbeb7ee8606f47bf8cefd3089ea02f81?wsSecret=003087422b2b3eb43270fb910cbd2ab4&wsTime=1471864181",
				//"rtmp://p1.live.126.net/live/fb1884f3d4d8401e8bdc3fc985c8d6fc?wsSecret=38609e2b3a9c7982bcbff34b818175b1&wsTime=1471864199",
				//"rtmp://p2e962b25.live.126.net/live/35c0031c93f94801948086a1aa71b0ea?wsSecret=e4d34ec60b6139bae5f81e228a0581b9&wsTime=1471923235",
				//"rtmp://p1.live.126.net/live/0ae0ccc3b254418a9f3dfb2a6e699803?wsSecret=36c74b0fad36965de7e5db7a1683e13e&wsTime=1471864215",
				//"rtmp://p1.live.126.net/live/cb0b6cab2a4f43d1b854cc8422cf8416?wsSecret=9f7a616af575ec0de31e35a6fb4a6eee&wsTime=1471864227",
				//"rtmp://p1.live.126.net/live/ef140778efe74cebb20c5aecb96cb36c?wsSecret=450daa1caddc5756ba6e194414901fb3&wsTime=1471864249" 
				};

		int number = 0;
		log.info(url.length);
		try {

			for (int i = 0; i < url.length; i++) {

				
				PushUrlResponse pushUrlRes = commonApi.getPushUrl(url[i], null, null);

				Assert.assertEquals(pushUrlRes.getHttpCode(), CommonString.CODE_OK);

				if (pushUrlRes.getToken() != null)
					number++;

				log.info("==========================返回webrtc方式次数： " + number);
			}

		} catch (AssertionError e) {
			log.error("AssertionError:", e);
			Assert.fail(e.toString());
		}

	}

}
