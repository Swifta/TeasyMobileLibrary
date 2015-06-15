package com.ng.mats.psa.mt.teasymobile.model;

import java.math.BigDecimal;

public class MoneyTransfer {

	private String receiver = "";
	private String sender = "";
	private String transactionType = "";;
	private BigDecimal amount;
	private String mmo = "";
	private String teasypin = "";
	private String teasyrequestreference = "";
	private String pingRequestParam = "";
	private String url = "";
	private String username = "";
	private String password = "";
	private String batchNumberValue = "";
	private String numberOfRecordsValue = "";
	private String accountIdentificationNameValue = "";
	private String accountIdentificationNumberValue = "";
	private String destinationCodeValue = "";
	private String fTAmountValue = "";
	private String narrationValue = "";
	private String paymentReferenceValue = "";
	private String recIDValue = "";
	private String originCodeValue = "";
	private String usernameValue = "";
	private String passwordValue = "";
	private String creditAccountNoValue = "";
	private String debitAccountNoValue = "";
	private String debitAccountPINValue = "";
	private String debitAmountValue = "";
	private String debitFeeValue = "";

	// @XmlTransient
	// private CashOutStatus status = CashOutStatus.PENDING;
	private String reference = "";

	public MoneyTransfer() {

		this.receiver = "";
		this.transactionType = "";
		this.mmo = "";
		this.teasypin = "";
		// this.teasyrequestreference = teasyrequestreference;
		// this.status = status;
		this.reference = "";
		this.url = "";
		this.username = "";
		this.password = "";

	}

