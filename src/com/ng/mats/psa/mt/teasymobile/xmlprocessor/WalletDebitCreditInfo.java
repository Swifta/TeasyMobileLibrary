package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "WalletDebitCreditInfo")
public class WalletDebitCreditInfo {

	private AuthenticationDetails authenticationDetails = null;
	private DebitRequest debitRequest = null;
	private CreditRequest creditRequest = null;

	@Override
	public String toString() {
		return "WalletDebitCreditInfo{" + "AuthenticationDetails="
				+ authenticationDetails + ", DebitRequest=" + debitRequest
				+ ", CreditRequest=" + creditRequest + "}";
	}

	public AuthenticationDetails getAuthenticationDetails() {
		return authenticationDetails;
	}

	public void setAuthenticationDetails(
			AuthenticationDetails authenticationDetails) {
		this.authenticationDetails = authenticationDetails;
	}

	public DebitRequest getDebitRequest() {
		return debitRequest;
	}

	public void setDebitRequest(DebitRequest debitRequest) {
		this.debitRequest = debitRequest;
	}

	public CreditRequest getCreditRequest() {
		return creditRequest;
	}

	public void setCreditRequest(CreditRequest creditRequest) {
		this.creditRequest = creditRequest;
	}

}