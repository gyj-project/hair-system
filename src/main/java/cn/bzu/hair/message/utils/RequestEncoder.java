package cn.bzu.hair.message.utils;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 处理请求数据
 * @author submail
 *
 */
public class RequestEncoder {
	
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	public static final String MD5 = "MD5";
	public static final String SHA1 = "SHA1";
	/**
	 * 编码的字符串
	 *
	 * @param algorithm
	 * @param str
	 * @return String
	 */
	public static String encode(String algorithm, String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(str.getBytes("UTF-8"));
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	/**
	 *获取原始字节并将其格式化。
	 * @param bytes
	 *            the raw bytes from the digest.
	 * @return the formatted bytes.
	 */
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		for (int j = 0; j < len; j++) { 			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}
	
	public static String formatRequest(Map<String, Object> data){
		Set<String> keySet = data.keySet();
		Iterator<String> it = keySet.iterator();
		StringBuffer sb = new StringBuffer();
		while(it.hasNext()){
			String key = it.next();
			Object value = data.get(key);
			if(value instanceof String){
				sb.append(key + "=" + value + "&");
			}
		}
		if(sb.length() != 0){
			return sb.substring(0, sb.length() - 1);
		}
		return null;
	}
}
