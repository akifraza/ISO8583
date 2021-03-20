package com.myapp.iso;


/**
 *
 * @author akifraza
 */
import java.math.BigInteger;
import java.util.Map;

public abstract class isoHelper {
	
	
	public static String isomsgToString (String mti, Map<Integer, String> isomsg ) {
		StringBuilder output = new StringBuilder();
		output.append(mti);
		System.out.println("MTI Added" + output);
		String bitmap = hitBitmap(isomsg).toString(16);
		System.out.println (bitmap);
		String Ascii = HextoAscii (bitmap);
		output.append(Ascii);
		System.out.println("ASCII Added" + output);
		System.out.println(isomsg.get(2));
		
		//isomsg.keySet().stream().forEach(x -> output.append(isomsg.get(x)));
		for (int i = 0; i < 128; i++)
		{
			if (isomsg.get(i) != null)
			output.append(isomsg.get(i));
		}
		return output.toString();
	}
	
	
	public static BigInteger hitBitmap(Map<Integer, String> isomsg) {
		BigInteger output = BigInteger.ZERO;

		int maxBit = 128;
		for (int i = 0; i < maxBit; i++) {
			if (i > 64) {
				output = output.setBit(maxBit - 1);
				
			}
			
			if (isomsg.get(i) != null) {
				output = output.setBit(maxBit - i);
				
			}
		}
		System.out.println("OutPut " + output);
		return output;
		
	}
	
	public static String HextoAscii (String hexString) {
	      String hex = hexString;
	      //			F632454128E090000000000004000000

	      if(hex.length()%2!=0){
	         System.err.println("Invlid hex string.");
	   }
	      
	      StringBuilder builder = new StringBuilder();

	      for (int i = 0; i < hex.length(); i = i + 2) {
	         // Step-1 Split the hex string into two character group
	         String s = hex.substring(i, i + 2);
	         // Step-2 Convert the each character group into integer using valueOf method
	         int n = Integer.valueOf(s, 16);
	         // Step-3 Cast the integer value to char
	         builder.append((char)n);
	      }

	      System.out.println("Hex = " + hex);
	      System.out.println("ASCII = " + builder.toString());
	      
	      return (builder.toString());
	   }

}
