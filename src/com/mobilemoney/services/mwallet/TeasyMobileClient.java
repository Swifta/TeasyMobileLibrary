package com.mobilemoney.services.mwallet;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.transport.http.HTTPConstants;

import com.mobilemoney.services.mwallet.MPWalletServiceStub.AccountNumber;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.AdditionalHeader;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.AirtimeTopupRequest;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.BalanceGetRequest;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.BalanceGetResponse;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.CashInRequest;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.CashOutRequest;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.CurrencyCode;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MAirtimeTopupRequestType;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MBalanceResponse;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MBasicRequestType;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MCashInRequestType;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MCashOutRequestType;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MHeader;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MPingResponse;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MTransactionQuery;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MTransferResponseType;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MTxnReverseRequest;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MTxnReverseResponse;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.MmTransferCode;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.PIN;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.PingRequest;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.PingResponse;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.RequestReference;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.ReverseTxnRequest;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.ReverseTxnResponse;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.TransactionQueryRequest;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.TransactionQueryResponse;
import com.mobilemoney.services.mwallet.MPWalletServiceStub.TransferResponse;
import com.multibank.webservice.WalletMultiBankServicesStub;
import com.multibank.webservice.WalletMultiBankServicesStub.PostMultiPartyPaymentRequest;
import com.multibank.webservice.WalletMultiBankServicesStub.PostMultiPartyPaymentRequestResponse;
import com.ng.mats.psa.mt.teasymobile.model.BatchNumber;
import com.ng.mats.psa.mt.teasymobile.model.MoneyTransfer;
import com.ng.mats.psa.mt.teasymobile.model.NumberOfRecords;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.AccountIdentificationName;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.AccountIdentificationNumber;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.Amount;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.AuthenticationDetails;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.CreditAccountNo;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.Currency;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.DebitAccountNo;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.DebitAccountPIN;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.DebitAmount;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.DebitFee;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.DebitRequest;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.DestinationCode;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.FTAmount;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.FTBulkCreditRequest;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.Fee;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.Header;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.MultiPartyPaymentRequest;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.Narration;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.OriginCode;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.Password;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.PaymentReference;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.RecID;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.Record;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.ResponseProcessor;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.Responsemessage;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.Status;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.Username;
import com.ng.mats.psa.mt.teasymobile.xmlprocessor.WalletDebitCreditInfo;

public class TeasyMobileClient {
	private static final Logger logger = Logger
			.getLogger(TeasyMobileClient.class.getName());
	private static MoneyTransfer moneyTransfer = null;
	private MPWalletServiceStub mpWalletServiceStub = null;
	WalletMultiBankServicesStub walletBankServiceStub = null;
	ResponseProcessor rp = null;

	public TeasyMobileClient(MoneyTransfer moneyTransfer) throws Exception {

		mpWalletServiceStub = new MPWalletServiceStub(moneyTransfer.getUrl());
		walletBankServiceStub = new WalletMultiBankServicesStub();
		rp = new ResponseProcessor();

	}

	public String generatePaymentString() {
		String batchNumberValue = "20150227210941001001", numberOfRecordsValue = "1", accountIdentificationNameValue = "Jude Smith", accountIdentificationNumberValue = "9998887771", destinationCodeValue = "999", fTAmountValue = "200000", narrationValue = "Transfer to Bank", paymentReferenceValue = "201502271111129", recIDValue = "000001", originCodeValue = "CINFORES", usernameValue = "cinfores", passwordValue = "apipass", creditAccountNoValue = "2348033919555", debitAccountNoValue = "2348171000157", debitAccountPINValue = "7005", debitAmountValue = "200000", debitFeeValue = "15000";
		MoneyTransfer moneyTransfer = new MoneyTransfer();
		moneyTransfer.setBatchNumberValue(batchNumberValue);
		moneyTransfer.setNumberOfRecordsValue(numberOfRecordsValue);
		moneyTransfer
				.setAccountIdentificationNameValue(accountIdentificationNameValue);
		moneyTransfer
				.setAccountIdentificationNumberValue(accountIdentificationNumberValue);
		moneyTransfer.setDestinationCodeValue(destinationCodeValue);
		moneyTransfer.setfTAmountValue(fTAmountValue);
		moneyTransfer.setNarrationValue(narrationValue);
		moneyTransfer.setPaymentReferenceValue(paymentReferenceValue);
		moneyTransfer.setRecIDValue(recIDValue);
		moneyTransfer.setOriginCodeValue(originCodeValue);
		moneyTransfer.setUsernameValue(usernameValue);
		moneyTransfer.setPasswordValue(passwordValue);
		moneyTransfer.setCreditAccountNoValue(creditAccountNoValue);
		moneyTransfer.setDebitAccountNoValue(debitAccountNoValue);
		moneyTransfer.setDebitAccountPINValue(debitAccountPINValue);
		moneyTransfer.setDebitAmountValue(debitAmountValue);
		moneyTransfer.setDebitFeeValue(debitFeeValue);
		return generatePaymentString(moneyTransfer);
	}

