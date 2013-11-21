package com.interswitchng.techquest.autopay.sdk.lib.utils;

public class ConstantUtils {

	
	public static final String SIGNATURE_HEADER = "Signature";
	public static final String SIGNATURE_METHOD_HEADER = "SignatureMethod";
	public static final String TIMESTAMP_HEADER = "Timestamp";
	public static final String NONCE_HEADER = "Nonce";
	public static final String CONTENT_TYPE_HEADER = "Content-Type";
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String INTERSWITCH_AUTHORIZATION = "InterswitchAuth";
	public static final String DEFAULT_SIGNATURE_METHOD = "SHA1";
	public static final String HTTP_POST = "POST";
	public static final String HTTP_GET = "GET";
	public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
	
	  
//	public static final String RESOURCE_SERVER_PATH	= "https://stagenet.interswitchng.com/api";
	public static final String RESOURCE_SERVER_PATH	= "http://172.25.20.104:9080/api/v1/autopay";
	
	
}
