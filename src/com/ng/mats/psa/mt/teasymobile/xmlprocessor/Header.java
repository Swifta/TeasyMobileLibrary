package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.ng.mats.psa.mt.teasymobile.model.BatchNumber;
import com.ng.mats.psa.mt.teasymobile.model.NumberOfRecords;

@XmlType(propOrder = { "BatchNumber", "NumberOfRecords" }, name = "Header")
public class Header {

	private BatchNumber batchNumber = null;
	private NumberOfRecords numberOfRecords = null;

	@Override
	public String toString() {
		return "Header{" + "BatchNumber=" + batchNumber + ",NumberOfRecords="
				+ numberOfRecords + "}";
	}

	@XmlAttribute
	public BatchNumber getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(BatchNumber batchNumber) {
		this.batchNumber = batchNumber;
	}

	@XmlAttribute
	public NumberOfRecords getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(NumberOfRecords numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

}