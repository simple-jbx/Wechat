package utils;

import java.security.MessageDigest;

public class SHA1Util {

	/***
	 * 使用sha-1散列方法处理str
	 * @param str
	 * @return sha1Encode(str)
	 */
	public static String sha1Encode(String str) {
		MessageDigest sha1 = null;
		char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',  
                'a','b','c','d','e','f'};	
		try {
			sha1 = MessageDigest.getInstance("SHA1");
			sha1.update(str.getBytes("UTF-8"));
			byte[] sha1Bytes = sha1.digest();
			int length = sha1Bytes.length; 
			StringBuffer buff = new StringBuffer("");
            for (int i = 0; i < length; i++) {  
                byte byte0 = sha1Bytes[i];  
                //将一个字节截取为两个十六进制字符
                buff.append(hexDigits[byte0 >>> 4 & 0xf]);
                buff.append(hexDigits[byte0 & 0xf]);
            }  
            return buff.toString(); 
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static void main(String[] args) throws Exception {
		System.out.println(sha1Encode("00000000"));
	}
}
