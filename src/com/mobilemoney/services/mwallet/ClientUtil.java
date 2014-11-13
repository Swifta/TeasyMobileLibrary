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
		
		OMElement AdditionalHeader = fac.createOMElement("AdditionalHeader", omNs);
		
		OMElement usernameElement = fac.createOMElement("username", omNs);
		usernameElement.addChild(fac.createOMText(usernameElement,"teasy"));
		
		OMElement passwordElement = fac.createOMElement("password", omNs);
		passwordElement.addChild(fac.createOMText(passwordElement,"t3apwd"));
		
		AdditionalHeader.addChild(usernameElement);
		AdditionalHeader.addChild(passwordElement);
		

		return AdditionalHeader;
	}
	
	public static OMElement getBodyOMElement() {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		
		OMNamespace omNs = fac.createOMNamespace(
				"http://mobilemoney.com/services/mWallet", "mwal");
		
		OMElement DebitRequestElement = fac.createOMElement("DebitRequest", omNs);
		
		OMElement AccountNumberElement = fac.createOMElement("AccountNumber", omNs);
		AccountNumberElement.addChild(fac.createOMText(AccountNumberElement,"2348171000157"));
		
		OMElement OriginCodeElement = fac.createOMElement("OriginCode", omNs);
		OriginCodeElement.addChild(fac.createOMText(OriginCodeElement,"USRT"));
		
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
