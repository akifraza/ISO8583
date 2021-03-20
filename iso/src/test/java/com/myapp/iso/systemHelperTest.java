package com.myapp.iso;


/**
 *
 * @author akifraza
 */
import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.BitSet;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;

public class systemHelperTest {

	@Test
	public void testGenerateInqReq() throws ISOException, InterruptedException {
//		ISOMsg reqInq = systemHelper.createInquiryRequest("02188776655");
		ISOPackager packager = new isoPackager();
//		reqInq.setPackager(packager);
//		String outputInq = new String (reqInq.pack());
//		System.out.println ("======================");
//		System.out.println ("ATTM" + outputInq);
//		System.out.println ("======================");
//
//		ISOMsg reqEcho = systemHelper.createEchoMessage("02188776655");
//		reqEcho.setPackager(packager);
//		String outputEcho = new String (reqEcho.pack());
//		System.out.println ("======================");
//		System.out.println ("ATTM" + outputEcho);
//		System.out.println ("======================");
//		
		try {

			Socket sock = new Socket("localhost", 12345);
			DataOutputStream out = new DataOutputStream(sock.getOutputStream());
			DataInputStream in = new DataInputStream(sock.getInputStream());
			for (int a = 0; a < 2000; a++) {
				ISOMsg reqPay = systemHelper.createWithDrawRequest("02188776655");
				// ISOMsg reqPay = systemHelper.createWithDrawRequest1304("02188776655");
				// ISOMsg reqPay = systemHelper.createEchoMessage("02188776655");
				reqPay.setPackager(packager);

				reqPay.recalcBitMap();
				BitSet bset = (BitSet) reqPay.getValue(-1);

				String outputPay = new String(reqPay.pack());
				String Hex = outputPay.substring(4, 32 + 4);
				System.out.println("Length " + hextoAscii(Hex).length());
				outputPay = outputPay.replaceAll(Hex, hextoAscii(Hex));
				byte[] dump = ISOUtil.hex2byte(outputPay.toString());

				System.out.println(ISOUtil.hexdump(dump));
				System.out.println("======================");
				System.out.println("ATTM" + outputPay);
				System.out.println("BitMap : " + bset);
				System.out.println("BitMap Binary: " + org.jpos.iso.ISOUtil.bitSet2String(bset));
				System.out.println("======================");

				short messageLength = (short) (outputPay.length() + 2);
				System.out.println("Full Message      : [" + messageLength + "]");

				// Sending Data.....

				out.writeShort(messageLength);
				// out.writeBytes(outputPay);
				out.writeUTF(outputPay);
				
				// out.close();
				System.out.println("Message Send Successfully....");

				// Receiving Response.....

				short responseLength = in.readShort();
				System.out.println("Full Response " + responseLength);
				String responseData;
				responseData = in.readUTF();
				System.out.println("Response From Server: " + new String(responseData));

			}
			out.flush();
			out.close();
			in.close();
			sock.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public String hextoAscii(String msg) {
		String hex = new String(msg);
		// F632454128E090000000000004000000

		StringBuilder builder = new StringBuilder();
		// System.out.println(hex.length());
		for (int i = 0; i < hex.length(); i = i + 2) {
			// Step-1 Split the hex string into two character group
			String s = hex.substring(i, i + 2);
			// Step-2 Convert the each character group into integer using valueOf method
			int n = Integer.valueOf(s, 16);
			// System.out.println("Hex : " + s + " Dec " + n);
			// Step-3 Cast the integer value to char
			builder.append((char) n);
		}
		// System.out.println(builder.toString().length());
		// System.out.println("Hex = " + hex);
		// System.out.println("ASCII = " + builder.toString());

		char[] ch = builder.toString().toCharArray();

		StringBuilder Hexbuilder = new StringBuilder();
		for (char c : ch) {
			// System.out.println(c);
			// Step-2 Use %H to format character to Hex
			String hexCode = String.format("%H", c);
			if (hexCode.equalsIgnoreCase("0") == true) {
				hexCode = "00";
				// System.out.println(hexCode);
			}
			Hexbuilder.append(hexCode);
		}

		// System.out.println("ASCII = " + builder.toString());
		// System.out.println("Hex = " + Hexbuilder.toString());
		return builder.toString();
	}

	public String hexToBinary(String reqMsg) {
		String reqMsgString = new String(reqMsg);
		String mti = reqMsgString.substring(0, 4);
		System.out.println(mti);
		String body = reqMsgString.substring(4);
		char firstPrimaryHex = body.charAt(0);
		String firstPrimaryBit = JposUtil.hexaToBinary(String.valueOf(firstPrimaryHex));

		String primaryBitmapHex = "";
		String bitData = "";

		if (firstPrimaryBit.startsWith("1")) {
			primaryBitmapHex = body.substring(0, 32);
			bitData = body.substring(32);
		} else {
			primaryBitmapHex = body.substring(0, 16);
			bitData = body.substring(16);
		}

		String[] splittedPrimaryBitmap = JposUtil.splitStringEvery(primaryBitmapHex, 1);
		StringBuilder primaryBitmapBit = new StringBuilder();

		for (int i = 0; i < splittedPrimaryBitmap.length; i++) {
			primaryBitmapBit.append(JposUtil.hexaToBinary(splittedPrimaryBitmap[i]));
		}

		String formatedReqMsg = mti + primaryBitmapBit.toString() + bitData;
		return formatedReqMsg;
	}

}
