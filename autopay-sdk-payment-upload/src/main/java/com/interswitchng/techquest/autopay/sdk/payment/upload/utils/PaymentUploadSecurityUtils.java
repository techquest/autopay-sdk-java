package com.interswitchng.techquest.autopay.sdk.payment.upload.utils;

import java.security.SecureRandom;
import java.util.ArrayList;

import org.bouncycastle.util.encoders.Hex;

import com.interswitchng.techquest.autopay.sdk.lib.utils.SecurityUtils;
import com.interswitchng.techquest.autopay.sdk.payment.upload.dto.PaymentRequest;
import com.interswitchng.techquest.autopay.sdk.payment.upload.dto.UploadPaymentRequest;


public class PaymentUploadSecurityUtils extends SecurityUtils{

	
	 
	 public String getPaymentUploadTransactionParameters(UploadPaymentRequest uploadPaymentRequest)
	 {
		 String paymentRequestTransactionParameters = "";
		 ArrayList<String> parameterList = getTransactionParametersList(uploadPaymentRequest);
		 
		 for(String str : parameterList)
			 if(!SecurityUtils.isNullorEmpty(str)) 
				 paymentRequestTransactionParameters += "&" + str;
		 
		 return paymentRequestTransactionParameters;
	 }
	 
	 
	 private ArrayList<String> getTransactionParametersList(UploadPaymentRequest uploadPaymentRequest)
	 {
		 PaymentRequest paymentRequestArr[] = uploadPaymentRequest.getPayments();
		 ArrayList<String> parameterList = new ArrayList<String>();
		 
		 parameterList.add(SecurityUtils.isNullorEmpty(uploadPaymentRequest.getTerminalId()) ? "" : uploadPaymentRequest.getTerminalId());
		 parameterList.add(SecurityUtils.isNullorEmpty(uploadPaymentRequest.getBatchName()) ? "" : uploadPaymentRequest.getBatchName());
		 parameterList.add(SecurityUtils.isNullorEmpty(uploadPaymentRequest.getSourceAccount()) ? "" : uploadPaymentRequest.getSourceAccount());
		 parameterList.add(getAccountNumbersTransactionParameter(paymentRequestArr));
		 parameterList.add(getPaymentRefsTransactionParameter(paymentRequestArr));
		 parameterList.add(getBeneficiaryNamesTransactionParameter(paymentRequestArr));
		 parameterList.add(getBeneficiaryCodesTransactionParameter(paymentRequestArr));
		 parameterList.add(getAmountsTransactionParameter(paymentRequestArr));		 
		 return parameterList;
	 }
	 
	 
	 private String getAccountNumbersTransactionParameter(PaymentRequest[] paymentRequestArr)
	 {
		 String node = "";
		 for(PaymentRequest paymentRequest: paymentRequestArr)
			 node += SecurityUtils.isNullorEmpty(paymentRequest.getAccountNumber()) ? "" : paymentRequest.getAccountNumber();
		 return node;
	 }
	 
	 private String getPaymentRefsTransactionParameter(PaymentRequest[] paymentRequestArr)
	 {
		 String node = "";
		 for(PaymentRequest paymentRequest: paymentRequestArr)
			 node += SecurityUtils.isNullorEmpty(paymentRequest.getPaymentRef()) ? "" : paymentRequest.getPaymentRef();
		 return node;
	 }
	 
	 private String getBeneficiaryNamesTransactionParameter(PaymentRequest[] paymentRequestArr)
	 {
		 String node = "";
		 for(PaymentRequest paymentRequest: paymentRequestArr)
			 node += SecurityUtils.isNullorEmpty(paymentRequest.getBeneficiaryName()) ? "" : paymentRequest.getBeneficiaryName();
		 return node;
	 }
	 
	 private String getBeneficiaryCodesTransactionParameter(PaymentRequest[] paymentRequestArr)
	 {
		 String node = "";
		 for(PaymentRequest paymentRequest: paymentRequestArr)
			 node += SecurityUtils.isNullorEmpty(paymentRequest.getBeneficiaryCode()) ? "" : paymentRequest.getBeneficiaryCode();
		 return node;
	 }
	 
	 private String getAmountsTransactionParameter(PaymentRequest[] paymentRequestArr)
	 {
		 String node = "";
		 for(PaymentRequest paymentRequest: paymentRequestArr)
			 node += SecurityUtils.isNullorEmpty(paymentRequest.getAmount()) ? "" : paymentRequest.getAmount();
		 return node;
	 }
	 
}
