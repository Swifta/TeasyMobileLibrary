package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "recID", "destinationCode", "accountIdentificationName",
		"accountIdentificationNumber", "narration", "paymentReference",
		"fTAmount" }, name = "record")
public class Record {

	private RecID recID = null;
	private DestinationCode destinationCode = null;
	private AccountIdentificationName accountIdentificationName = null;
	private AccountIdentificationNumber accountIdentificationNumber = null;
	private Narration narration = null;
	private PaymentReference paymentReference = null;
	private FTAmount fTAmount = null;

	@Override
	public String toString() {
		return "Record{" + "RecID=" + recID + ",DestinationCode="
				+ destinationCode + "AccountIdentificationName="
				+ accountIdentificationName + "AccountIdentificationNumber="
				+ accountIdentificationNumber + "Narration=" + narration
				+ "PaymentReference=" + paymentReference + "FTAmount="
				+ fTAmount + "}";
	}

	public RecID getRecID() {
		return recID;
	}

	public void setRecID(RecID recID) {
		this.recID = recID;
	}

	public DestinationCode getDestinationCode() {
		return destinationCode;
	}

	public void setDestinationCode(DestinationCode destinationCode) {
		this.destinationCode = destinationCode;
	}

	public AccountIdentificationName getAccountIdentificationName() {
		return accountIdentificationName;
	}

	public void setAccountIdentificationName(
			AccountIdentificationName accountIdentificationName) {
		this.accountIdentificationName = accountIdentificationName;
	}

	public AccountIdentificationNumber getAccountIdentificationNumber() {
		return accountIdentificationNumber;
	}

	public void setAccountIdentificationNumber(
			AccountIdentificationNumber accountIdentificationNumber) {
		this.accountIdentificationNumber = accountIdentificationNumber;
	}

	public Narration getNarration() {
		return narration;
	}

	public void setNarration(Narration narration) {
		this.narration = narration;
	}

	public PaymentReference getPaymentReference() {
		return paymentReference;
	}

	public void setPaymentReference(PaymentReference paymentReference) {
		this.paymentReference = paymentReference;
	}

	public FTAmount getfTAmount() {
		return fTAmount;
	}

	public void setfTAmount(FTAmount fTAmount) {
		this.fTAmount = fTAmount;
	}

}