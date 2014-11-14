package com.mobilemoney.services.mwallet;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.axis2.transport.http.HTTPConstants;

import com.mobilemoney.services.mwallet.MPWalletServiceStub.AccountNumber;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.CreditRequest;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.CurrencyCode;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.DebitRequest;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MTransferRequestType;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MTransferResponseType;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MmTransferCode;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.PIN;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.RequestReference;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.TransferResponse;
import com.ng.mats.psa.mt.teasymobile.model.MoneyTransfer;

public class TeasyMobileClient {
	private static final Logger logger = Logger.getLogger(TeasyMobileClient.class
			.getName());
	private MPWalletServiceStub mpWalletServiceStub = null;

	// EndpointReference fundgateURl = null;

	// String fundgateURl = "";

	// private static final String wso2appserverHome =
	// "/usr/server/wso2/wso2setup/wso2esb-4.8.1";

	public TeasyMobileClient() throws Exception {

		mpWalletServiceStub = new MPWalletServiceStub();

	}

	// @SuppressWarnings("deprecation")
	// public void configureSecurity() throws UnknownHostException, IOException
	// {
	// String clientSSLStore = wso2appserverHome + File.separator
	// + "repository" + File.separator + "resources" + File.separator
	// + "security" + File.separator + "client-truststore.jks";
	//
	// // wso2carbon.jks client-truststore.jks
	//
	// System.getProperties().remove("javax.net.ssl.trustStore");
	// System.getProperties().remove("javax.net.ssl.trustStoreType");
	// System.getProperties().remove("javax.net.ssl.trustStorePassword");
	//
	// System.setProperty("javax.net.ssl.trustStore", clientSSLStore);
	// System.setProperty("javax.net.ssl.trustStoreType", "JKS");
	// System.setProperty("javax.net.ssl.trustStorePassword", "wso2carbon");
	// System.setProperty("javax.net.debug", "ssl");
	// // System.setProperty("https.protocols", "SSLv3");
	//
	// Protocol myProtocolHandler = new Protocol("https",
	// new SSL3ProtocolSocketFactory(), 443);
	//
	// fundgateStub
	// ._getServiceClient()
	// .getOptions()
	// .setProperty(HTTPConstants.CUSTOM_PROTOCOL_HANDLER,
	// myProtocolHandler);
	//
	// fundgateStub._getServiceClient().getOptions()
	// .setProperty(HTTPConstants.CHUNKED, "false");
	//
	// }

	public MTransferResponseType doCashout(MoneyTransfer moneyTransfer)
			throws Exception {
		System.out.println("...do Cash out...");
		System.out.println("-----------------------------doing cashout.....");

		if (mpWalletServiceStub == null) {
			System.out.println("Fund stub is not available");
			System.out.println("---------------------------Fund stub is not available");
		}

		DebitRequest debitRequest = new DebitRequest();
		System.out.println("---------------------------Debit request");
		MTransferRequestType mtrTransferRequestType = new MTransferRequestType();
		AccountNumber accountNumber = new AccountNumber();
		System.out.println("---------------------------Account number");
		accountNumber.setAccountNumber(moneyTransfer.getReceiver()); // 2348171000157
		mtrTransferRequestType.setAccountNumber(accountNumber);
		PIN pin = new PIN();
		System.out.println("---------------------------PIN");
		pin.setPIN(moneyTransfer.getTeasypin()); // 7005
		mtrTransferRequestType.setAccountPIN(pin);
		mtrTransferRequestType.setAmount(moneyTransfer.getAmount().intValue());
		CurrencyCode currencyCode = new CurrencyCode();
		System.out.println("---------------------------Currency code");
		currencyCode.setCurrencyCode("NGN");
		mtrTransferRequestType.setCurrency(currencyCode);
		MmTransferCode mmTransferCode = new MmTransferCode();
		System.out.println("---------------------------MmTransfer code");
		mmTransferCode.setMmTransferCode("T3ASYT3ST");
		mtrTransferRequestType.setOriginCode(mmTransferCode);
		RequestReference requestReference = new RequestReference();
		System.out.println("---------------------------Request reference");
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("YYYYMMddHHmmSSS");
		requestReference.setRequestReference(ft.format(dNow));
		mtrTransferRequestType.setRequestReference(requestReference);

		debitRequest.setDebitRequest(mtrTransferRequestType);
		System.out.println("---------------------------PIN");
		// AdditionalHeader additionalHeader = new AdditionalHeader();
		// MHeader mhHeader = new MHeader();
		// mhHeader.setUsername("teasy");
		// mhHeader.setPassword("t3apwd");
		//
		// additionalHeader.setAdditionalHeader(mhHeader);

		// SOAPFactory soapFactory = OMAbstractFactory.getSOAP11Factory();
		// SOAPEnvelope soapEnvelope = soapFactory.getDefaultEnvelope();
		//
		// OMElement AdditionalHeader = soapFactory.
		// createOMElement(new
		// QName("http://mobilemoney.com/services/mWallet","AdditionalHeader","mwal"));
		//
		// OMElement username = soapFactory.createOMElement(new
		// QName("http://mobilemoney.com/services/mWallet","username"));
		// OMText usernamevalue = soapFactory.createOMText(username,"");
		// username.addChild(usernamevalue);
		// OMElement password = soapFactory.createOMElement(new
		// QName("http://mobilemoney.com/services/mWallet","password"));
		// OMText passwordvalue = soapFactory.createOMText(password,"");
		// password.addChild(passwordvalue);
		// AdditionalHeader.addChild(username);
		// AdditionalHeader.addChild(password);
		//
		// soapEnvelope.addChild(AdditionalHeader);

		// mpWalletServiceStub._getServiceClient().removeHeaders();

		mpWalletServiceStub._getServiceClient().addHeader(
				ClientUtil.getHeaderOMElement());
		mpWalletServiceStub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);

