package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "message", "transactionTime", "debitamt", "creditamt",
		"charges", "destinationMDN", "transferID", "parentTxnID", "sctlID",
		"amount", "responseCode", "refID", "status" })
public class TransferResponse {

}
package com.ng.mats.psa.mt.fortis.xmlprocessor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "message", "transactionTime", "debitamt", "creditamt",
		"charges", "destinationMDN", "transferID", "parentTxnID", "sctlID",
		"amount", "responseCode", "refID", "status" })
public class Response {

	private Message message = null;
	private TransactionTime transactionTime = null;
	private Debitamt debitamt = null;
	private Creditamt creditamt = null;
	private Charges charges = null;
	private DestinationMDN destinationMDN = null;
	private TransferID transferID = null;
	private ParentTxnID parentTxnID = null;
	private SctlID sctlID = null;
	private Amount amount = null;
	private ResponseCode responseCode = null;
	private RefID refID = null;
	private Status status = null;

	@Override
	public String toString() {
		return "Response{" + "message=" + message + ", transactionTime="
				+ transactionTime + ", debitamt=" + debitamt + ", creditamt="
				+ creditamt + ", charges=" + charges + ", destinationMDN="
				+ destinationMDN + ", transferID=" + transferID
				+ ", parentTxnID=" + parentTxnID + ", sctlID=" + sctlID
				+ ", Amount=" + amount + ", ResponseCode=" + responseCode
				+ ", refID=" + refID + ",Status=" + status + '}';
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public TransactionTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(TransactionTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	public Debitamt getDebitamt() {
		return debitamt;
	}

	public void setDebitamt(Debitamt debitamt) {
		this.debitamt = debitamt;
	}

	public Creditamt getCreditamt() {
		return creditamt;
	}

	public void setCreditamt(Creditamt creditamt) {
		this.creditamt = creditamt;
	}

	public Charges getCharges() {
		return charges;
	}

	public void setCharges(Charges charges) {
		this.charges = charges;
	}

	public DestinationMDN getDestinationMDN() {
		return destinationMDN;
	}

	public void setDestinationMDN(DestinationMDN destinationMDN) {
		this.destinationMDN = destinationMDN;
	}

	public TransferID getTransferID() {
		return transferID;
	}

	public void setTransferID(TransferID transferID) {
		this.transferID = transferID;
	}

	public ParentTxnID getParentTxnID() {
		return parentTxnID;
	}

	public void setParentTxnID(ParentTxnID parentTxnID) {
		this.parentTxnID = parentTxnID;
	}

	public SctlID getSctlID() {
		return sctlID;
	}

	public void setSctlID(SctlID sctlID) {
		this.sctlID = sctlID;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	public RefID getRefID() {
		return refID;
	}

	public void setRefID(RefID refID) {
		this.refID = refID;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
