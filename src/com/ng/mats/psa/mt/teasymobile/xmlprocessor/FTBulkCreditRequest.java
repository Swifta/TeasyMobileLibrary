package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "Header", "Record" }, name = "FTBulkCreditRequest")
public class FTBulkCreditRequest {

	private Header header = null;
	private Record record = null;

	@Override
	public String toString() {
		return "FTBulkCreditRequest{" + "Header=" + header + ", Record="
				+ record + "}";
	}

	@XmlAttribute
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	@XmlAttribute
	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

}