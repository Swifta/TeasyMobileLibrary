package com.mobilemoney.services.mwallet;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;

public class ClientUtil {
	
	public static OMElement getHeaderOMElement() {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		
		OMNamespace omNs = fac.createOMNamespace(
				"http://mobilemoney.com/services/mWallet", "mwal");
		
		OMElement additionalHeader = fac.createOMElement("AdditionalHeader", omNs);
		
		OMElement usernameElement = fac.createOMElement("username", omNs);
		usernameElement.addChild(fac.createOMText(usernameElement,"cinfores"));
		
		OMElement passwordElement = fac.createOMElement("password", omNs);
		passwordElement.addChild(fac.createOMText(passwordElement,"apipass"));
		
		additionalHeader.addChild(usernameElement);
		additionalHeader.addChild(passwordElement);
		//we need to test this code first
		//then upload to the repo

		return additionalHeader;
	}
	
	public static OMElement getBodyOMElement() {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		
		OMNamespace omNs = fac.createOMNamespace(
				"http://mobilemoney.com/services/mWallet", "mwal");
		
		OMElement DebitRequestElement = fac.createOMElement("DebitRequest", omNs);
		
		OMElement AccountNumberElement = fac.createOMElement("AccountNumber", omNs);
		AccountNumberElement.addChild(fac.createOMText(AccountNumberElement,"2348171000157"));
		
		OMElement OriginCodeElement = fac.createOMElement("OriginCode", omNs);
		OriginCodeElement.addChild(fac.createOMText(OriginCodeElement,"CINFORES"));
		
		OMElement AccountPINElement = fac.createOMElement("AccountPIN", omNs);
		AccountPINElement.addChild(fac.createOMText(AccountPINElement,"7005"));
		
		OMElement RequestReferenceElement = fac.createOMElement("RequestReference", omNs);
		RequestReferenceElement.addChild(fac.createOMText(RequestReferenceElement,"201407010000001"));
		
		OMElement CurrencyElement = fac.createOMElement("Currency", omNs);
		CurrencyElement.addChild(fac.createOMText(CurrencyElement,"NGN"));
		
		OMElement AmountElement = fac.createOMElement("Amount", omNs);
		AmountElement.addChild(fac.createOMText(AmountElement,"1500"));
		
		DebitRequestElement.addChild(AccountNumberElement);
		DebitRequestElement.addChild(OriginCodeElement);
		DebitRequestElement.addChild(AccountPINElement);
		DebitRequestElement.addChild(RequestReferenceElement);
		DebitRequestElement.addChild(CurrencyElement);
		DebitRequestElement.addChild(AmountElement);
		

		return DebitRequestElement;
	}

}
