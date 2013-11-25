package com.interswitchng.techquest.autopay.sdk.beneficiary.upload.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;

import com.interswitchng.techquest.autopay.sdk.beneficiary.upload.dto.UploadBeneficiaryRequest;
import com.interswitchng.techquest.autopay.sdk.beneficiary.upload.utils.BeneficiaryUploadSecurityUtils;
import com.interswitchng.techquest.autopay.sdk.beneficiary.upload.utils.ConstantUtils;
import com.interswitchng.techquest.autopay.sdk.beneficiary.upload.utils.CsvBeneficiaryUtil;
import com.interswitchng.techquest.autopay.sdk.lib.dto.RestResponse;
import com.interswitchng.techquest.autopay.sdk.lib.http.AutoPAYHttpClient;

public abstract class BeneficiaryUpload {

	
	// Get Client ID from Interswitch developer console. 
	public static String CLIENT_ID;
	// Get Client Secret Key from Interswitch developer console.
	public static String CLIENT_SECRET_KEY;
	
	public BeneficiaryUpload()
	{
		Assert.assertNotNull("Ensure Client Id is not null", CLIENT_ID);
		Assert.assertTrue("Ensure Client Id is not empty", !CLIENT_ID.equalsIgnoreCase(""));
		Assert.assertNotNull("Ensure Client Secret Key is not null", CLIENT_SECRET_KEY);
		Assert.assertTrue("Ensure Client Secret Key is not empty", !CLIENT_SECRET_KEY.equalsIgnoreCase(""));
	}
	
	
	protected RestResponse uploadBeneficiarys(UploadBeneficiaryRequest uploadBeneficiaryRequest)
	{
		AutoPAYHttpClient<UploadBeneficiaryRequest> autoPAYHttpClient = new AutoPAYHttpClient<UploadBeneficiaryRequest>();
		HashMap<String, List<String>> headers = getPaymentHeaders(ConstantUtils.BENEFICIARY_UPLOAD_RESOURCE_PATH, uploadBeneficiaryRequest);
		RestResponse restResponse = autoPAYHttpClient.post(ConstantUtils.BENEFICIARY_UPLOAD_RESOURCE_PATH, uploadBeneficiaryRequest, headers);
		return restResponse;
	}
	
	
	private HashMap<String, List<String>> getPaymentHeaders(String url, UploadBeneficiaryRequest uploadBeneficiaryRequest)
	{
		BeneficiaryUploadSecurityUtils securityUtils = new BeneficiaryUploadSecurityUtils();
		String transactionParameters = securityUtils.getBeneficiaryUploadTransactionParameters(uploadBeneficiaryRequest);
		HashMap<String, List<String>> headers = securityUtils.getSecurityHeaders(CLIENT_ID, CLIENT_SECRET_KEY, com.interswitchng.techquest.autopay.sdk.lib.utils.ConstantUtils.HTTP_POST, url, transactionParameters);
		return headers;
	}
	
}
