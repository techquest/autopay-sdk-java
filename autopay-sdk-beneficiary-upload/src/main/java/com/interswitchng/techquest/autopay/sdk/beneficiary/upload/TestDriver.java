package com.interswitchng.techquest.autopay.sdk.beneficiary.upload;

import java.util.List;

import com.interswitchng.techquest.autopay.sdk.beneficiary.upload.services.CsvBeneficiaryUpload;
import com.interswitchng.techquest.autopay.sdk.lib.dto.Errors;
import com.interswitchng.techquest.autopay.sdk.lib.dto.Error;
import com.interswitchng.techquest.autopay.sdk.lib.dto.RestResponse;

public class TestDriver {

	
	public static void main(String[] args)
	{
		try
		{
			// PaymentUpload paymentUpload = new PaymentUpload();
			String csvFile = "D:/Development/Systems Development/Programming/Java/autopay-sdk/autopay-sdk/autopay-sdk-payment-upload/src/main/resources/payment.csv";
//			String csvFile = "payment.csv";
			CsvBeneficiaryUpload.CLIENT_ID = "IKIAEC529AFD17F2933B45A79FFDF488B68E07C67ADA";
			CsvBeneficiaryUpload.CLIENT_SECRET_KEY = "NjPmuXURqZONFkYTWvXzv4TV9wwzAnWZykznYQ==";
			CsvBeneficiaryUpload csvBeneficiaryUpload = new CsvBeneficiaryUpload();
			RestResponse restResponse = csvBeneficiaryUpload.uploadBeneficiarys(csvFile);
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
