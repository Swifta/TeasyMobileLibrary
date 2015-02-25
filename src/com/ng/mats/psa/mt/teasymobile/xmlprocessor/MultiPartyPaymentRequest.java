package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "walletDebitCreditInfo", "fTBulkCreditRequest" }, name = "MultiPartyPaymentRequest")
public class MultiPartyPaymentRequest {
	private WalletDebitCreditInfo walletDebitCreditInfo = null;
	private FTBulkCreditRequest fTBulkCreditRequest = null;

	@Override
	public String toString() {
		return "MultiPartyPaymentRequest{" + "WalletDebitCreditInfo="
				+ walletDebitCreditInfo + ", WalletDebitCreditInfo="
				+ fTBulkCreditRequest + '}';
	}

	public WalletDebitCreditInfo getWalletDebitCreditInfo() {
		return walletDebitCreditInfo;
	}

	public void setWalletDebitCreditInfo(
			WalletDebitCreditInfo walletDebitCreditInfo) {
		this.walletDebitCreditInfo = walletDebitCreditInfo;
	}

	public FTBulkCreditRequest getfTBulkCreditRequest() {
		return fTBulkCreditRequest;
	}

	public void setfTBulkCreditRequest(FTBulkCreditRequest fTBulkCreditRequest) {
		this.fTBulkCreditRequest = fTBulkCreditRequest;
	}
}