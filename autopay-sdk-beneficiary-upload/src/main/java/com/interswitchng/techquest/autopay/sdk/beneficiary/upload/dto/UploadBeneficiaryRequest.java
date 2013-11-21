package com.interswitchng.techquest.autopay.sdk.beneficiary.upload.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "beneficiarys")
public class UploadBeneficiaryRequest {

	@XmlElement(name = "beneficiary")
	BeneficiaryRequest[] beneficiarys;
//	@XmlElement(name = "payment")
//	PaymentRequest[] payments;
	
	String terminalId;
	String batchName;
	String isBulkRemittance;
	String sourceAccount;
	String isOffline;
	String isConsolidated;
	String mac;
	
	
	public BeneficiaryRequest[] getBeneficiarys() {
		return beneficiarys;
	}
	public void setBeneficiarys(BeneficiaryRequest[] beneficiarys) {
		this.beneficiarys = beneficiarys;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public String getIsBulkRemittance() {
		return isBulkRemittance;
	}
	public void setIsBulkRemittance(String isBulkRemittance) {
		this.isBulkRemittance = isBulkRemittance;
	}
	public String getSourceAccount() {
		return sourceAccount;
	}
	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	public String getIsOffline() {
		return isOffline;
	}
	public void setIsOffline(String isOffline) {
		this.isOffline = isOffline;
	}
	public String getIsConsolidated() {
		return isConsolidated;
	}
	public void setIsConsolidated(String isConsolidated) {
		this.isConsolidated = isConsolidated;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
//	public PaymentRequest[] getPayments() {
//		return payments;
//	}
//	public void setPayments(PaymentRequest[] payments) {
//		this.payments = payments;
//	}
	
	
	
	
	
}
