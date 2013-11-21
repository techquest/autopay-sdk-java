package com.interswitchng.techquest.autopay.sdk.payment.upload.dto;

public class CsvUploadPaymentRequest {

	private String terminalId; 
	private String batchName; 
	private String isBulkRemittance;	 
	private String sourceAccount; 
	private String isOffline;
	private String isConsolidated;	 
	private String mac;
	private String paymentRef;	 
	private String paymentType;	 
	private String beneficiaryCode;	 
	private String narration;
	private String amount;
	private String currencyCode;	 
	private String beneficiaryName;	 
	private String accountNumber; 
	private String accountType;
	private String bankCBNCode;
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
	public String getPaymentRef() {
		return paymentRef;
	}
	public void setPaymentRef(String paymentRef) {
		this.paymentRef = paymentRef;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getBeneficiaryCode() {
		return beneficiaryCode;
	}
	public void setBeneficiaryCode(String beneficiaryCode) {
		this.beneficiaryCode = beneficiaryCode;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBankCBNCode() {
		return bankCBNCode;
	}
	public void setBankCBNCode(String bankCBNCode) {
		this.bankCBNCode = bankCBNCode;
	}
	
	

}
