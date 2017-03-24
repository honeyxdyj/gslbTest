package com.netease.vcloud.dawn.gslb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * 读取环境配置
 */
public class EnvConfig {
		
	public String getTestEnvironment() {
		File file = new File(this.getClass().getResource("/").getPath());
		String confPath = file + "/configEnv";
		String env = null;
		byte[] buf = new byte[1024]; 
		try {
			
			@SuppressWarnings("resource")
			InputStream is = new FileInputStream(confPath);
			is.read(buf);
			env = new String(buf).trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String result = file + "/conf/" + env + ".properties";
		return result;
	}
	
	
	public File getPath(){
		
		File file = new File(this.getClass().getResource("/").getPath());
		
		return file;
		
	}
	
	
}

