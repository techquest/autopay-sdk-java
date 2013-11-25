package com.interswitchng.techquest.autopay.sdk.payment.upload.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameMappingStrategy;

import com.interswitchng.techquest.autopay.sdk.payment.upload.dto.CsvUploadPaymentRequest;
import com.interswitchng.techquest.autopay.sdk.payment.upload.dto.PaymentRequest;
import com.interswitchng.techquest.autopay.sdk.payment.upload.dto.UploadPaymentRequest;

public class CsvPaymentUtil {

	static Mapper mapper = new DozerBeanMapper();
	
	
	public static UploadPaymentRequest extractUploadPaymentRequestFromCSVFile(File file) throws IOException
	{
		List<CsvUploadPaymentRequest> csvUploadPaymentRequestList = extractCSVUploadPaymentRequestFromCSV(file);
		UploadPaymentRequest uploadPaymentRequest = new UploadPaymentRequest();
		if(csvUploadPaymentRequestList == null || csvUploadPaymentRequestList.size() < 1)
			return null;
		
		CsvUploadPaymentRequest csvUploadPaymentRequest = csvUploadPaymentRequestList.get(0);		
		uploadPaymentRequest.setBatchName(csvUploadPaymentRequest.getBatchName());
		uploadPaymentRequest.setIsBulkRemittance(csvUploadPaymentRequest.getIsBulkRemittance());
		uploadPaymentRequest.setIsConsolidated(csvUploadPaymentRequest.getIsConsolidated());
		uploadPaymentRequest.setIsOffline(csvUploadPaymentRequest.getIsOffline());
		uploadPaymentRequest.setMac(csvUploadPaymentRequest.getMac());
		uploadPaymentRequest.setSourceAccount(csvUploadPaymentRequest.getSourceAccount());
		uploadPaymentRequest.setTerminalId(csvUploadPaymentRequest.getTerminalId());
		PaymentRequest[] paymentRequestArr = extractPaymentRequestsFromCSVUploadPaymentRequest(csvUploadPaymentRequestList);
		uploadPaymentRequest.setPayments(paymentRequestArr);
		
		return uploadPaymentRequest;
	}
	
	private static List<CsvUploadPaymentRequest> extractCSVUploadPaymentRequestFromCSV(File file) throws IOException
	{        
        HeaderColumnNameMappingStrategy<CsvUploadPaymentRequest> headerColumnNameMappingStrategy = new HeaderColumnNameMappingStrategy<CsvUploadPaymentRequest>();
        headerColumnNameMappingStrategy.setType(CsvUploadPaymentRequest.class);
        
        CsvToBean<CsvUploadPaymentRequest> csvToBean = new CsvToBean<CsvUploadPaymentRequest>();
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        List<CsvUploadPaymentRequest> csvUploadPaymentRequestList = csvToBean.parse(headerColumnNameMappingStrategy, inputStreamReader);
        
        return csvUploadPaymentRequestList;
	}
	
	private static PaymentRequest[] extractPaymentRequestsFromCSVUploadPaymentRequest(List<CsvUploadPaymentRequest> csvUploadPaymentRequestList)
	{
		if(csvUploadPaymentRequestList == null || csvUploadPaymentRequestList.size() < 1)
			return null;
		
		int index = 0;
		PaymentRequest paymentRequestArr[] = new PaymentRequest[csvUploadPaymentRequestList.size()];
		for(CsvUploadPaymentRequest csvUploadPaymentRequest : csvUploadPaymentRequestList)
		{
			PaymentRequest paymentRequest = new PaymentRequest();
			paymentRequest = mapper.map(csvUploadPaymentRequest, PaymentRequest.class); 
			paymentRequestArr[index++] = paymentRequest;
		}
		
		return paymentRequestArr;
	}
	

	
}
