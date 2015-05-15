package com.zebra.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import com.zebra.utils.exceptions.UtilException;

/**
 * 字符集工具类
 * @author xiaoleilu
 *
 */
public class CharsetUtil {
	
	public static final String ISO_8859_1 = "ISO-8859-1";
	public static final String UTF_8 = "UTF-8";
	public static final String GBK = "GBK";
	
	private CharsetUtil() {
		// 静态类不可实例化
	}
	
	/**
	 * 转换字符串的字符集编码
	 * @param source 字符串
	 * @param srcCharset 源字符集，默认ISO-8859-1
	 * @param newCharset 目标字符集，默认UTF-8
	 * @return 转换后的字符集
	 */
	public static String convert(String source, String srcCharset, String newCharset) {
		if(StringUtil.isBlank(srcCharset)) {
			srcCharset = ISO_8859_1;
		}
		
		if(StringUtil.isBlank(newCharset)) {
			srcCharset = UTF_8;
		}
		
		if (StringUtil.isBlank(source) || srcCharset.equals(newCharset)) {
			return source;
		}
		try {
			return new String(source.getBytes(srcCharset), newCharset);
		} catch (UnsupportedEncodingException unex) {
			throw new UtilException(unex);
		}
	}
	
	/**
	 * 将编码的byte数据转换为字符串<br>
	 * 已废弃，请使用StrUtil.decode
	 * @param data 数据
	 * @param charset 字符集，如果为空使用当前系统字符集
	 * @return 字符串
	 */
	@Deprecated
	public static String str(byte[] data, String charset) {
		if(data == null) {
			return null;
		}
		
		if(StringUtil.isBlank(charset)) {
			return new String(data);
		}
		
		try {
			return new String(data, charset);
		} catch (UnsupportedEncodingException e) {
			throw new UtilException(e);
		}
	}
	
	/**
	 * 将编码的byteBuffer数据转换为字符串
	 * @param data 数据
	 * @param charset 字符集，如果为空使用当前系统字符集
	 * @return 字符串
	 */
	public static String str(ByteBuffer data, String charset){
		if(data == null) {
			return null;
		}
		
		Charset cs;
		
		if(StringUtil.isBlank(charset)) {
			cs = Charset.defaultCharset();
		}else {
			cs = Charset.forName(charset);
		}
		
		return cs.decode(data).toString();
	}
	
	/**
	 * 字符串转换为byteBuffer
	 * @param str 字符串
	 * @param charset 编码
	 * @return byteBuffer
	 */
	public static ByteBuffer toByteBuffer(String str, String charset) {
		return ByteBuffer.wrap(StringUtil.encode(str, charset));
	}
}
