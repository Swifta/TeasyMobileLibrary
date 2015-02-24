package com.ng.mats.psa.mt.teasymobile.model;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(name = "NumberOfRecords")
public class NumberOfRecords {
	private String value = null;

	@Override
	public String toString() {
		return "NumberOfRecords{" + "value=" + value + '}';
	}

	@XmlValue
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}