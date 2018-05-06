package utils;

import java.security.MessageDigest;

/**
 * ����MD5����
 * 
 * @author SNNUJSX
 * @datetime 2017-03-06
 */
public class MD5Util {

	/***
	 * MD5���� ����32λmd5��
	 * 
	 * @param �������ַ���
	 * @return ����32λmd5�� ����MD5����һ��Ϊ�˰�ȫ�����û������룬����ѧ����������ϵͳ�����ݿ���������Ҳ����MD5����
	 */

	public static String md5Encode(String str) throws Exception {
		MessageDigest md5 = null;

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = str.getBytes("UTF-8");
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * ����������
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		String str = new String("amigoxiexiexingxing");
		System.out.println("ԭʼ��" + str);
		System.out.println("MD5��" + md5Encode(str));
	}
}