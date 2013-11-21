package com.interswitchng.techquest.autopay.sdk.lib.utils;

public class HexConverter {


    /*=======================================================================*/
    /*	STATIC CLASS VARIABLES  											 */
    /*=======================================================================*/
    private final static byte[] hex_table = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /*=======================================================================*/
    /*	CONSTRUCTOR										 					 */
    /*=======================================================================*/
    public HexConverter() {
    }

    /*=======================================================================*/
    /*	IMPLEMENTED METHODS									 	 			 */
    /*=======================================================================*/
    public static String fromBinary2Hex(byte[] binary_data) {
        byte lo, hi;

        // get the length of the array
        String tmp = new String(binary_data);
        int len = tmp.length();
        byte[] str  = new byte[len * 2];

        int index = 0;
        for (int i = 0; i < len; i++) {
            lo = (byte) (binary_data[i] & 0x0F);
            hi = (byte) ((binary_data[i] >> 4) & 0x0F);

            str[index++] = hex_table[hi];
            str[index++] = hex_table[lo];
        }

        return new String(str);
    }

    public static String fromHex2Binary(byte[] hex_data) {
        byte val, val2;
        StringBuffer str = new StringBuffer();

        // get the length of the array
        String tmp = new String(hex_data);
        int len = tmp.length();
        //byte[] str = new byte[len / 2];
        str.setLength(len/2);

        val = 0;
        val2 = 0;

        for (int i=0, j=0; i < len; j++, i+=2) {

            if (hex_data[i] >= 'A' && hex_data[i] <= 'F') {
                val = (byte) (hex_data[i] - 'A' + 10);
            }
            else if (hex_data[i] >= 'a' && hex_data[i] <= 'f') {
                val = (byte) (hex_data[i] - 'a' + 10);
            }
            else if (hex_data[i] >= '0' && hex_data[i] <= '9') {
                val = (byte) (hex_data[i] - '0');
            }

            if (hex_data[i + 1] >= 'A' && hex_data[i + 1] <= 'F') {
                val2 = (byte) (hex_data[i + 1] - 'A' + 10);
            }
            else if (hex_data[i + 1] >= 'a' && hex_data[i + 1] <= 'f') {
                val2 = (byte) (hex_data[i + 1] - 'a' + 10);
            }
            else if (hex_data[i + 1] >= '0' && hex_data[i + 1] <= '9') {
                val2 = (byte) (hex_data[i + 1] - '0');
            }

            val = (byte) (val << 4);
            val = (byte) (val | val2);
            str.setCharAt(j, (char) val);
        }

        /*
        StringBuffer tmpbuf = new StringBuffer(2);

        for (int i=0, j=0; i < len; j++, i+=2) {
            //tmpbuf.insert(0, hex_data[i]);
            tmpbuf.insert(0, (char) hex_data[i]);
            tmpbuf.insert(1, (char) hex_data[i+1]);

            val = (byte) Integer.parseInt(tmpbuf.toString(), 16);
            //str.append(val);
            str.setCharAt(j, (char)val);
        }
        */

        return str.toString();
    }

    public static String fromShort2Hex(short val) {
        byte[] str = new byte[4];
        byte hi, lo;

        // get hex for high byte of integer
        hi = (byte) ((val >> 12) & 0x000F);
        lo = (byte) ((val >> 8) & 0x000F);

        str[0] = hex_table[hi];
        str[1] = hex_table[lo];

        // get hex for low byte of integer
        hi = (byte) ((val >> 4) & 0x000F);
        lo = (byte) (val & 0x000F);

        str[2] = hex_table[hi];
        str[3] = hex_table[lo];

        return new String(str);
    }

