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

		return sb.toString();
	}

}
