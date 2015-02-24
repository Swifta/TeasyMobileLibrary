package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "TransferResponse")
public class TransferResponse {
	private Status status = null;
	private Amount amount = null;
	private Fee fee = null;
	private Currency currency = null;

	@Override
	public String toString() {
		return "TransferResponse{" + "Status=" + status + ", Amount=" + amount
				+ ", Fee=" + fee + ", Currency=" + currency + '}';
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public Fee getFee() {
		return fee;
	}

	public void setFee(Fee fee) {
		this.fee = fee;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}
