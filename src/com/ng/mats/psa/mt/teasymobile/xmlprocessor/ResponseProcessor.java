package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ResponseProcessor {
	private static final Logger logger = Logger
			.getLogger(ResponseProcessor.class.getName());

	public static TransferResponse unMarshal(String xml) {
		TransferResponse response = null;
		logger.info("--------------------------Unmarshal the xml which is :"
				+ xml);

		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(TransferResponse.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			if (xml != null) {
				response = (TransferResponse) jaxbUnmarshaller
						.unmarshal(new ByteArrayInputStream(xml.getBytes()));
			} else {
				logger.info("XML is NULL>>>>>>>>>>>>>>");
			}

			System.out.println(response);
			logger.info("New patch breakpoint>>>>>>>>");
		} catch (JAXBException e) {
			logger.info("ERROR OCCURED WHILE UNMARSHALLING XML" + e.toString());
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}
		logger.info("New patch breakpoint>>>>>> BEFORE RETURNING RESPONSE");
		return response;

	}

	public static String marshal(MultiPartyPaymentRequest request) {
		String xml = "";
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(TransferResponse.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			if (request != null) {
				// Marshal the employees list in console
				jaxbMarshaller.marshal(request, sw);
				xml = sw.toString();

			} else {
				logger.info("XML is NULL>>>>>>>>>>>>>>");
			}

			logger.info("New patch breakpoint>>>>>>>>");
		} catch (JAXBException e) {
			logger.info("ERROR OCCURED WHILE UNMARSHALLING XML" + e.toString());
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		}
		return xml;
	}

}
