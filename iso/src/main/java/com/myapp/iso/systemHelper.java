package com.myapp.iso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author akifraza
 */
public class systemHelper {

	private static final SimpleDateFormat formatterBit7 = new SimpleDateFormat("MMddHHmmss");
	private static final SimpleDateFormat formatterBit12 = new SimpleDateFormat("YYMMddHHmmss");
	private static final SimpleDateFormat formatterBit1315 = new SimpleDateFormat("MMdd");
	private static final SimpleDateFormat formatterBit15 = new SimpleDateFormat("YYMMdd");
	
	private static final SimpleDateFormat formatterBit48 = new SimpleDateFormat("ddMMyyyy");

	public static ISOMsg createInquiryRequest(String Message) {
		try {
			Date now = new Date();
			Integer spaceData = 20;
			String MessageISO = StringUtils.rightPad(Message, spaceData, " ");

			String bit48 = messageConstants.PRODUCT_CODE_TELCOM;
			bit48 += "0";
			bit48 += MessageISO;
			bit48 += formatterBit48.format(now);
			bit48 += formatterBit12.format(now);
			// Customer Name
			String CustName = "AKIF RAZA";
			bit48 += CustName;
			bit48 += StringUtils.rightPad("", 30 - CustName.length(), " ");
			bit48 += messageConstants.AMOUNT_INQ;
			bit48 += messageConstants.ADMIN_FEE_INQ;
			bit48 += StringUtils.rightPad("", 32, " "); // ref #1
			bit48 += StringUtils.rightPad("", 32, " "); // ref #2
			bit48 += messageConstants.SYB_REC_ID_INQ;

			ISOMsg reqMsg = new ISOMsg();

			reqMsg.setMTI(messageConstants.MTI_REQ_INQ);
			reqMsg.set(7, formatterBit7.format(now));
			reqMsg.set(11, new RunningNumberGenerator().generateStan());
			reqMsg.set(12, formatterBit12.format(now));
			reqMsg.set(13, formatterBit1315.format(now));
			reqMsg.set(15, formatterBit1315.format(now));
			reqMsg.set(18, messageConstants.MERCHANT_TYPE);
			reqMsg.set(32, messageConstants.ACQ_ID_CODE);
			reqMsg.set(37, new RunningNumberGenerator().generateStan());
			reqMsg.set(41, messageConstants.TERMINAL_ID);
			reqMsg.set(48, bit48);
			reqMsg.set(49, messageConstants.CURRENCY_CODE);
			reqMsg.set(63, messageConstants.SYB_FORMAT_CODE);
			return reqMsg;

		} catch (ISOException ex) {
			Logger.getLogger(systemHelper.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public static ISOMsg createEchoMessage(String Message) {
		try {
			Date now = new Date();
			ISOMsg reqMsg = new ISOMsg();
			reqMsg.setMTI("1804");
			reqMsg.set(7, formatterBit7.format(now));
			reqMsg.set(11, new RunningNumberGenerator().generateStan());
			reqMsg.set(12, formatterBit12.format(now));
			reqMsg.set(24, "002");
			reqMsg.set(93, "1142146200000");
			reqMsg.set(94,"1142146200000");
			return reqMsg;
		} catch (ISOException ex) {
			Logger.getLogger(systemHelper.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	public static ISOMsg createWithDrawRequest1304 (String Message) {
		try {
			ISOMsg reqMsg = new ISOMsg();
			reqMsg.setMTI(messageConstants.MTI_REQ_1304);
			reqMsg.set(2,"1401100060035126");
//			reqMsg.set(3, "312000");
//			reqMsg.set(4, "000000000000");
//			reqMsg.set(6, "000000000000");
//			reqMsg.set(7, "0220152818");
			reqMsg.set(11, "082157");
			reqMsg.set(12, "150220153743");
//			reqMsg.set(15, "150220");
//			reqMsg.set(18, "0000");
//			reqMsg.set(22, "2G0101215141");
			reqMsg.set(24, "333");
			reqMsg.set(25, "EAIN");
			reqMsg.set(26, "6011");
			reqMsg.set(32, "044230");
//			reqMsg.set(35, "274283250003279396=1712126885");
//			reqMsg.set(37, "505120886921");
//			reqMsg.set(41, "1101    ");
//			reqMsg.set(42, "0110           ");
//			reqMsg.set(43, "UXBZI BANK   BRANCH      ROMAL        CA                                                           ");
//			reqMsg.set(49, "971");
//			reqMsg.set(52, "00000000");
			reqMsg.set(59, "0200200003000          ");
			reqMsg.set(72, "071000110          00000000000000000000000000000000            FSB   ATM  ");
			//reqMsg.set(102, "1401100060052737");
			return reqMsg;
		} catch (ISOException ex) {
			Logger.getLogger(systemHelper.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		
	}
	
	
	public static ISOMsg createWithDrawRequest (String Message) {
		try {
			ISOMsg reqMsg = new ISOMsg();
			Date now = new Date();
			//reqMsg.setHeader("ATRB".getBytes());
			reqMsg.setMTI(messageConstants.MTI_REQ_PAY);
			reqMsg.set(2,"164283250003279396");
			reqMsg.set(3, "312000");
			reqMsg.set(4, "000000000000");
			reqMsg.set(6, "000000000000");
			reqMsg.set(7, formatterBit7.format(now));
			reqMsg.set(11, new RunningNumberGenerator().generateStan());
			//reqMsg.set(12, "150220152818"); ormatterBit7.format(now)
			reqMsg.set(12, formatterBit12.format(now));
			reqMsg.set(15, formatterBit15.format(now));
			reqMsg.set(18, "0000");
			reqMsg.set(22, "2G0101215141");
			reqMsg.set(24, "200");
			reqMsg.set(26, "6011");
			reqMsg.set(32, "1160137300000");
			reqMsg.set(35, "274283250003279396=1712126885");
			reqMsg.set(37, "505120886921");
			reqMsg.set(41, "1101    ");
			reqMsg.set(42, "0110           ");
			reqMsg.set(43, "AZIZI BANK MAIN BRANCH   KABUL        AF                                                           ");
			reqMsg.set(49, "971");
			reqMsg.set(52, "00000000");
			reqMsg.set(102, "1401100060052737");
			return reqMsg;
		} catch (ISOException ex) {
			Logger.getLogger(systemHelper.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
		
	}
}
