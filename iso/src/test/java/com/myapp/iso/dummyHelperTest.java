package com.myapp.iso;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jpos.iso.ISOMsg;
import org.junit.Test;

public class dummyHelperTest {

	private static final SimpleDateFormat formatterBit7 = new SimpleDateFormat("MMddHHmmss");
	private static final SimpleDateFormat formatterBit12 = new SimpleDateFormat("HHmmss");
	private static final SimpleDateFormat formatterBit1315 = new SimpleDateFormat("MMdd");
	private static final SimpleDateFormat formatterBit48 = new SimpleDateFormat("ddMMyyyy");

	@Test
	public void testHitBitmap() {
		Map<Integer, String> message = new HashMap<>();
		
        Date now = new Date();
        Integer spaceData = 20;
        String MessageISO = StringUtils.rightPad("02188776655", spaceData, " ");

        String bit48 = messageConstants.PRODUCT_CODE_TELCOM;
        bit48 += "0";
        bit48 += MessageISO;
        bit48 += formatterBit48.format(now);
        bit48 += formatterBit12.format(now);
        //Customer Name
        String CustName = "AKIF RAZA";
        bit48 += CustName;
        bit48 += StringUtils.rightPad("", 30 - CustName.length(), " ");
        bit48 += messageConstants.AMOUNT_INQ;
        bit48 += messageConstants.ADMIN_FEE_INQ;
        bit48 += StringUtils.rightPad("", 32, " "); // ref #1
        bit48 += StringUtils.rightPad("", 32, " "); // ref #2
        bit48 += messageConstants.SYB_REC_ID_INQ;

        message.put(7, formatterBit7.format(now));
        message.put(11, new RunningNumberGenerator().generateStan());
        message.put(12, formatterBit12.format(now));
        message.put(13, formatterBit1315.format(now));
        message.put(15, formatterBit1315.format(now));
        message.put(18, messageConstants.MERCHANT_TYPE);
        message.put(32, messageConstants.ACQ_ID_CODE);
        message.put(37, new RunningNumberGenerator().generateStan());
        message.put(41, messageConstants.TERMINAL_ID);
        message.put(48, bit48);
        message.put(49, messageConstants.CURRENCY_CODE);
        message.put(63, messageConstants.SYB_FORMAT_CODE);
		
//		
//		
//		
//		message.put(7, "0806153031");
//		message.put(11, "120031");
//		message.put(70, "301");

		BigInteger bitmap = isoHelper.hitBitmap(message);

		System.out.println("BitMap  : " + bitmap.toString(16));

		System.out.println("Message : " + isoHelper.isomsgToString(messageConstants.MTI_REQ_INQ, message));
	}
}