	public String generatePaymentString(MoneyTransfer moneyTransfer) {
		MultiPartyPaymentRequest request = new MultiPartyPaymentRequest();

		FTBulkCreditRequest fTBulkCreditRequest = new FTBulkCreditRequest();
		Header header = new Header();
		Record record = new Record();
		BatchNumber batchNumber = new BatchNumber();
		NumberOfRecords numberOfRecords = new NumberOfRecords();

		numberOfRecords.setValue(moneyTransfer.getNumberOfRecordsValue());
		batchNumber.setValue(moneyTransfer.getBatchNumberValue());

		header.setBatchNumber(batchNumber);
		header.setNumberOfRecords(numberOfRecords);

		AccountIdentificationName accountIdentificationName = new AccountIdentificationName();
		AccountIdentificationNumber accountIdentificationNumber = new AccountIdentificationNumber();
		DestinationCode destinationCode = new DestinationCode();
		FTAmount fTAmount = new FTAmount();
		Narration narration = new Narration();
		PaymentReference paymentReference = new PaymentReference();
		RecID recID = new RecID();

		accountIdentificationName.setValue(moneyTransfer
				.getAccountIdentificationNameValue());
		accountIdentificationNumber.setValue(moneyTransfer
				.getAccountIdentificationNumberValue());
		destinationCode.setValue(moneyTransfer.getDestinationCodeValue());
		fTAmount.setValue(moneyTransfer.getfTAmountValue());
		narration.setValue(moneyTransfer.getNarrationValue());
		paymentReference.setValue(moneyTransfer.getPaymentReferenceValue());
		recID.setValue(moneyTransfer.getRecIDValue());

		record.setAccountIdentificationName(accountIdentificationName);
		record.setAccountIdentificationNumber(accountIdentificationNumber);
		record.setDestinationCode(destinationCode);
		record.setfTAmount(fTAmount);
		record.setNarration(narration);
		record.setPaymentReference(paymentReference);
		record.setRecID(recID);

		fTBulkCreditRequest.setHeader(header);
		fTBulkCreditRequest.setRecord(record);

		request.setfTBulkCreditRequest(fTBulkCreditRequest);

		WalletDebitCreditInfo walletDebitCreditInfo = new WalletDebitCreditInfo();
		AuthenticationDetails authenticationDetails = new AuthenticationDetails();

		OriginCode originCode = new OriginCode();
		originCode.setValue(moneyTransfer.getOriginCodeValue());
		Password password = new Password();
		password.setValue(moneyTransfer.getPasswordValue());
		Username username = new Username();
		username.setValue(moneyTransfer.getUsernameValue());

		authenticationDetails.setOriginCode(originCode);
		authenticationDetails.setPassword(password);
		authenticationDetails.setUsername(username);

		walletDebitCreditInfo.setAuthenticationDetails(authenticationDetails);
		com.ng.mats.psa.mt.teasymobile.xmlprocessor.CreditRequest creditRequest = new com.ng.mats.psa.mt.teasymobile.xmlprocessor.CreditRequest();
		CreditAccountNo creditAccountNo = new CreditAccountNo();
		DebitAccountNo debitAccountNo = new DebitAccountNo();
		DebitAccountPIN debitAccountPIN = new DebitAccountPIN();
		DebitAmount debitAmount = new DebitAmount();
		DebitFee debitFee = new DebitFee();

		creditAccountNo.setValue(moneyTransfer.getCreditAccountNoValue());
		debitAccountNo.setValue(moneyTransfer.getDebitAccountNoValue());
		debitAccountPIN.setValue(moneyTransfer.getDebitAccountPINValue());
		debitAmount.setValue(moneyTransfer.getDebitAmountValue());
		debitFee.setValue(moneyTransfer.getDebitFeeValue());

		creditRequest.setCreditAccountNo(creditAccountNo);
		DebitRequest debitRequest = new DebitRequest();
		debitRequest.setDebitAccountNo(debitAccountNo);
		debitRequest.setDebitAccountPIN(debitAccountPIN);
		debitRequest.setDebitAmount(debitAmount);
		debitRequest.setDebitFee(debitFee);
		walletDebitCreditInfo.setCreditRequest(creditRequest);
		walletDebitCreditInfo.setDebitRequest(debitRequest);

		request.setWalletDebitCreditInfo(walletDebitCreditInfo);
		return rp.marshal(request);
	}

