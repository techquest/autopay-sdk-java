package com.interswitchng.techquest.autopay.sdk.lib.utils;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.bouncycastle.util.encoders.Base64;

public class SignatureUtils {

	
	private static final String ISO_8859_1 = "ISO-8859-1";
	
	
	
	public static String getSignature(String httpMethod, String url, String timestamp, String nonce, String clientId, String clientSecretKey, String transactionParameters, String signatureMethod)
	{
		try
		{
			String signatureCipher = getBaseSignatureCipher(httpMethod, url, String.valueOf(timestamp), nonce, clientId, clientSecretKey);
			signatureCipher = signatureCipher + transactionParameters;
			MessageDigest messagedigest = MessageDigest.getInstance(signatureMethod);
			byte []signaturebytes = messagedigest.digest(signatureCipher.getBytes());
			String signature = new String(Base64.encode(signaturebytes)).trim();
			System.out.println("signatureCipher: " + signatureCipher);
			System.out.println("signature: " + signature);
			System.out.println("signature method: " + signatureMethod);
			return signature;
		}
		catch(Exception ex)
		{
			return "";
		}
	}
	
	private static String getBaseSignatureCipher(String httpMethod, String url, String timestamp, String nonce, String clientId, String clientSecretKey)
	{
//		url = url.replace("https://", "");
		url = url.replace("http://", "https://");
		try{url = URLEncoder.encode(url, ISO_8859_1);}
		catch(Exception ex){ex.printStackTrace();}
		String baseStringToBeSigned = httpMethod + "&" + url + "&" + timestamp + "&" + nonce + "&" + clientId + "&" + clientSecretKey;
		return baseStringToBeSigned;
	}
	
	public static String generateNonce()
	{
		try
		{
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			// Get 1024 random bits
			byte[] bytes = new byte[128/8];
			sr.nextBytes(bytes);
			return HexConverter.fromBinary2Hex(bytes);
		}
		catch(Exception ex)
		{
			return null;
		}
	}

}
