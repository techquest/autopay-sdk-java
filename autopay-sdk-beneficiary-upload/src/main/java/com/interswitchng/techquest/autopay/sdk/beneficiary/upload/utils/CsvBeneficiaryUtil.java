package com.interswitchng.techquest.autopay.sdk.beneficiary.upload.utils;

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

import com.interswitchng.techquest.autopay.sdk.beneficiary.upload.dto.BeneficiaryRequest;
import com.interswitchng.techquest.autopay.sdk.beneficiary.upload.dto.CsvUploadBeneficiaryRequest;
import com.interswitchng.techquest.autopay.sdk.beneficiary.upload.dto.UploadBeneficiaryRequest;

public class CsvBeneficiaryUtil {

	static Mapper mapper = new DozerBeanMapper();
	
	
	public static UploadBeneficiaryRequest extractUploadBeneficiaryRequestFromCSVFile(File file) throws IOException
	{
		List<CsvUploadBeneficiaryRequest> csvUploadBeneficiaryRequestList = extractCSVUploadBeneficiaryRequestFromCSV(file);
		UploadBeneficiaryRequest uploadBeneficiaryRequest = new UploadBeneficiaryRequest();
		if(csvUploadBeneficiaryRequestList == null || csvUploadBeneficiaryRequestList.size() < 1)
			return null;
		
		CsvUploadBeneficiaryRequest csvUploadBeneficiaryRequest = csvUploadBeneficiaryRequestList.get(0);		
		uploadBeneficiaryRequest.setBatchName(csvUploadBeneficiaryRequest.getBatchName());
		uploadBeneficiaryRequest.setIsBulkRemittance(csvUploadBeneficiaryRequest.getIsBulkRemittance());
		uploadBeneficiaryRequest.setIsConsolidated(csvUploadBeneficiaryRequest.getIsConsolidated());
		uploadBeneficiaryRequest.setIsOffline(csvUploadBeneficiaryRequest.getIsOffline());
		uploadBeneficiaryRequest.setMac(csvUploadBeneficiaryRequest.getMac());
		uploadBeneficiaryRequest.setSourceAccount(csvUploadBeneficiaryRequest.getSourceAccount());
		uploadBeneficiaryRequest.setTerminalId(csvUploadBeneficiaryRequest.getTerminalId());
		BeneficiaryRequest[] beneficiaryRequestArr = CsvBeneficiaryUtil.extractBeneficiaryRequestsFromCSVUploadBeneficiaryRequest(csvUploadBeneficiaryRequestList);
		uploadBeneficiaryRequest.setBeneficiarys(beneficiaryRequestArr);
		
		return uploadBeneficiaryRequest;
	}
	
	private static List<CsvUploadBeneficiaryRequest> extractCSVUploadBeneficiaryRequestFromCSV(File file) throws IOException
	{        
        HeaderColumnNameMappingStrategy<CsvUploadBeneficiaryRequest> headerColumnNameMappingStrategy = new HeaderColumnNameMappingStrategy<CsvUploadBeneficiaryRequest>();
        headerColumnNameMappingStrategy.setType(CsvUploadBeneficiaryRequest.class);
        
        CsvToBean<CsvUploadBeneficiaryRequest> csvToBean = new CsvToBean<CsvUploadBeneficiaryRequest>();
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        List<CsvUploadBeneficiaryRequest> csvUploadPaymentRequestList = csvToBean.parse(headerColumnNameMappingStrategy, inputStreamReader);
        
        return csvUploadPaymentRequestList;
	}
	
	private static BeneficiaryRequest[] extractBeneficiaryRequestsFromCSVUploadBeneficiaryRequest(List<CsvUploadBeneficiaryRequest> csvUploadBeneficiaryRequestList)
	{
		if(csvUploadBeneficiaryRequestList == null || csvUploadBeneficiaryRequestList.size() < 1)
			return null;
		
		int index = 0;
		BeneficiaryRequest paymentRequestArr[] = new BeneficiaryRequest[csvUploadBeneficiaryRequestList.size()];
		for(CsvUploadBeneficiaryRequest csvUploadBeneficiaryRequest : csvUploadBeneficiaryRequestList)
		{
			BeneficiaryRequest beneficiaryRequest = new BeneficiaryRequest();
			beneficiaryRequest = mapper.map(csvUploadBeneficiaryRequest, BeneficiaryRequest.class); 
			paymentRequestArr[index++] = beneficiaryRequest;
		}
		
		return paymentRequestArr;
	}
	

	
}
