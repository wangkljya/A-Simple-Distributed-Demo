package com.wkl.utils;

public class ConvertUtil {
	
	//int -> byte[]
	public static byte[] intToBytes(int value) {  
        byte[] des = new byte[4];  
        des[0] = (byte) (value & 0xff);  // 低位(右边)的8个bit位  
        des[1] = (byte) ((value >> 8) & 0xff); //第二个8 bit位  
        des[2] = (byte) ((value >> 16) & 0xff); //第三个 8 bit位  
        des[3] = (byte) ((value >> 24) & 0xff); //第4个 8 bit位  
        return des;  
    }  
	
	//byte[] -> int
	public static int bytesToInt(byte[] des, int offset) {  
        int value;  
        value = (int) (des[offset] & 0xff  
                | ((des[offset + 1] & 0xff) << 8)  
                | ((des[offset + 2] & 0xff) << 16)  
                | (des[offset + 3] & 0xff) << 24);  
        return value;  
    } 
}
