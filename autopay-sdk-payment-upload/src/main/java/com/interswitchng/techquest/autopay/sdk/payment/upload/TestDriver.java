package com.interswitchng.techquest.autopay.sdk.payment.upload;

import java.net.URL;
import java.util.List;

import com.interswitchng.techquest.autopay.sdk.lib.dto.Errors;
import com.interswitchng.techquest.autopay.sdk.lib.dto.Error;
import com.interswitchng.techquest.autopay.sdk.lib.dto.RestResponse;
import com.interswitchng.techquest.autopay.sdk.payment.upload.dto.UploadPaymentRequest;
import com.interswitchng.techquest.autopay.sdk.payment.upload.services.CsvPaymentUpload;
import com.interswitchng.techquest.autopay.sdk.payment.upload.utils.CsvPaymentUtil;

public class TestDriver {

	
	public static void main(String[] args)
	{
		try
		{
			// PaymentUpload paymentUpload = new PaymentUpload();
			URL resourceUrl = TestDriver.class.getResource("/payment.csv");
			String csvFile = resourceUrl.getFile();
			csvFile = csvFile.replace("%20", " ");
			CsvPaymentUpload.CLIENT_ID = "IKIAEC529AFD17F2933B45A79FFDF488B68E07C67ADA";
			CsvPaymentUpload.CLIENT_SECRET_KEY = "NjPmuXURqZONFkYTWvXzv4TV9wwzAnWZykznYQ==";
			CsvPaymentUpload csvUploadPayment = new CsvPaymentUpload();
			RestResponse restResponse = csvUploadPayment.uploadPayments(csvFile);
			if(restResponse.isSuccessful())
				System.out.println("Successful upload");
			else
			{
				System.out.println("Unsuccessful upload");
				Errors errors = (Errors) restResponse;
				Error[] error = errors.getErrors();
				System.out.println(error[0].getCode());
				System.out.println(error[0].getMessage());
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
