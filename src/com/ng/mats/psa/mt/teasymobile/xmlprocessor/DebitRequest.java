package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "DebitAccountNo", "DebitAccountPIN", "DebitAmount",
		"DebitFee" }, name = "DebitRequest")
public class DebitRequest {

	private DebitAccountNo debitAccountNo = null;
	private DebitAccountPIN debitAccountPIN = null;
	private DebitAmount debitAmount = null;
	private DebitFee debitFee = null;

	@Override
	public String toString() {
		return "DebitRequest{" + "DebitAccountNo=" + debitAccountNo
				+ ", DebitAccountPIN=" + debitAccountPIN + ", DebitAmount="
				+ debitAmount + ", DebitFee=" + debitFee + "}";
	}

	@XmlAttribute
	public DebitAccountNo getDebitAccountNo() {
		return debitAccountNo;
	}

	public void setDebitAccountNo(DebitAccountNo debitAccountNo) {
		this.debitAccountNo = debitAccountNo;
	}

	@XmlAttribute
	public DebitAccountPIN getDebitAccountPIN() {
		return debitAccountPIN;
	}

	public void setDebitAccountPIN(DebitAccountPIN debitAccountPIN) {
		this.debitAccountPIN = debitAccountPIN;
	}

	@XmlAttribute
	public DebitAmount getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(DebitAmount debitAmount) {
		this.debitAmount = debitAmount;
	}

	@XmlAttribute
	public DebitFee getDebitFee() {
		return debitFee;
	}

	public void setDebitFee(DebitFee debitFee) {
		this.debitFee = debitFee;
	}

}