	public MTransferResponseType walletToBank(MoneyTransfer moneyTransfer) {
		// walletBankServiceStub._getServiceClient().addHeader(
		// ClientUtil.getHeaderOMElement());
		// walletBankServiceStub._getServiceClient().getOptions()
		// .setProperty(HTTPConstants.CHUNKED, false);

		PostMultiPartyPaymentRequest postMultiPartyPaymentRequest = new PostMultiPartyPaymentRequest();
		PostMultiPartyPaymentRequestResponse response = new PostMultiPartyPaymentRequestResponse();

		String requestFormat = "";
		// MultiPartyPaymentRequest multiPartyPaymentRequest = new
		// MultiPartyPaymentRequest();
		// multiPartyPaymentRequest
		// generateResponse();
		requestFormat = generatePaymentString();
		logger.info("<<<<<<<<<THE XML GENERATED IS >>>>>>>>>>>>\n"
				+ requestFormat);
		postMultiPartyPaymentRequest.setMultipartyPaymentRequest(requestFormat);
		try {
			response = walletBankServiceStub
					.postMultiPartyPaymentRequest(postMultiPartyPaymentRequest);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("THIS IS THE RESPONSE FROM THE WEBSERVICE CALL>>>>>"
				+ response);
		logger.info("THIS IS THE RESPONSE FROM THE WEBSERVICE CALL TO STRING>>>>>"
				+ response.toString());
		logger.info("THIS IS THE RESPONSE FROM THE WEBSERVICE CALL RETURN>>>>>"
				+ response.get_return());
		return rp.unMarshal(response.get_return());

		// mpWalletServiceStub.
	}

	public MTransferResponseType transactionQuery(MoneyTransfer moneyTransfer)
			throws RemoteException {
		mpWalletServiceStub._getServiceClient().addHeader(
				ClientUtil.getHeaderOMElement(moneyTransfer.getUsername(),
						moneyTransfer.getPassword()));
		mpWalletServiceStub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);
		TransactionQueryRequest transactionQueryRequest = new TransactionQueryRequest();
		MTransactionQuery mTransRequest = new MTransactionQuery();
		AccountNumber accountNumber = new AccountNumber();
		logger.info("---------------------------Account number>>>>"
				+ moneyTransfer.getSender());
		accountNumber.setAccountNumber(moneyTransfer.getSender());
		mTransRequest.setAccountNumber(accountNumber);
		RequestReference reference = new RequestReference();
		reference.setRequestReference(moneyTransfer.getTeasyrequestreference());
		mTransRequest.setRequestReference(reference);
		transactionQueryRequest.setTransactionQueryRequest(mTransRequest);
		TransactionQueryResponse transactionQueryResponse = mpWalletServiceStub
				.transactionQueryRequest(transactionQueryRequest, null);
		MTransferResponseType response = transactionQueryResponse
				.getTransactionQueryResponse();
		return response;
	}

