package com.interswitchng.techquest.autopay.sdk.beneficiary.upload.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "beneficiary")
public class BeneficiaryRequest {

	String beneficiaryCode;
	String beneficiaryName;
	String accountNumber;
	String accountType;
	String bankCBNCode;
	String maxPayableAmount;
	String email;
	String mobile;
	String isCashCard;
	String category;
	String currencyCode;
	public String getBeneficiaryCode() {
		return beneficiaryCode;
	}
	public void setBeneficiaryCode(String beneficiaryCode) {
		this.beneficiaryCode = beneficiaryCode;
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
	public String getMaxPayableAmount() {
		return maxPayableAmount;
	}
	public void setMaxPayableAmount(String maxPayableAmount) {
		this.maxPayableAmount = maxPayableAmount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIsCashCard() {
		return isCashCard;
	}
	public void setIsCashCard(String isCashCard) {
		this.isCashCard = isCashCard;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	
	
	
}
