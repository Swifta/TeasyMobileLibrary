package com.ng.mats.psa.mt.teasymobile.xmlprocessor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.util.StreamReaderDelegate;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;

import com.mobilemoney.services.mwallet.MPWalletServiceStub.MTransferResponseType;

public class ResponseProcessor {
	private static final Logger logger = Logger
			.getLogger(ResponseProcessor.class.getName());

	public static MTransferResponseType unMarshal(String xml) {
		MTransferResponseType response = null;
		logger.info("-------------------------->>>>>>>>>>Unmarshal the xml which is :"
				+ xml);

		try {

			byte[] byteArray = xml.getBytes("UTF-8");
			ByteArrayInputStream inputStream = new ByteArrayInputStream(
					byteArray);

			XMLInputFactory xif = XMLInputFactory.newInstance();
			XMLStreamReader xsr = xif.createXMLStreamReader(inputStream);
			xsr.nextTag(); // Advance to Envelope tag
			xsr.nextTag(); // Advance to Body tag
			xsr.nextTag(); // Advance to getNumberResponse tag

			xsr = new MyStreamReaderDelegate(xsr);

			/*
			 * String formattedXml =
			 * "<?xml version=\"1.0\" encoding=\"UTF-8\"?><TransferResponse xmlns=\"http://mobilemoney.com/services/mWallet\"><Status>Complete</Status><Amount>100</Amount><Fee>5</Fee><Currency>NGN</Currency><ResponseMessage>Internal server error</ResponseMessage></TransferResponse>"
			 * ;
			 * 
			 * byte[] byteArray = formattedXml.getBytes("UTF-8");
			 * ByteArrayInputStream inputStream = new ByteArrayInputStream(
			 * byteArray); XMLInputFactory inputFactory =
			 * XMLInputFactory.newInstance(); XMLStreamReader xsr = inputFactory
			 * .createXMLStreamReader(inputStream); xsr = new
			 * MyStreamReaderDelegate(xsr);
			 */

			// String formattedXml = getOuterXml(xsr);
			// System.out.println("+++++++++++++++++++++++++>>>>>>>>>>>>>"
			// + formattedXml);

			JAXBContext jaxbContext = JAXBContext
					.newInstance(TransferResponse.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			response = (MTransferResponseType) jaxbUnmarshaller.unmarshal(xsr);
			// if (formattedXml != null) {
			// response = (TransferResponse) jaxbUnmarshaller
			// .unmarshal(new ByteArrayInputStream(formattedXml
			// .getBytes("UTF-8")));
			// StringBuffer xmlStr = new StringBuffer(formattedXml);
			// response = (TransferResponse) jaxbUnmarshaller
			// .unmarshal(new StreamSource(new StringReader(xmlStr
			// .toString())));
			// } else {
			// logger.info("XML is NULL>>>>>>>>>>>>>>");
			// }

			// System.out.println(response.toString());
			logger.info("New patch breakpoint>>>>>>>>" + response);
		} catch (JAXBException e) {
			logger.info("ERROR OCCURED WHILE UNMARSHALLING XML" + e.toString());
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("New patch breakpoint>>>>>> BEFORE RETURNING RESPONSE");
		return response;

	}

	private static String getOuterXml(XMLStreamReader xmlr)
			throws TransformerConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {

		System.setProperty("javax.xml.transform.TransformerFactory",
				"com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		StringWriter stringWriter = new StringWriter();
		transformer.transform(new StAXSource(xmlr), new StreamResult(
				stringWriter));
		return stringWriter.toString();
	}

	public static String marshal(MultiPartyPaymentRequest request) {
		String xml = "";
		try {

			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

			JAXBContext jaxbContext = JAXBContext
					.newInstance(MultiPartyPaymentRequest.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();

			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			if (request != null) {
				// Marshal the employees list in console
				// jaxbMarshaller.marshal(request, sw);
				// xml = sw.toString();
				// logger.info("XML is redefined as>>>>>>>>>>>>>>" + xml2);

				jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				XMLStreamWriter xmlStreamWriter = xmlOutputFactory
						.createXMLStreamWriter(baos, (String) jaxbMarshaller
								.getProperty(Marshaller.JAXB_ENCODING));
				xmlStreamWriter.writeStartDocument((String) jaxbMarshaller
						.getProperty(Marshaller.JAXB_ENCODING), "1.0");
				jaxbMarshaller.marshal(request, xmlStreamWriter);
				xmlStreamWriter.writeEndDocument();
				xmlStreamWriter.close();
				xml = new String(baos.toByteArray());
				// logger.info("XML is redefined as>>>>>>>>>>>>>>" + xml);

			} else {
				logger.info("XML is NULL>>>>>>>>>>>>>>");
			}

			logger.info("New patch breakpoint>>>>>>>>");
		} catch (JAXBException e) {
			logger.info("ERROR OCCURED WHILE UNMARSHALLING XML" + e.toString());
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xml;
	}

	public static String marshalTransferResponse(TransferResponse request) {
		String xml = "";
		try {

			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

			JAXBContext jaxbContext = JAXBContext
					.newInstance(TransferResponse.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();

			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

			if (request != null) {
				// Marshal the employees list in console
				// jaxbMarshaller.marshal(request, sw);
				// xml = sw.toString();
				// logger.info("XML is redefined as>>>>>>>>>>>>>>" + xml2);

				jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				XMLStreamWriter xmlStreamWriter = xmlOutputFactory
						.createXMLStreamWriter(baos, (String) jaxbMarshaller
								.getProperty(Marshaller.JAXB_ENCODING));
				xmlStreamWriter.writeStartDocument((String) jaxbMarshaller
						.getProperty(Marshaller.JAXB_ENCODING), "1.0");
				jaxbMarshaller.marshal(request, xmlStreamWriter);
				xmlStreamWriter.writeEndDocument();
				xmlStreamWriter.close();
				xml = new String(baos.toByteArray());
				// logger.info("XML is redefined as>>>>>>>>>>>>>>" + xml);

			} else {
				logger.info("XML is NULL>>>>>>>>>>>>>>");
			}

			logger.info("New patch breakpoint>>>>>>>>");
		} catch (JAXBException e) {
			logger.info("ERROR OCCURED WHILE UNMARSHALLING XML" + e.toString());
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xml;
	}

	public static TransferResponse unMarshalTransferResponse(String xml) {
		TransferResponse response = null;
		logger.info("-------------------------->>>>>>>>>>THE TRANSFER RESPONSE the xml which is :"
				+ xml);

		try {

			byte[] byteArray = xml.getBytes("UTF-8");
			ByteArrayInputStream inputStream = new ByteArrayInputStream(
					byteArray);
			XMLInputFactory xif = XMLInputFactory.newInstance();
			XMLStreamReader xsr = xif.createXMLStreamReader(inputStream);
			xsr.nextTag();

			String formattedXml = getOuterXml(xsr);

			JAXBContext jaxbContext = JAXBContext
					.newInstance(TransferResponse.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			if (formattedXml != null) {
				response = (TransferResponse) jaxbUnmarshaller
						.unmarshal(new ByteArrayInputStream(formattedXml
								.getBytes("UTF-8")));
			} else {
				logger.info("XML is NULL>>>>>>>>>>>>>>");
			}

			logger.info("New patch breakpoint>>>>>>>>");
		} catch (JAXBException e) {
			logger.info("ERROR OCCURED WHILE UNMARSHALLING XML" + e.toString());
			logger.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) { // TODO Auto-generated
														// catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("New patch breakpoint>>>>>> BEFORE RETURNING RESPONSE");
		return response;

	}

	private static class MyStreamReaderDelegate extends StreamReaderDelegate {

		public MyStreamReaderDelegate(XMLStreamReader xsr) {
			super(xsr);
		}

		@Override
		public String getAttributeLocalName(int index) {
			return super.getAttributeLocalName(index).toLowerCase().intern();
		}

		@Override
		public String getLocalName() {
			return super.getLocalName().toLowerCase().intern();
		}

	}

	public static void main(String args[]) {
		unMarshal("");
	}
}