	public MTransferResponseType airtimeSales(MoneyTransfer moneyTransfer)
			throws RemoteException {
		mpWalletServiceStub._getServiceClient().addHeader(
				ClientUtil.getHeaderOMElement(moneyTransfer.getUsername(),
						moneyTransfer.getPassword()));
		mpWalletServiceStub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);
		AirtimeTopupRequest airtimeTopupRequest = new AirtimeTopupRequest();
		MAirtimeTopupRequestType airtimeRequest = new MAirtimeTopupRequestType();
		AccountNumber accountNumber = new AccountNumber();
		logger.info("---------------------------Account number>>>>"
				+ moneyTransfer.getSender());
		accountNumber.setAccountNumber(moneyTransfer.getSender()); // 2348171000157
		airtimeRequest.setAccountNumber(accountNumber);
		PIN pin = new PIN();
		logger.info("---------------------------PIN>>>"
				+ moneyTransfer.getTeasypin());
		pin.setPIN(moneyTransfer.getTeasypin()); // 7005
		airtimeRequest.setAccountPIN(pin);
		airtimeRequest.setAmount(moneyTransfer.getAmount().intValue());
		logger.info("---------------------------Amount>>>>"
				+ moneyTransfer.getAmount());
		CurrencyCode currencyCode = new CurrencyCode();
		logger.info("---------------------------Currency code");
		currencyCode.setCurrencyCode("NGN");
		airtimeRequest.setCurrency(currencyCode);
		MmTransferCode mmTransferCode = new MmTransferCode();
		logger.info("---------------------------MmTransfer code");
		mmTransferCode.setMmTransferCode("T3ASYT3ST");
		airtimeRequest.setOriginCode(mmTransferCode);
		RequestReference requestReference = new RequestReference();
		logger.info("---------------------------Request reference");
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("YYYYMMddHHmmSSS");
		requestReference.setRequestReference(ft.format(dNow));
		airtimeRequest.setRequestReference(requestReference);
		mmTransferCode = new MmTransferCode();
		mmTransferCode.setMmTransferCode("KAKA");
		airtimeRequest.setDestinationCode(mmTransferCode);
		accountNumber = new AccountNumber();
		logger.info("---------------------------Account number>>>>"
				+ moneyTransfer.getReceiver());
		accountNumber.setAccountNumber(moneyTransfer.getReceiver());
		airtimeRequest.setNumberToTopup(accountNumber);

