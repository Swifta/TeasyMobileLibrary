package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "CreditRequest")
public class CreditRequest {

	private CreditAccountNo creditAccountNo = null;

	@Override
	public String toString() {
		return "CreditRequest{" + "CreditAccountNo=" + creditAccountNo + "}";
	}

	public CreditAccountNo getCreditAccountNo() {
		return creditAccountNo;
	}

	public void setCreditAccountNo(CreditAccountNo creditAccountNo) {
		this.creditAccountNo = creditAccountNo;
	}

}