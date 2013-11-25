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
import com.interswitchng.techquest.autopay.sdk.lib.utils.SecurityUtils;

public class CsvBeneficiaryUpload extends BeneficiaryUpload{

	public RestResponse uploadBeneficiarys(String csvFile) throws IOException
	{
		Assert.assertNotNull("Ensure CSV File is not null", csvFile);
		Assert.assertTrue("Ensure CSV File is not empty", !csvFile.equalsIgnoreCase(""));
		File file = new File(csvFile);
		Assert.assertTrue("Ensure CSV File exists", file.exists());
		
		UploadBeneficiaryRequest uploadBeneficiaryRequest = CsvBeneficiaryUtil.extractUploadBeneficiaryRequestFromCSVFile(file);
		RestResponse restResponse = uploadBeneficiarys(uploadBeneficiaryRequest);
		return restResponse;
	}

}
