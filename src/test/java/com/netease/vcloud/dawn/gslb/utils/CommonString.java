package com.netease.vcloud.dawn.gslb.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.log4j.Logger;


public class CommonString {
	
	private static final Logger log = Logger.getLogger(CommonString.class);	
	
	public static final int CODE_OK = 200;
	public static final int CODE_BAD_REQUEST = 400;
	public static final int CODE_UNAUTORIZED = 401;
	public static final int CODE_FORBIDDEN = 403;
	public static final int CODE_NOT_FOUND = 404;
	public static final int CODE_CONFLICT = 409;
	public static final int CODE_INTERNAL_SERVER_ERROR = 500;
	public static final int CODE_GATEWAY_TIMEOUT = 504;	
	public static final String MSG_OK = "OK";
	
	public static final int VOD_CODE_PARAM_ILLEGAL = 711;
	public static final int VOD_CODE_FREQUENCY_LIMIT = 723;
	
	public static final int HTTP_POST = 1;
	public static final int HTTP_GET = 2;
	public static final int HTTP_PUT = 3;
	public static final int HTTP_DELETE = 4;	
	
	public static final int paramNotPass = -1221;// 此值1221主要用于为验证不传时的参数异常校验
	public static final int paramIllegalType = -2016; //此值为参数校验是类型错误，传入String型等
	
	public static final String paramNotPassStr = "不传该字段";// 此值主要用于为验证不传时的参数异常校验
	public static final String paramIllegalTypeStr = "字段类型非法";//此值为参数校验是类型错误，传入int型等	
	
	//roomserver
	public static final int roomServerId_1 = 1;
	public static final int roomServerId_2 = 3;

	
	public static String getConfig(String descKey){
		
		String str = new EnvConfig().getTestEnvironment(); 
		
		Properties prop =  new  Properties();
		String pattern = null;
		try  {    
			InputStream is = new FileInputStream(str);
            prop.load(is);    
            pattern = prop.getProperty(descKey).trim();    
        }  catch  (IOException e) {    
            e.printStackTrace();    
        }    
		
		return pattern;
	}
	

	public static String getRandomString(int length) { 
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }
		
	public static int getRandom() {    
	    int rand = new Random().nextInt(100) + 1;      
	    return rand;     
	 } 
	
	
    @SuppressWarnings("static-access")
	public static String base64UrlSafeEncode(byte[] bb) {
        Base64 encoder = new Base64(true);
        return encoder.encodeBase64URLSafeString(bb);
    }

    @SuppressWarnings("static-access")
	public static String base64UrlSafeEncode(String s) {
        Base64 encoder = new Base64(true);
        return encoder.encodeBase64URLSafeString(s.getBytes());
    }

    public static String base64UrlSafeDecode(String text) {
        Base64 decoder = new Base64(true);
        byte[] decodeByte = decoder.decode(text);
        return new String(decodeByte);
    }

    public static byte[] hmacSha1(String body, String accessKey) {
        // Simplifies common Mac tasks. This class is immutable and thread-safe.
        return HmacUtils.hmacSha1(CommonString.getConfig("secrectkey"), body);
    }
    
  	
	public static void main(String[] args){
		
		log.info(getConfig("DEFAULT_HOST"));

	    
	}
	

}