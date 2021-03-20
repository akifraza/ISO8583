package com.myapp.iso;

import org.jpos.iso.ISOException;

public class JposUtil {
	
	/**
	 * NPE save for splitting string
	 * @param string
	 * @param splitter
	 * @return
	 * @throws ISOException
	 */
	public static String[] split(String string, String splitter) throws ISOException{
		if (string == null || splitter == null) {
			throw new ISOException("string or splitter cannot be null");
		}
		return string.split(splitter);
	}
	
	/**
	 * NPE save for substring a given string between start and end index
	 * @param string
	 * @param start
	 * @param end
	 * @return
	 * @throws ISOException
	 */
	public static String subString(String string, int start, int end) throws ISOException{
		if (string == null) {
			throw new ISOException("String value cannot be null");
		}
		return string.substring(start, end);
	}
	
	/**
	 * NPE save for subString a given string between start index and end of string
	 * @param string
	 * @param start
	 * @return
	 * @throws ISOException
	 */
	public static String subString(String string, int start) throws ISOException{
		if (string == null) {
			throw new ISOException("String value cannot be null");
		}
		return string.substring(start);
	}
	
	/**
	 * Convert Decimal to Hex
	 * @param decimalNumber
	 * @return
	 */
	public static String decimalToHexa(Long decimalNumber) {
		return Long.toHexString(decimalNumber);
    }
 
	/**
	 * Convert Decimal To Binary Type
	 * @param decimalNumber
	 * @return
	 */
    public static String decimalToBinary(Long decimalNumber) {
        StringBuilder binaryNumber = new StringBuilder();
        StringBuilder sbBinary = new StringBuilder();
        String binaryString = Long.toBinaryString(decimalNumber);
        char[] binary = binaryString.toCharArray();
        int counter = 0;
        for (int i=binary.length-1; i>=0; i--) {
            counter++;
            sbBinary.append(binary[i]);
            if (counter == 4) counter = 0;
        }
 
        for (int i=0; i<4-counter; i++) {
            if (counter > 0) sbBinary.append("0");
        }
 
        for (int i=sbBinary.length()-1; i>=0;i--) {
            binaryNumber.append(sbBinary.toString().charAt(i));
        }
 
        return binaryNumber.toString();
    }
 
    /**
     * Convert Binary to Decimal
     * @param binaryNumber
     * @return
     */
    public static Long binaryToDecimal(String binaryNumber) {
        return Long.parseLong(binaryNumber, 2);
    }
 
    /**
     * Convert Binary to Hex
     * @param binaryNumber
     * @return
     */
    public static String binaryToHexa(String binaryNumber) {
        return decimalToHexa(binaryToDecimal(binaryNumber));
    }
 
    /**
     * Convert Hex to Decimal
     * @param hexaNumber
     * @return
     */
    public static Long hexaToDecimal(String hexaNumber) {
        return Long.parseLong(hexaNumber, 16);
    }
 
    /**
     * Convert Hex to Binary
     * @param hexaNumber
     * @return
     */
    public static String hexaToBinary(String hexaNumber) {
        return decimalToBinary(hexaToDecimal(hexaNumber));
    }

    /**
     * Split String every 'n' digit
     * @param string
     * @param interval
     * @return
     * @see <a href="http://stackoverflow.com/questions/12295711/split-a-string-at-every-nth-position">Aske B. answere</a>
     */
    public static String[] splitStringEvery(String s, int interval) {
        int arrayLength = (int) Math.ceil(((s.length() / (double)interval)));
        String[] result = new String[arrayLength];

        int j = 0;
        int lastIndex = result.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            result[i] = s.substring(j, j + interval);
            j += interval;
        } //Add the last bit
        result[lastIndex] = s.substring(j);

        return result;
    }
	
}
