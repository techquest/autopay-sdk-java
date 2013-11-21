package com.interswitchng.techquest.autopay.sdk.beneficiary.upload.utils;

import java.security.SecureRandom;
import java.util.ArrayList;

import org.bouncycastle.util.encoders.Hex;

import com.interswitchng.techquest.autopay.sdk.beneficiary.upload.dto.BeneficiaryRequest;
import com.interswitchng.techquest.autopay.sdk.beneficiary.upload.dto.UploadBeneficiaryRequest;
import com.interswitchng.techquest.autopay.sdk.lib.utils.SecurityUtils;


public class BeneficiaryUploadSecurityUtils extends SecurityUtils{

	
	 
	 public String getBeneficiaryUploadTransactionParameters(UploadBeneficiaryRequest uploadBeneficiaryRequest)
	 {
		 String beneficiaryRequestTransactionParameters = "";
		 ArrayList<String> parameterList = getTransactionParametersList(uploadBeneficiaryRequest);
		 for(String str : parameterList)
			 if(!SecurityUtils.isNullorEmpty(str)) 
				 beneficiaryRequestTransactionParameters += "&" + str;
		 return beneficiaryRequestTransactionParameters;
	 }
	 
	 
	 private ArrayList<String> getTransactionParametersList(UploadBeneficiaryRequest uploadBeneficiaryRequest)
	 {
		 BeneficiaryRequest beneficiaryRequestArr[] = uploadBeneficiaryRequest.getBeneficiarys();
		 ArrayList<String> parameterList = new ArrayList<String>();
		 
		 parameterList.add(SecurityUtils.isNullorEmpty(uploadBeneficiaryRequest.getTerminalId()) ? "" : uploadBeneficiaryRequest.getTerminalId());
		 parameterList.add(SecurityUtils.isNullorEmpty(uploadBeneficiaryRequest.getBatchName()) ? "" : uploadBeneficiaryRequest.getBatchName());
		 parameterList.add(SecurityUtils.isNullorEmpty(uploadBeneficiaryRequest.getSourceAccount()) ? "" : uploadBeneficiaryRequest.getSourceAccount());
		 parameterList.add(getAccountNumbersTransactionParameter(beneficiaryRequestArr));
		 parameterList.add(getBeneficiaryNamesTransactionParameter(beneficiaryRequestArr));
		 parameterList.add(getBeneficiaryCodesTransactionParameter(beneficiaryRequestArr));
		 parameterList.add(getMaxPayableAmountsTransactionParameter(beneficiaryRequestArr));		 
		 return parameterList;
	 }
	 
	 
	 private String getAccountNumbersTransactionParameter(BeneficiaryRequest[] beneficiaryRequestArr)
	 {
		 String node = "";
		 for(BeneficiaryRequest beneficiaryRequest: beneficiaryRequestArr)
			 node += SecurityUtils.isNullorEmpty(beneficiaryRequest.getAccountNumber()) ? "" : beneficiaryRequest.getAccountNumber();
		 return node;
	 }
	 
	 private String getBeneficiaryNamesTransactionParameter(BeneficiaryRequest[] beneficiaryRequestArr)
	 {
		 String node = "";
		 for(BeneficiaryRequest beneficiaryRequest: beneficiaryRequestArr)
			 node += SecurityUtils.isNullorEmpty(beneficiaryRequest.getBeneficiaryName()) ? "" : beneficiaryRequest.getBeneficiaryName();
		 return node;
	 }
	 
	 private String getBeneficiaryCodesTransactionParameter(BeneficiaryRequest[] beneficiaryRequestArr)
	 {
		 String node = "";
		 for(BeneficiaryRequest beneficiaryRequest: beneficiaryRequestArr)
			 node += SecurityUtils.isNullorEmpty(beneficiaryRequest.getBeneficiaryCode()) ? "" : beneficiaryRequest.getBeneficiaryCode();
		 return node;
	 }
	 
	 private String getMaxPayableAmountsTransactionParameter(BeneficiaryRequest[] beneficiaryRequestArr)
	 {
		 String node = "";
		 for(BeneficiaryRequest beneficiaryRequest: beneficiaryRequestArr)
			 node += SecurityUtils.isNullorEmpty(beneficiaryRequest.getMaxPayableAmount()) ? "" : beneficiaryRequest.getMaxPayableAmount();
		 return node;
	 }
	 
}
