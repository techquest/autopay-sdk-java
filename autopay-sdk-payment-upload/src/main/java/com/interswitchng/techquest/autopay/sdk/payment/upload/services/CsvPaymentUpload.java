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

public class CsvPaymentUpload extends PaymentUpload{

	
	public RestResponse uploadPayments(String csvFile) throws IOException
	{
		Assert.assertNotNull("Ensure CSV File is not null", csvFile);
		Assert.assertTrue("Ensure CSV File is not empty", !csvFile.equalsIgnoreCase(""));
		File file = new File(csvFile);
		Assert.assertTrue("Ensure CSV File exists", file.exists());
		
		UploadPaymentRequest uploadPaymentRequest = CsvPaymentUtil.extractUploadPaymentRequestFromCSVFile(file);
		RestResponse restResponse = uploadPayments(uploadPaymentRequest);
		return restResponse;
	}
	
	
	
}
