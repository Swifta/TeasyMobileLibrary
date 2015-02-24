package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "FTBulkCreditRequest")
public class FTBulkCreditRequest {

	private Header header = null;
	private Record record = null;

	@Override
	public String toString() {
		return "FTBulkCreditRequest{" + "Header=" + header + ", Record="
				+ record + "}";
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

}