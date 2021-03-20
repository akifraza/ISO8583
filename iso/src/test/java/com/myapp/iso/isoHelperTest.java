package com.myapp.iso;

import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;



public class isoHelperTest {
	
	@Test
	public void  testHitBitmap () {
		Map<Integer, String> message = new HashMap<>();
		message.put(2, "164283250003279396");
		message.put(3, "312000");
		message.put(4, "000000000000");
		message.put(6, "000000000000");
		message.put(7, "0220152818");
		message.put(11, "886921");
		message.put(12, "150220152818");
		message.put(15, "150220");
		message.put(18, "0000");
		message.put(22, "2G0101215141");
		message.put(24, "200");
		message.put(26, "6011");
		message.put(32, "1160137300000");
		message.put(35, "274283250003279396=1712126885");
		message.put(37, "505120886921");
		message.put(41, "1101    ");
		message.put(42, "0110           ");
		message.put(43, "KARACHI GAON BRANCH      KARACHI      PK                                                           ");
		message.put(49, "586");
		message.put(52, "00000000");
		message.put(102, "1401100060052737");
		//System.out.println(message.get(2));
		BigInteger bitmap = isoHelper.hitBitmap(message);
	
		System.out.println ("BitMap  : " + bitmap.toString(16));
		
		System.out.println ("Message : " + isoHelper.isomsgToString("ATTM1200", message));
		
		send (isoHelper.isomsgToString("ATRB1200", message));
	}
	
	public void send (String message) {
		short messageLength = (short) (message.length() + 2);
		System.out.println ("Full Message      : [" + messageLength + "]");
		try {
			// Sending Data.....
			Socket sock = new Socket ("localhost",3500);
			DataOutputStream out = new DataOutputStream(sock.getOutputStream());
			out.writeShort(messageLength);
			out.writeBytes(message);
			//out.writeChars(message);
			out.flush();
			System.out.println ("Message Send Successfully....");		
//			// Receiving Response.....
//			DataInputStream in = new DataInputStream (sock.getInputStream());
//			short responseLength = in.readShort();
//			System.out.println("Full Response " + responseLength);
//			byte[] responseData = new byte [responseLength - 2];
//			in.readFully(responseData);
//			System.out.println("Response : " + new String (responseData));
			sock.close();
		} catch (IOException e) {
			System.out.println (e.getMessage());
		}
		
	}
}
