package com.myapp.iso;

import org.junit.Test;

import com.sleepycat.je.log.FileReader.EOFException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.BitSet;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.packager.ISO87BPackager;

public class systemHelperTestLocal {

	@Test
	public void testGenerateInqReq() throws ISOException {
		ISOPackager packager = new isoPackager();
		ISOMsg reqPay = systemHelper.createWithDrawRequest("02188776655");
		reqPay.setPackager(packager);

		reqPay.recalcBitMap();
		BitSet bset = (BitSet) reqPay.getValue(-1);

		
	    String outputPay = new String (reqPay.pack());
		String Hex = outputPay.substring(4, 32);
		outputPay = outputPay.replaceAll(Hex, hextoAscii(Hex));


		byte[] dump= ISOUtil.hex2byte(outputPay.toString());
		System.out.println(ISOUtil.hexdump(dump));
	    System.out.println("======================");
		System.out.println(outputPay);
		System.out.println("BitMap : " + bset);
		System.out.println("BitMap : " + org.jpos.iso.ISOUtil.bitSet2String(bset));
		System.out.println("======================");

		short messageLength = (short) (outputPay.length() + 2);
		System.out.println("Full Message      : [" + messageLength + "]");
		
		ISOMsg receiveMessage = new ISOMsg();
		receiveMessage.setPackager(packager);
		receiveMessage.unpack(outputPay.getBytes());
		System.out.println (receiveMessage.getMTI());
		BitSet bsetRec = (BitSet) receiveMessage.getValue(-1);
		System.out.println("Receive Message: " + bsetRec);
		
		System.out.println(reqPay.getBytes(1));
		System.out.println(receiveMessage.toString());
		
		for (int i=1; i <= receiveMessage.getMaxField(); i++) {
		if (receiveMessage.hasField(i)) {
			//System.out.println("Bit " + i + " Active");
			System.out.println("Bit Data " + i + " [" + receiveMessage.getString(i) + "] " + receiveMessage.getPackager()
            .getFieldDescription(receiveMessage, i));
		}
	}
	}
	public String hextoAscii (String msg) {
		String hex = new String (msg);
		// F632454128E090000000000004000000



		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < hex.length(); i = i + 2) {
			// Step-1 Split the hex string into two character group
			String s = hex.substring(i, i + 2);
			// Step-2 Convert the each character group into integer using valueOf method
			int n = Integer.valueOf(s, 16);
			// Step-3 Cast the integer value to char
			builder.append((char) n);
		}

		//System.out.println("Hex = " + hex);
		//System.out.println("ASCII = " + builder.toString());
		return builder.toString();
	}
	
	   public String AsciiToHex (String msg) {

		      String ascii = new String (msg);

		      // Step-1 - Convert ASCII string to char array
		      char[] ch = ascii.toCharArray();

		      StringBuilder builder = new StringBuilder();
		      for (char c : ch) {
		         // Step-2 Use %H to format character to Hex
		         String hexCode=String.format("%H", c);
		         builder.append(hexCode);
		      }

		      System.out.println("ASCII = " + ascii);
		      System.out.println("Hex = " + builder.toString());
		      return builder.toString();
		   }
}
