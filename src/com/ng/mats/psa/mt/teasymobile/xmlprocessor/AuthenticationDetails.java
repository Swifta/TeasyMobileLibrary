package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AuthenticationDetails")
public class AuthenticationDetails {

	private Username username = null;
	private Password password = null;
	private OriginCode originCode = null;

	@Override
	public String toString() {
		return "AuthenticationDetails{" + "Username=" + username
				+ ", Password=" + password + ", OriginCode=" + originCode + "}";
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public OriginCode getOriginCode() {
		return originCode;
	}

	public void setOriginCode(OriginCode originCode) {
		this.originCode = originCode;
	}

}