    public static byte[] fromShort2Hex2(short val) {
        byte[] str = new byte[2];
        byte hi, lo;

        // get hex for high byte of integer
        hi = (byte) ((val >> 8) & 0x00FF);
        lo = (byte) (val & 0x00FF);

        str[0] = hi;
        str[1] = lo;

        /*
        // get hex for low byte of integer
        hi = (byte) ((val >> 4) & 0x000F);
        lo = (byte) (val & 0x000F);

        str[2] = hi;
        str[3] = lo;
        */

        return str;
    }

    public static String fromByte2Hex(byte val) {
        byte[] str = new byte[2];
        byte hi, lo;

        // get hex for hi and low nibble of integer
        hi = (byte) ((val >> 4) & 0x0F);
        lo = (byte) (val & 0x0F);

        str[0] = hex_table[hi];
        str[1] = hex_table[lo];

        return new String(str);
    }

    public static short fromHex2Short(byte[] hex_data) {
        // byte[] str = new byte[2];
        int val = 0;

        for (int i=0; i < 4; i++) {
            val = 16 * val + HexValue(hex_data[i]);
        }

        return (short) val;

    }

    public static byte fromHex2Byte(byte[] hex_data) {
        // byte[] str = new byte[2];
        int val = 0;

        for (int i=0; i < 2; i++) {
            val = 16 * val + HexValue(hex_data[i]);
        }

        return (byte) val;

    }

    public static byte[] fromHex2ByteArray(byte[] hex_data) {
        int len = new String(hex_data).length();
        byte[] byte_array = new byte[len/2];
        int val = 0;

        for (int j=0; j < len/2; j++) {
            val = 0;
            for (int i=0; i < 2; i++) {
                val = 16 * val + HexValue(hex_data[j*2+i]);
            }
            byte_array[j] = (byte) val;
        }

        return byte_array;

    }

    public static byte HexValue(byte ch) {

        switch (ch) {
            case '0':
                return 0;

            case '1':
                return 1;

            case '2':
                return 2;

            case '3':
                return 3;

            case '4':
                return 4;

            case '5':
                return 5;

            case '6':
                return 6;

            case '7':
                return 7;

            case '8':
                return 8;

            case '9':
                return 9;

            case 'a': case 'A':
                return 10;

            case 'b': case 'B':
                return 11;

            case 'c': case 'C':
                return 12;

            case 'd': case 'D':
                return 13;

            case 'e': case 'E':
                return 14;

            case 'f': case 'F':
                return 15;
            default:
                return -1;

        }
    }
    
    public static int HexValueString(String ch)
    {
    	if(ch.equals("0"))
    		return 0;
    	if(ch.equals("1"))
    		return 1;
    	if(ch.equals("2"))
    		return 2;
    	if(ch.equals("3"))
    		return 3;
    	if(ch.equals("4"))
    		return 4;
    	if(ch.equals("5"))
    		return 5;
    	if(ch.equals("6"))
    		return 6;
    	if(ch.equals("7"))
    		return 7;
    	if(ch.equals("8"))
    		return 8;
    	if(ch.equals("9"))
    		return 9;
    	if(ch.equalsIgnoreCase("A"))
    		return 10;
    	if(ch.equalsIgnoreCase("B"))
    		return 11;
    	if(ch.equalsIgnoreCase("C"))
    		return 12;
    	if(ch.equalsIgnoreCase("D"))
    		return 13;
    	if(ch.equalsIgnoreCase("E"))
    		return 14;
    	if(ch.equalsIgnoreCase("F"))
    		return 15;
    	return 0;
    }
    
    public static String DecValue(byte ch) {

        switch (ch) {
            case 0:
                return "0";

            case 1:
                return "1";

            case 2:
                return "2";

            case 3:
                return "3";

            case 4:
                return "4";

            case 5:
                return "5";

            case 6:
                return "6";

            case 7:
                return "7";

            case 8:
                return "8";

            case 9:
                return "9";

            case 10:
                return "A";

            case 11:
                return "B";

            case 12:
                return "C";

            case 13:
                return "D";

            case 14:
                return "E";

            case 15:
                return "F";
            default:
                return "";

        }
    }
    
}
