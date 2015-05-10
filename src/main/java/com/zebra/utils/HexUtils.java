package com.zebra.utils;

/**
 * 编码解码工具类
 * 
 * @author zhangjz
 * @version [1.0.0 2013-05-07]
 */
public class HexUtils {
	
	public static byte[] hex2byte(String hexString) {
		Assert.notNull(hexString);

		int len = hexString.length();

		byte[] out = new byte[len >> 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; j < len; i++) {
			int f = Character.digit(hexString.charAt(j), 16) << 4;
			j++;
			f = f | Character.digit(hexString.charAt(j), 16);
			j++;
			out[i] = (byte) (f & 0xFF);
		}

		return out;
	}

	/**
	 * byte数组转16进制串
	 * @param bytes 被转换的byte数组
	 * @return 转换后的值
	 */
	public static String byte2hex(byte[] bytes) {
		final StringBuilder des = new StringBuilder();
		String tmp = null;
		for (int i = 0; i < bytes.length; i++) {
			tmp = (Integer.toHexString(bytes[i] & 0xFF));
			if (tmp.length() == 1) {
				des.append("0");
			}
			des.append(tmp);
		}
		return des.toString();
	}

	public static void main(String[] args) {

		String hString = "46333430303035567141674E46714D44";
		System.out.println(new String(hex2byte(hString)));
	}
}
