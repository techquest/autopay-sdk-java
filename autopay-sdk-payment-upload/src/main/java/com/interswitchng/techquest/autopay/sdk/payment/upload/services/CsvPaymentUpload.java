package com.interswitchng.techquest.autopay.sdk.payment.upload.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;

import com.interswitchng.techquest.autopay.sdk.lib.dto.RestResponse;
import com.interswitchng.techquest.autopay.sdk.lib.http.AutoPAYHttpClient;
import com.interswitchng.techquest.autopay.sdk.lib.utils.SecurityUtils;
import com.interswitchng.techquest.autopay.sdk.payment.upload.dto.UploadPaymentRequest;
import com.interswitchng.techquest.autopay.sdk.payment.upload.utils.ConstantUtils;
import com.interswitchng.techquest.autopay.sdk.payment.upload.utils.CsvPaymentUtil;
import com.interswitchng.techquest.autopay.sdk.payment.upload.utils.PaymentUploadSecurityUtils;

public class CsvPaymentUpload {

	// Get Client ID from Interswitch developer console. 
	public static String CLIENT_ID;
	// Get Client Secret Key from Interswitch developer console.
	public static String CLIENT_SECRET_KEY;
	
	public CsvPaymentUpload()
	{
		Assert.assertNotNull("Ensure Client Id is not null", CLIENT_ID);
		Assert.assertTrue("Ensure Client Id is not empty", !CLIENT_ID.equalsIgnoreCase(""));
		Assert.assertNotNull("Ensure Client Secret Key is not null", CLIENT_SECRET_KEY);
		Assert.assertTrue("Ensure Client Secret Key is not empty", !CLIENT_SECRET_KEY.equalsIgnoreCase(""));
		
//      
//		if (!file.exists()) 
//      {
//          System.out.println("The file you specified does not exist. path=" + pathToCsvFile);
//          return new ArrayList<CsvUploadPaymentRequest>();
//      }
	}
	
	public RestResponse uploadPayments(String csvFile) throws IOException
	{
		Assert.assertNotNull("Ensure CSV File is not null", csvFile);
		Assert.assertTrue("Ensure CSV File is not empty", !csvFile.equalsIgnoreCase(""));
		File file = new File(csvFile);
		Assert.assertTrue("Ensure CSV File exists", file.exists());
		
		UploadPaymentRequest uploadPaymentRequest = CsvPaymentUtil.extractUploadPaymentRequestFromCSVFile(file);
		AutoPAYHttpClient<UploadPaymentRequest> autoPAYHttpClient = new AutoPAYHttpClient<UploadPaymentRequest>();
		HashMap<String, List<String>> headers = getPaymentHeaders(ConstantUtils.PAYMENT_UPLOAD_RESOURCE_PATH, uploadPaymentRequest);
		RestResponse restResponse = autoPAYHttpClient.post(ConstantUtils.PAYMENT_UPLOAD_RESOURCE_PATH, uploadPaymentRequest, headers);
		return restResponse;
	}
	
	
	private HashMap<String, List<String>> getPaymentHeaders(String url, UploadPaymentRequest uploadPaymentRequest)
	{
		PaymentUploadSecurityUtils securityUtils = new PaymentUploadSecurityUtils();
		String transactionParameters = securityUtils.getPaymentUploadTransactionParameters(uploadPaymentRequest);
		HashMap<String, List<String>> headers = securityUtils.getSecurityHeaders(CLIENT_ID, CLIENT_SECRET_KEY, com.interswitchng.techquest.autopay.sdk.lib.utils.ConstantUtils.HTTP_POST, url, transactionParameters);
		return headers;
	}
}