	public MoneyTransfer(String receiver, BigDecimal amount, String reference,
			String teasypin) {
		super();

		this.receiver = receiver;

		this.amount = amount;
		this.teasypin = teasypin;
		// this.teasyrequestreference = teasyrequestreference;
		// this.status = status;
		this.reference = reference;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getMmo() {
		return mmo;
	}

	public void setMmo(String mmo) {
		this.mmo = mmo;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	// public CashOutStatus getStatus() {
	// return status;
	// }
	//
	// public void setStatus(CashOutStatus status) {
	// this.status = status;

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTeasypin() {
		return teasypin;
	}

	public void setTeasypin(String teasypin) {
		this.teasypin = teasypin;
	}

	public String getTeasyrequestreference() {
		return teasyrequestreference;
	}

	public void setTeasyrequestreference(String teasyrequestreference) {
		this.teasyrequestreference = teasyrequestreference;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getPingRequestParam() {
		return pingRequestParam;
	}

	public void setPingRequestParam(String pingRequestParam) {
		this.pingRequestParam = pingRequestParam;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBatchNumberValue() {
		return batchNumberValue;
	}

	public void setBatchNumberValue(String batchNumberValue) {
		this.batchNumberValue = batchNumberValue;
	}

	public String getNumberOfRecordsValue() {
		return numberOfRecordsValue;
	}

	public void setNumberOfRecordsValue(String numberOfRecordsValue) {
		this.numberOfRecordsValue = numberOfRecordsValue;
	}

	public String getAccountIdentificationNameValue() {
		return accountIdentificationNameValue;
	}

	public void setAccountIdentificationNameValue(
			String accountIdentificationNameValue) {
		this.accountIdentificationNameValue = accountIdentificationNameValue;
	}

	public String getAccountIdentificationNumberValue() {
		return accountIdentificationNumberValue;
	}

	public void setAccountIdentificationNumberValue(
			String accountIdentificationNumberValue) {
		this.accountIdentificationNumberValue = accountIdentificationNumberValue;
	}

	public String getDestinationCodeValue() {
		return destinationCodeValue;
	}

	public void setDestinationCodeValue(String destinationCodeValue) {
		this.destinationCodeValue = destinationCodeValue;
	}

	public String getfTAmountValue() {
		return fTAmountValue;
	}

	public void setfTAmountValue(String fTAmountValue) {
		this.fTAmountValue = fTAmountValue;
	}

	public String getNarrationValue() {
		return narrationValue;
	}

	public void setNarrationValue(String narrationValue) {
		this.narrationValue = narrationValue;
	}

	public String getPaymentReferenceValue() {
		return paymentReferenceValue;
	}

	public void setPaymentReferenceValue(String paymentReferenceValue) {
		this.paymentReferenceValue = paymentReferenceValue;
	}

	public String getRecIDValue() {
		return recIDValue;
	}

	public void setRecIDValue(String recIDValue) {
		this.recIDValue = recIDValue;
	}

	public String getOriginCodeValue() {
		return originCodeValue;
	}

	public void setOriginCodeValue(String originCodeValue) {
		this.originCodeValue = originCodeValue;
	}

	public String getUsernameValue() {
		return usernameValue;
	}

	public void setUsernameValue(String usernameValue) {
		this.usernameValue = usernameValue;
	}

	public String getPasswordValue() {
		return passwordValue;
	}

	public void setPasswordValue(String passwordValue) {
		this.passwordValue = passwordValue;
	}

	public String getCreditAccountNoValue() {
		return creditAccountNoValue;
	}

	public void setCreditAccountNoValue(String creditAccountNoValue) {
		this.creditAccountNoValue = creditAccountNoValue;
	}

	public String getDebitAccountNoValue() {
		return debitAccountNoValue;
	}

	public void setDebitAccountNoValue(String debitAccountNoValue) {
		this.debitAccountNoValue = debitAccountNoValue;
	}

	public String getDebitAccountPINValue() {
		return debitAccountPINValue;
	}

	public void setDebitAccountPINValue(String debitAccountPINValue) {
		this.debitAccountPINValue = debitAccountPINValue;
	}

	public String getDebitAmountValue() {
		return debitAmountValue;
	}

	public void setDebitAmountValue(String debitAmountValue) {
		this.debitAmountValue = debitAmountValue;
	}

	public String getDebitFeeValue() {
		return debitFeeValue;
	}

	public void setDebitFeeValue(String debitFeeValue) {
		this.debitFeeValue = debitFeeValue;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Receiver: " + receiver + "\n");
		sb.append("Sender: " + sender + "\n");
		sb.append("Amount: " + amount + "\n");
		sb.append("Reference: " + reference + "\n");
		sb.append("Mmo: " + mmo + "\n");
		sb.append("Url: " + url + "\n");
		sb.append("Username: " + username + "\n");
		sb.append("Password: " + password + "\n");
		// sb.append("Status: " + status + "\n");
		sb.append("batchNumberValue: " + batchNumberValue + "\n");
		sb.append("numberOfRecordsValue: " + numberOfRecordsValue + "\n");
		sb.append("accountIdentificationNameValue: "
				+ accountIdentificationNameValue + "\n");
		sb.append("accountIdentificationNumberValue: "
				+ accountIdentificationNumberValue + "\n");
		sb.append("destinationCodeValue: " + destinationCodeValue + "\n");
		sb.append("fTAmountValue: " + fTAmountValue + "\n");
		sb.append("narrationValue: " + narrationValue + "\n");
		sb.append("paymentReferenceValue: " + paymentReferenceValue + "\n");
		sb.append("recIDValue: " + recIDValue + "\n");
		sb.append("originCodeValue: " + originCodeValue + "\n");
		sb.append("usernameValue: " + usernameValue + "\n");
		sb.append("passwordValue: " + passwordValue + "\n");
		sb.append("creditAccountNoValue: " + creditAccountNoValue + "\n");
		sb.append("debitAccountNoValue: " + debitAccountNoValue + "\n");
		sb.append("debitAccountPINValue: " + debitAccountPINValue + "\n");
		sb.append("debitAmountValue: " + debitAmountValue + "\n");
		sb.append("debitFeeValue: " + debitFeeValue + "\n");

		return sb.toString();
	}

}