		airtimeTopupRequest.setAirtimeTopupRequest(airtimeRequest);
		TransferResponse transferResponse = mpWalletServiceStub
				.airtimeTopupRequest(airtimeTopupRequest, null);
		MTransferResponseType mTransferResponse = transferResponse
				.getTransferResponse();
		return mTransferResponse;
	}

	public MTxnReverseResponse reverseTransaction(MoneyTransfer moneyTransfer) {
		mpWalletServiceStub._getServiceClient().addHeader(
				ClientUtil.getHeaderOMElement(moneyTransfer.getUsername(),
						moneyTransfer.getPassword()));
		mpWalletServiceStub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);
		ReverseTxnRequest reverseTxnRequest = new ReverseTxnRequest();
		MTxnReverseRequest mTxnReverseRequest = new MTxnReverseRequest();

		AccountNumber accountNumber = new AccountNumber();
		logger.info("---------------------------Account number>>>>"
				+ moneyTransfer.getSender());
		accountNumber.setAccountNumber(moneyTransfer.getSender());

		RequestReference reference = new RequestReference();
		reference.setRequestReference(moneyTransfer.getTeasyrequestreference());

		mTxnReverseRequest.setAccountNumber(accountNumber);
		mTxnReverseRequest.setRequestReference(reference);
		reverseTxnRequest.setReverseTxnRequest(mTxnReverseRequest);
		ReverseTxnResponse response = null;
		MTxnReverseResponse mTransferResponse = null;
		try {
			response = mpWalletServiceStub.reverseTxnRequest(reverseTxnRequest,
					null);
			mTransferResponse = response.getReverseTxnResponse();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mTransferResponse;
	}

	public MPingResponse pingRequest(MoneyTransfer moneyTransfer) {
		String param = "";
		mpWalletServiceStub._getServiceClient().addHeader(
				ClientUtil.getHeaderOMElement(moneyTransfer.getUsername(),
						moneyTransfer.getPassword()));
		mpWalletServiceStub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);
		PingRequest pingRequest = new PingRequest();
		pingRequest.setPingRequest(moneyTransfer.getPingRequestParam());

		PingResponse pingResponse = null;
		MPingResponse mPingResponse = null;
		try {
			pingResponse = mpWalletServiceStub.pingRequest(pingRequest, null);
			pingResponse.getPingResponse();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mPingResponse;

	}

	public MBalanceResponse getBalance(MoneyTransfer moneyTransfer)
			throws Exception {
		mpWalletServiceStub._getServiceClient().addHeader(
				ClientUtil.getHeaderOMElement(moneyTransfer.getUsername(),
						moneyTransfer.getPassword()));
		mpWalletServiceStub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);
		BalanceGetRequest balanceGetRequest = new BalanceGetRequest();
		MBasicRequestType param = new MBasicRequestType();
		AccountNumber accountNumber = new AccountNumber();
		accountNumber.setAccountNumber(moneyTransfer.getSender());
		param.setAccountNumber(accountNumber);

		PIN pin = new PIN();
		pin.setPIN(moneyTransfer.getTeasypin());

		param.setAccountPIN(pin);
		balanceGetRequest.setBalanceGetRequest(param);

		BalanceGetResponse balanceGetResponse = mpWalletServiceStub
				.balanceGetRequest(balanceGetRequest, null);
		// mpWalletServiceStub.
		logger.info("---------------------------Transfer Response");
		return balanceGetResponse.getBalanceGetResponse();

	}

	public AdditionalHeader getAdditionalHeader(String username, String password) {
		AdditionalHeader additionalHeader = new AdditionalHeader();
		MHeader mHeaderParam = new MHeader();
		OMElement loginCredentials = ClientUtil.getHeaderOMElement(username,
				password), contentCredentials = null;
		Iterator<OMElement> oem = loginCredentials.getChildElements();
		logger.info("------------------" + loginCredentials.toString());
		while (oem.hasNext()) {
			contentCredentials = oem.next();
			logger.info("------------------"
					+ contentCredentials.toString()
					+ "----------"
					+ contentCredentials.getText()
					+ "------------"
					+ contentCredentials.getLocalName()
					+ "-------"
					+ contentCredentials.getAttributeValue(contentCredentials
							.getQName()));
			if (contentCredentials.getLocalName().equalsIgnoreCase("username")) {
				mHeaderParam.setUsername(contentCredentials.getText());
			} else if (contentCredentials.getLocalName().equalsIgnoreCase(
					"password")) {
				mHeaderParam.setPassword(contentCredentials.getText());
			}
		}
		logger.info("<<<<<<<<<<<<<THE PARAMETER HEADERS ARE >>>>>>>>>>"
				+ mHeaderParam.getUsername() + " :::::Password:::::"
				+ mHeaderParam.getPassword());
		additionalHeader.setAdditionalHeader(mHeaderParam);
		return additionalHeader;
	}

	public MTransferResponseType doCashout(MoneyTransfer moneyTransfer)
			throws Exception {
		logger.info("...do Cash out...");
		logger.info("-----------------------------doing cashout.....");

		if (mpWalletServiceStub == null) {
			logger.info("Fund stub is not available");
			logger.info("---------------------------Fund stub is not available");
		}
		int amount = moneyTransfer.getAmount().intValue() * 100;

		// DebitRequest debitRequest = new DebitRequest();
		logger.info("---------------------------Debit request");
		// MTransferRequestType mtrTransferRequestType = new
		// MTransferRequestType();
		AccountNumber accountNumber = new AccountNumber(), merchantNumber = new AccountNumber();

		logger.info("---------------------------Account number>>>>"
				+ moneyTransfer.getReceiver());
		accountNumber.setAccountNumber(moneyTransfer.getReceiver()); // 2348171000157

		logger.info("---------------------------Merchant number>>>>"
				+ moneyTransfer.getSender());
		merchantNumber.setAccountNumber(moneyTransfer.getSender());
		// mtrTransferRequestType.setAccountNumber(accountNumber);
		PIN pin = new PIN();
		logger.info("---------------------------PIN>>>"
				+ moneyTransfer.getTeasypin());
		pin.setPIN(moneyTransfer.getTeasypin()); // 7005
		// mtrTransferRequestType.setAccountPIN(pin);
		// mtrTransferRequestType.setAmount(amount);
		logger.info("---------------------------Amount>>>>" + amount);
		CurrencyCode currencyCode = new CurrencyCode();

		currencyCode.setCurrencyCode("NGN");
		// mtrTransferRequestType.setCurrency(currencyCode);
		logger.info("---------------------------Currency code::"
				+ currencyCode.getCurrencyCode());
		MmTransferCode mmTransferCode = new MmTransferCode();
		mmTransferCode.setMmTransferCode("T3ASYT3ST");
		logger.info("---------------------------MmTransfer code::"
				+ mmTransferCode.getMmTransferCode());
		// mtrTransferRequestType.setOriginCode(mmTransferCode);
		RequestReference requestReference = new RequestReference();

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("YYYYMMddHHmmSSS");
		requestReference.setRequestReference(ft.format(dNow));
		// mtrTransferRequestType.setRequestReference(requestReference);
		logger.info("---------------------------Request reference:"
				+ requestReference.getRequestReference());
		// debitRequest.setDebitRequest(mtrTransferRequestType);
		logger.info("---------------------------PIN");

		mpWalletServiceStub._getServiceClient().addHeader(
				ClientUtil.getHeaderOMElement(moneyTransfer.getUsername(),
						moneyTransfer.getPassword()));
		mpWalletServiceStub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);

		CashOutRequest cashOutRequest = new CashOutRequest();
		MCashOutRequestType mCashOutRequestType = new MCashOutRequestType();
		mCashOutRequestType.setAccountNumber(accountNumber);
		mCashOutRequestType.setAccountPIN(pin);
		mCashOutRequestType.setAmount(amount);
		mCashOutRequestType.setCurrency(currencyCode);
		mCashOutRequestType.setMerchantNumber(merchantNumber);
		mCashOutRequestType.setOriginCode(mmTransferCode);
		mCashOutRequestType.setRequestReference(requestReference);
		cashOutRequest.setCashOutRequest(mCashOutRequestType);

		TransferResponse transferResponse = mpWalletServiceStub.cashOutRequest(
				cashOutRequest, null);
		// TransferResponse transferResponse = mpWalletServiceStub.debitRequest(
		// debitRequest, getAdditionalHeader());
		logger.info("---------------------------Transfer Response");
		return transferResponse.getTransferResponse();

	}

	public MTransferResponseType doCashIn(MoneyTransfer moneyTransfer)
			throws Exception {
		logger.info("---------------------------Inside the do cashin");
		logger.info("...do Cash In...");

		if (mpWalletServiceStub == null) {
			logger.info("---------------------------Stub is null");
			logger.info("Fund stub is not available");
		} else {
			logger.info("---------------------------Stub is not null");
		}

		// CreditRequest creditRequest = new CreditRequest();

		int amount = moneyTransfer.getAmount().intValue() * 100;

		// MTransferRequestType mtrTransferRequestType = new
		// MTransferRequestType();

		AccountNumber accountNumber = new AccountNumber(), merchantNumber = new AccountNumber();
		accountNumber.setAccountNumber(moneyTransfer.getReceiver()); // 2348171000157
		merchantNumber.setAccountNumber(moneyTransfer.getSender());
		// mtrTransferRequestType.setAccountNumber(accountNumber);
		PIN pin = new PIN();
		logger.info("after instantiation of PIN");
		// moneyTransfer.setTeasypin("");
		pin.setPIN(moneyTransfer.getTeasypin()); // 7005

		// mtrTransferRequestType.setAccountPIN(pin);
		// mtrTransferRequestType.setAmount(amount);
		CurrencyCode currencyCode = new CurrencyCode();
		logger.info("after instantiation of CurrencyCode");
		currencyCode.setCurrencyCode("NGN");
		// mtrTransferRequestType.setCurrency(currencyCode);
		MmTransferCode mmTransferCode = new MmTransferCode();
		logger.info("MmtransferCode is instantiated");
		mmTransferCode.setMmTransferCode("T3ASYT3ST");
		// mtrTransferRequestType.setOriginCode(mmTransferCode);
		RequestReference requestReference = new RequestReference();
		logger.info("Instantiation of RequestReference");
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("YYYYMMddHHmmSSS");
		requestReference.setRequestReference(ft.format(dNow));
		// mtrTransferRequestType.setRequestReference(requestReference);
		logger.info("After set request reference");
		// creditRequest.setCreditRequest(mtrTransferRequestType);

		CashInRequest cashInRequest = new CashInRequest();
		MCashInRequestType mCashInRequestType = new MCashInRequestType();
		mCashInRequestType.setAccountNumber(accountNumber);
		mCashInRequestType.setAmount(amount);
		mCashInRequestType.setCurrency(currencyCode);
		mCashInRequestType.setMerchantNumber(merchantNumber);
		mCashInRequestType.setOriginCode(mmTransferCode);
		mCashInRequestType.setRequestReference(requestReference);

		cashInRequest.setCashInRequest(mCashInRequestType);
		mpWalletServiceStub._getServiceClient().addHeader(
				ClientUtil.getHeaderOMElement(moneyTransfer.getUsername(),
						moneyTransfer.getPassword()));
		mpWalletServiceStub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);
		TransferResponse transferResponse = mpWalletServiceStub.cashInRequest(
				cashInRequest, null);

		// TransferResponse transferResponse =
		// mpWalletServiceStub.creditRequest(
		// creditRequest, getAdditionalHeader());
		logger.info("After return of transfer response");
		return transferResponse.getTransferResponse();

	}

	public static void main(String args[]) throws Exception {
		moneyTransfer = new TeasyMobilePropertyValues().getPropertyValues();
		TeasyMobileClient teasyMobileClient = new TeasyMobileClient(
				moneyTransfer);

		MTransferResponseType response = teasyMobileClient
		// .doCashIn(moneyTransfer);
				.doCashout(moneyTransfer);
		// .walletToBank(moneyTransfer);
		// MBalanceResponse response =
		// teasyMobileClient.getBalance(moneyTransfer);
		// logger.info("Status: " + response.getStatus());

		logger.info("ResponseMessage: " + response.getResponseMessage());
		logger.info("ResponseMessage: " + response.getAmount());
		logger.info("Transactionid: " + response.getTransactionId());

		// airtimesales

		// logger.info("Amount: " + response.getAmount());
		// logger.info("CurrencyCode: " + response.getCurrency());
		// logger.info("Fee: " + response.getFee());
		// logger.info("TransactionId: " + response.getTransactionId());
		// balance
		// logger.info("Balance: " + response.getBalance() / 100);
		// reverse transaction
		// logger.info("Balance: " + response.getTransactionId());

		logger.info("The output response is::::" + response.toString());
	}

	public com.ng.mats.psa.mt.teasymobile.xmlprocessor.TransferResponse generateResponse() {
		com.ng.mats.psa.mt.teasymobile.xmlprocessor.TransferResponse response = new com.ng.mats.psa.mt.teasymobile.xmlprocessor.TransferResponse();
		Amount amount = new Amount();
		Currency currency = new Currency();
		Fee fee = new Fee();
		Status status = new Status();
		Responsemessage responseMessage = new Responsemessage();

		status.setValue("Complete");
		fee.setValue("5");
		currency.setValue("NGN");
		amount.setValue("100");
		responseMessage.setValue("Internal server error");

		response.setAmount(amount);
		response.setCurrency(currency);
		response.setFee(fee);
		response.setStatus(status);
		response.setResponsemessage(responseMessage);
		String xmlFormat = rp.marshalTransferResponse(response);
		logger.info("THE TEST MARSHALLING OF THE TRANSFER RESPONSE =================="
				+ xmlFormat);

		logger.info("THE RETURNED MARSHALLING TEST============="
				+ rp.unMarshalTransferResponse(xmlFormat));
		return response;
	}
}