		TransferResponse transferResponse = mpWalletServiceStub
				.debitRequest(debitRequest);
		System.out.println("---------------------------Transfer Response");
		return transferResponse.getTransferResponse();

	}

	public MTransferResponseType doCashIn(MoneyTransfer moneyTransfer)
			throws Exception {
		System.out.println("---------------------------Inside the do cashin");
		System.out.println("...do Cash In...");

		if (mpWalletServiceStub == null) {
			System.out.println("---------------------------Stub is null");
			System.out.println("Fund stub is not available");
		}else{
			System.out.println("---------------------------Stub is not null");
		}

		CreditRequest creditRequest = new CreditRequest();

		MTransferRequestType mtrTransferRequestType = new MTransferRequestType();
		AccountNumber accountNumber = new AccountNumber();
		accountNumber.setAccountNumber(moneyTransfer.getReceiver()); // 2348171000157
		mtrTransferRequestType.setAccountNumber(accountNumber);
		PIN pin = new PIN();
		System.out.println("after instantiation of PIN");
		pin.setPIN(moneyTransfer.getTeasypin()); // 7005
		mtrTransferRequestType.setAccountPIN(pin);
		mtrTransferRequestType.setAmount(moneyTransfer.getAmount().intValue());
		CurrencyCode currencyCode = new CurrencyCode();
		System.out.println("after instantiation of CurrencyCode");
		currencyCode.setCurrencyCode("NGN");
		mtrTransferRequestType.setCurrency(currencyCode);
		MmTransferCode mmTransferCode = new MmTransferCode();
		System.out.println("MmtransferCode is instantiated");
		mmTransferCode.setMmTransferCode("T3ASYT3ST");
		mtrTransferRequestType.setOriginCode(mmTransferCode);
		RequestReference requestReference = new RequestReference();
		System.out.println("Instantiation of RequestReference");
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("YYYYMMddHHmmSSS");
		requestReference.setRequestReference(ft.format(dNow));
		mtrTransferRequestType.setRequestReference(requestReference);

		System.out.println("After set request reference");
		creditRequest.setCreditRequest(mtrTransferRequestType);

		// AdditionalHeader additionalHeader = new AdditionalHeader();
		// MHeader mhHeader = new MHeader();
		// mhHeader.setUsername("teasy");
		// mhHeader.setPassword("t3apwd");
		//
		// additionalHeader.setAdditionalHeader(mhHeader);

		// SOAPFactory soapFactory = OMAbstractFactory.getSOAP11Factory();
		// SOAPEnvelope soapEnvelope = soapFactory.getDefaultEnvelope();
		//
		// OMElement AdditionalHeader = soapFactory.
		// createOMElement(new
		// QName("http://mobilemoney.com/services/mWallet","AdditionalHeader","mwal"));
		//
		// OMElement username = soapFactory.createOMElement(new
		// QName("http://mobilemoney.com/services/mWallet","username"));
		// OMText usernamevalue = soapFactory.createOMText(username,"");
		// username.addChild(usernamevalue);
		// OMElement password = soapFactory.createOMElement(new
		// QName("http://mobilemoney.com/services/mWallet","password"));
		// OMText passwordvalue = soapFactory.createOMText(password,"");
		// password.addChild(passwordvalue);
		// AdditionalHeader.addChild(username);
		// AdditionalHeader.addChild(password);
		//
		// soapEnvelope.addChild(AdditionalHeader);

		// mpWalletServiceStub._getServiceClient().removeHeaders();

		mpWalletServiceStub._getServiceClient().addHeader(
				ClientUtil.getHeaderOMElement());
		mpWalletServiceStub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);

		TransferResponse transferResponse = mpWalletServiceStub
				.creditRequest(creditRequest);
		System.out.println("After return of transfer response");
		return transferResponse.getTransferResponse();

	}

	// @SuppressWarnings({ "unused", "static-access" })
	// private static String Encrypt(String plainText, String key)
	// throws NoSuchAlgorithmException, NoSuchPaddingException,
	// InvalidKeyException, IllegalBlockSizeException,
	// BadPaddingException, UnsupportedEncodingException {
	// SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	//
	// // Instantiate the cipher
	// Cipher cipher = Cipher.getInstance("AES");
	// cipher.init(Cipher.ENCRYPT_MODE, keySpec);
	//
	// byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
	//
	// return new Base64().encodeBase64String(encryptedTextBytes);
	// }

	public static void main(String args[]) throws Exception {
		TeasyMobileClient teasyMobileClient = new TeasyMobileClient();
		MoneyTransfer moneyTransfer = new MoneyTransfer("2348171000157",
				new BigDecimal(100), "dada", "7005");

		MTransferResponseType response = teasyMobileClient
				.doCashout(moneyTransfer);

		System.out.println("Status: " + response.getStatus());

		System.out.println("ResponseMessage: " + response.getResponseMessage());

	}

}
