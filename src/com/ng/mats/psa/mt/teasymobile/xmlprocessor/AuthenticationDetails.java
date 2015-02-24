package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "username", "password", "OriginCode" }, name = "AuthenticationDetails")
public class AuthenticationDetails {

	private Username username = null;
	private Password password = null;
	private OriginCode originCode = null;

	@Override
	public String toString() {
		return "AuthenticationDetails{" + "Username=" + username
				+ ", Password=" + password + ", OriginCode=" + originCode + "}";
	}

	@XmlAttribute
	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}

	@XmlAttribute
	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	@XmlAttribute
	public OriginCode getOriginCode() {
		return originCode;
	}

	public void setOriginCode(OriginCode originCode) {
		this.originCode = originCode;
	}

}