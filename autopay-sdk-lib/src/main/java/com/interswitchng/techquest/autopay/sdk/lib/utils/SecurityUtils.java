package com.interswitchng.techquest.autopay.sdk.lib.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

public abstract class SecurityUtils {

	
	 
	 public HashMap<String, List<String>> getSecurityHeaders(String clientId, String clientSecretKey, String httpMethod, String url, String transactionParameters)
	 {
		 HashMap<String, List<String>> headers = new HashMap<String, List<String>>();
		 
		 List<String> contentTypeHeader = new ArrayList<String>();
		 String contentType = ConstantUtils.CONTENT_TYPE_APPLICATION_JSON;
		 contentTypeHeader.add(contentType);
		 headers.put(ConstantUtils.CONTENT_TYPE_HEADER, contentTypeHeader);
		 
		 List<String> authorizationHeader = new ArrayList<String>();
		 String authorization = ConstantUtils.INTERSWITCH_AUTHORIZATION + " " + new String(Base64.encode(clientId.getBytes())).trim();
		 authorizationHeader.add(authorization);
		 headers.put(ConstantUtils.AUTHORIZATION_HEADER, authorizationHeader);
		 
		 List<String> timestampHeader = new ArrayList<String>();
		 String timestamp = String.valueOf(System.currentTimeMillis() / 1000L);
		 timestampHeader.add(timestamp);
		 headers.put(ConstantUtils.TIMESTAMP_HEADER, timestampHeader);
		
		 List<String> nonceHeader = new ArrayList<String>();
		 String nonce = SignatureUtils.generateNonce();
		 nonceHeader.add(nonce);
		 headers.put(ConstantUtils.NONCE_HEADER, nonceHeader);
		 
		 List<String> signatureMethodHeader = new ArrayList<String>();
		 String signatureMethod = ConstantUtils.DEFAULT_SIGNATURE_METHOD;
		 signatureMethodHeader.add(signatureMethod);
		 headers.put(ConstantUtils.SIGNATURE_METHOD_HEADER, signatureMethodHeader);
		 
		 List<String> signatureHeader = new ArrayList<String>();
		 String signature = SignatureUtils.getSignature(httpMethod, url, timestamp, nonce, clientId, clientSecretKey, transactionParameters, signatureMethod);
		 signatureHeader.add(signature);
		 headers.put(ConstantUtils.SIGNATURE_HEADER, signatureHeader);
		 
		 return headers;
	 }
	 
	 public static boolean isNullorEmpty(String str)
	 {
		return str == null || "".equalsIgnoreCase(str);
	 }
		

	 
}
