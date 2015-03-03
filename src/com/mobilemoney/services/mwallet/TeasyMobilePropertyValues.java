package com.mobilemoney.services.mwallet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.logging.Logger;

import com.ng.mats.psa.mt.teasymobile.model.MoneyTransfer;

public class TeasyMobilePropertyValues {
	private static final Logger logger = Logger
			.getLogger(TeasyMobilePropertyValues.class.getName());

	public MoneyTransfer getPropertyValues() {
		// for cashout, payernumber = customer and reciever number = agent:::::
		// for cashing, payernumber = agent and reciever number = customer

		MoneyTransfer moneyTransfer = new MoneyTransfer();
		Properties prop = new Properties();
		String propFileName = "com/mobilemoney/services/mwallet/config.properties";

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);
		try {
			if (inputStream != null) {

				prop.load(inputStream);

			} else {
				throw new FileNotFoundException("property file '"
						+ propFileName + "' not found in the classpath");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// get the property value and print it out
		String parameterType = prop.getProperty("settings-type");
		logger.info("THE CONFIGURATION BEING USED AT THIS POINT IS ==========================="
				+ parameterType);

		moneyTransfer.setAmount(new BigDecimal(prop.getProperty("Amount_"
				+ parameterType)));
		moneyTransfer.setMmo(prop.getProperty("Mmo_" + parameterType));
		moneyTransfer.setPingRequestParam(prop.getProperty("PingRequestParam_"
				+ parameterType));
		moneyTransfer
				.setReceiver(prop.getProperty("Reciever_" + parameterType));
		moneyTransfer.setReference(prop.getProperty("Reference_"
				+ parameterType));
		moneyTransfer.setSender(prop.getProperty("Sender_" + parameterType));
		moneyTransfer
				.setTeasypin(prop.getProperty("TeasyPin_" + parameterType));
		moneyTransfer.setTeasyrequestreference(prop
				.getProperty("Teasyrequestreference_" + parameterType));
		moneyTransfer.setTransactionType(prop.getProperty("TransactionType_"
				+ parameterType));

		return moneyTransfer;
	}

}
