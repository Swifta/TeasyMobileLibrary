package com.mobilemoney.services.mwallet;

import java.math.BigDecimal;
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
import com.mobilemoney.services.mwallet.MPWalletServiceStub.CreditRequest;
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
import com.ng.mats.psa.mt.teasymobile.model.MoneyTransfer;

public class TeasyMobileClient {
	private static final Logger logger = Logger
			.getLogger(TeasyMobileClient.class.getName());
	private MPWalletServiceStub mpWalletServiceStub = null;

	public TeasyMobileClient() throws Exception {

		mpWalletServiceStub = new MPWalletServiceStub();

	}

	public MTransferResponseType transactionQuery(MoneyTransfer moneyTransfer)
			throws RemoteException {
		mpWalletServiceStub._getServiceClient().addHeader(
				ClientUtil.getHeaderOMElement());
		mpWalletServiceStub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);
		TransactionQueryRequest transactionQueryRequest = new TransactionQueryRequest();
		MTransactionQuery mTransRequest = new MTransactionQuery();
		AccountNumber accountNumber = new AccountNumber();
		System.out.println("---------------------------Account number>>>>"
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
				ClientUtil.getHeaderOMElement());
		mpWalletServiceStub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);
		AirtimeTopupRequest airtimeTopupRequest = new AirtimeTopupRequest();
		MAirtimeTopupRequestType airtimeRequest = new MAirtimeTopupRequestType();
		AccountNumber accountNumber = new AccountNumber();
		System.out.println("---------------------------Account number>>>>"
				+ moneyTransfer.getSender());
		accountNumber.setAccountNumber(moneyTransfer.getSender()); // 2348171000157
		airtimeRequest.setAccountNumber(accountNumber);
		PIN pin = new PIN();
		System.out.println("---------------------------PIN>>>"
				+ moneyTransfer.getTeasypin());
		pin.setPIN(moneyTransfer.getTeasypin()); // 7005
		airtimeRequest.setAccountPIN(pin);
		airtimeRequest.setAmount(moneyTransfer.getAmount().intValue());
		System.out.println("---------------------------Amount>>>>"
				+ moneyTransfer.getAmount());
		CurrencyCode currencyCode = new CurrencyCode();
		System.out.println("---------------------------Currency code");
		currencyCode.setCurrencyCode("NGN");
		airtimeRequest.setCurrency(currencyCode);
		MmTransferCode mmTransferCode = new MmTransferCode();
		System.out.println("---------------------------MmTransfer code");
		mmTransferCode.setMmTransferCode("T3ASYT3ST");
		airtimeRequest.setOriginCode(mmTransferCode);
		RequestReference requestReference = new RequestReference();
		System.out.println("---------------------------Request reference");
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("YYYYMMddHHmmSSS");
		requestReference.setRequestReference(ft.format(dNow));
		airtimeRequest.setRequestReference(requestReference);
		mmTransferCode = new MmTransferCode();
		mmTransferCode.setMmTransferCode("KAKA");
		airtimeRequest.setDestinationCode(mmTransferCode);
		accountNumber = new AccountNumber();
		System.out.println("---------------------------Account number>>>>"
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
				ClientUtil.getHeaderOMElement());
		mpWalletServiceStub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);
		ReverseTxnRequest reverseTxnRequest = new ReverseTxnRequest();
		MTxnReverseRequest mTxnReverseRequest = new MTxnReverseRequest();

		AccountNumber accountNumber = new AccountNumber();
		System.out.println("---------------------------Account number>>>>"
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
				ClientUtil.getHeaderOMElement());
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
				ClientUtil.getHeaderOMElement());
		mpWalletServiceStub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);
		BalanceGetRequest balanceGetRequest = new BalanceGetRequest();
		MBasicRequestType param = new MBasicRequestType();
		AccountNumber accountNumber = new AccountNumber();
		accountNumber.setAccountNumber(moneyTransfer.getReceiver());
		param.setAccountNumber(accountNumber);

		PIN pin = new PIN();
		pin.setPIN(moneyTransfer.getTeasypin());

		param.setAccountPIN(pin);
		balanceGetRequest.setBalanceGetRequest(param);

		BalanceGetResponse balanceGetResponse = mpWalletServiceStub
				.balanceGetRequest(balanceGetRequest, null);
		// mpWalletServiceStub.
		System.out.println("---------------------------Transfer Response");
		return balanceGetResponse.getBalanceGetResponse();

	}

	public AdditionalHeader getAdditionalHeader() {
		AdditionalHeader additionalHeader = new AdditionalHeader();
		MHeader mHeaderParam = new MHeader();
		OMElement loginCredentials = ClientUtil.getHeaderOMElement(), contentCredentials = null;
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
		System.out.println("<<<<<<<<<<<<<THE PARAMETER HEADERS ARE >>>>>>>>>>"
				+ mHeaderParam.getUsername() + " :::::Password:::::"
				+ mHeaderParam.getPassword());
		additionalHeader.setAdditionalHeader(mHeaderParam);
		return additionalHeader;
	}

	public MTransferResponseType doCashout(MoneyTransfer moneyTransfer)
			throws Exception {
		System.out.println("...do Cash out...");
		System.out.println("-----------------------------doing cashout.....");

		if (mpWalletServiceStub == null) {
			System.out.println("Fund stub is not available");
			System.out
					.println("---------------------------Fund stub is not available");
		}
		int amount = moneyTransfer.getAmount().intValue() * 100;

		// DebitRequest debitRequest = new DebitRequest();
		System.out.println("---------------------------Debit request");
		// MTransferRequestType mtrTransferRequestType = new
		// MTransferRequestType();
		AccountNumber accountNumber = new AccountNumber(), merchantNumber = new AccountNumber();

		System.out.println("---------------------------Account number>>>>"
				+ moneyTransfer.getReceiver());
		accountNumber.setAccountNumber(moneyTransfer.getReceiver()); // 2348171000157

		System.out.println("---------------------------Merchant number>>>>"
				+ moneyTransfer.getSender());
		merchantNumber.setAccountNumber(moneyTransfer.getSender());
		// mtrTransferRequestType.setAccountNumber(accountNumber);
		PIN pin = new PIN();
		System.out.println("---------------------------PIN>>>"
				+ moneyTransfer.getTeasypin());
		pin.setPIN(moneyTransfer.getTeasypin()); // 7005
		// mtrTransferRequestType.setAccountPIN(pin);
		// mtrTransferRequestType.setAmount(amount);
		System.out.println("---------------------------Amount>>>>" + amount);
		CurrencyCode currencyCode = new CurrencyCode();

		currencyCode.setCurrencyCode("NGN");
		// mtrTransferRequestType.setCurrency(currencyCode);
		System.out.println("---------------------------Currency code::"
				+ currencyCode.getCurrencyCode());
		MmTransferCode mmTransferCode = new MmTransferCode();
		mmTransferCode.setMmTransferCode("T3ASYT3ST");
		System.out.println("---------------------------MmTransfer code::"
				+ mmTransferCode.getMmTransferCode());
		// mtrTransferRequestType.setOriginCode(mmTransferCode);
		RequestReference requestReference = new RequestReference();

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("YYYYMMddHHmmSSS");
		requestReference.setRequestReference(ft.format(dNow));
		// mtrTransferRequestType.setRequestReference(requestReference);
		System.out.println("---------------------------Request reference:"
				+ requestReference.getRequestReference());
		// debitRequest.setDebitRequest(mtrTransferRequestType);
		System.out.println("---------------------------PIN");

		mpWalletServiceStub._getServiceClient().addHeader(
				ClientUtil.getHeaderOMElement());
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
		} else {
			System.out.println("---------------------------Stub is not null");
		}

		CreditRequest creditRequest = new CreditRequest();

		int amount = moneyTransfer.getAmount().intValue() * 100;

		// MTransferRequestType mtrTransferRequestType = new
		// MTransferRequestType();
		AccountNumber accountNumber = new AccountNumber(), merchantNumber = new AccountNumber();
		accountNumber.setAccountNumber(moneyTransfer.getReceiver()); // 2348171000157
		merchantNumber.setAccountNumber(moneyTransfer.getSender());
		// mtrTransferRequestType.setAccountNumber(accountNumber);
		PIN pin = new PIN();
		System.out.println("after instantiation of PIN");
		pin.setPIN(moneyTransfer.getTeasypin()); // 7005

		// mtrTransferRequestType.setAccountPIN(pin);
		// mtrTransferRequestType.setAmount(amount);
		CurrencyCode currencyCode = new CurrencyCode();
		System.out.println("after instantiation of CurrencyCode");
		currencyCode.setCurrencyCode("NGN");
		// mtrTransferRequestType.setCurrency(currencyCode);
		MmTransferCode mmTransferCode = new MmTransferCode();
		System.out.println("MmtransferCode is instantiated");
		mmTransferCode.setMmTransferCode("T3ASYT3ST");
		// mtrTransferRequestType.setOriginCode(mmTransferCode);
		RequestReference requestReference = new RequestReference();
		System.out.println("Instantiation of RequestReference");
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("YYYYMMddHHmmSSS");
		requestReference.setRequestReference(ft.format(dNow));
		// mtrTransferRequestType.setRequestReference(requestReference);
		System.out.println("After set request reference");
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
				ClientUtil.getHeaderOMElement());
		mpWalletServiceStub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.CHUNKED, false);
		TransferResponse transferResponse = mpWalletServiceStub.cashInRequest(
				cashInRequest, null);

		// TransferResponse transferResponse =
		// mpWalletServiceStub.creditRequest(
		// creditRequest, getAdditionalHeader());
		System.out.println("After return of transfer response");
		return transferResponse.getTransferResponse();

	}

	public static void main(String args[]) throws Exception {
		TeasyMobileClient teasyMobileClient = new TeasyMobileClient();
		MoneyTransfer moneyTransfer = new MoneyTransfer("2348104001339",
				new BigDecimal(110), "dada", "1234");
		moneyTransfer.setSender("2348170730938");
		// MTransferResponseType response = teasyMobileClient
		// .doCashout(moneyTransfer);
		// MTransferResponseType response = teasyMobileClient
		// .doCashIn(moneyTransfer);
		// MBalanceResponse response =
		// teasyMobileClient.getBalance(moneyTransfer);
		moneyTransfer.setTeasyrequestreference("201411281658736");
		// MTransferResponseType response = teasyMobileClient
		// .airtimeSales(moneyTransfer);
		// MTransferResponseType response = teasyMobileClient
		// .transactionQuery(moneyTransfer);
		// MTxnReverseResponse response = teasyMobileClient
		// .reverseTransaction(moneyTransfer);
		moneyTransfer.setPingRequestParam("41.220.65.177");
		MPingResponse response = teasyMobileClient.pingRequest(moneyTransfer);
		System.out.println("Status: " + response.getStatus());

		System.out.println("ResponseMessage: " + response.getResponseMessage());

		/*
		 * airtimesales System.out.println("Amount: " + response.getAmount());
		 * System.out.println("CurrencyCode: " + response.getCurrency());
		 * System.out.println("Fee: " + response.getFee());
		 * System.out.println("TransactionId: " + response.getTransactionId());
		 */
		// balance
		// System.out.println("Balance: " + response.getBalance() / 100);
		// reverse transaction
		// System.out.println("Balance: " + response.getTransactionId());
	}

}
