package com.mobilemoney.services.mwallet.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Client {

	public static void main(String args[]) throws Exception {
		// TeasyMobileClient teasyMobileClient = new TeasyMobileClient();
		//
		// MTransferResponseType response = teasyMobileClient.doCashout();
		//
		// System.out.println(response.getResponseMessage());

//		String date = "12/01/2010";
//		String incDate = "";
//		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//		Calendar c = Calendar.getInstance();
//		c.setTime(sdf.parse(date));
//		int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
//		for (int co = 0; co < maxDay; co++) {
//			c.add(Calendar.DATE, 1);
//			incDate = sdf.format(c.getTime());
//			System.out.println(incDate);
//		}

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("YYYYMMddHHmmSSS");
//		if(ft.format(dNow).length() != 15){
//			SimpleDateFormat ft = new SimpleDateFormat("YYYYMMddmsS");
//		}

		System.out.println("Current Date: " + ft.format(dNow));
		
	}

}
