package com.netease.vcloud.dawn.gslb.smoke;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.netease.vcloud.dawn.gslb.api.CommonApiImpl;

public class SmokeTest {
	private static final Logger log = Logger.getLogger(SmokeTest.class);
	
	CommonApiImpl api  = new CommonApiImpl();
	
	@Test(groups = { "smoke" })
	public void test() throws IOException {

		log.info("========== test ============");

	}

}
