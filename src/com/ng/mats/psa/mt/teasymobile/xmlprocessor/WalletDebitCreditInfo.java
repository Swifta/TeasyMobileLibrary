package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "AuthenticationDetails", "DebitRequest", "CreditRequest" }, name = "WalletDebitCreditInfo")
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

	@XmlAttribute
	public AuthenticationDetails getAuthenticationDetails() {
		return authenticationDetails;
	}

	public void setAuthenticationDetails(
			AuthenticationDetails authenticationDetails) {
		this.authenticationDetails = authenticationDetails;
	}

	@XmlAttribute
	public DebitRequest getDebitRequest() {
		return debitRequest;
	}

	public void setDebitRequest(DebitRequest debitRequest) {
		this.debitRequest = debitRequest;
	}

	@XmlAttribute
	public CreditRequest getCreditRequest() {
		return creditRequest;
	}

	public void setCreditRequest(CreditRequest creditRequest) {
		this.creditRequest = creditRequest;
	}

}