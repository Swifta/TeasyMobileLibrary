package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "DebitAmount")
public class DebitAmount {
	private String value = null;

	@Override
	public String toString() {
		return "DebitAmount{" + "value=" + value + '}';
	}

	@XmlValue
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}