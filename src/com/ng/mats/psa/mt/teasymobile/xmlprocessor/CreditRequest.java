package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "CreditAccountNo" }, name = "CreditRequest")
public class CreditRequest {

	private CreditAccountNo creditAccountNo = null;

	@Override
	public String toString() {
		return "CreditRequest{" + "CreditAccountNo=" + creditAccountNo + "}";
	}

	@XmlAttribute
	public CreditAccountNo getCreditAccountNo() {
		return creditAccountNo;
	}

	public void setCreditAccountNo(CreditAccountNo creditAccountNo) {
		this.creditAccountNo = creditAccountNo;
	}

}