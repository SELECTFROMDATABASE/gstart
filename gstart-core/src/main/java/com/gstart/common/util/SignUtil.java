package com.gstart.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 请求校验工具类
 * 
 * @author liufeng
 * @date 2013-09-01
 */
public class SignUtil {
	
    private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
        'e', 'f' };

	// 与开发模式接口配置信息中的Token保持一致
	private static String token = "gdkmwx";

	/**
	 * 校验签名
	 * 
	 * @param signature
	 *            微信加密签名
	 * @param timestamp
	 *            时间戳
	 * @param nonce
	 *            随机数
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp,
			String nonce) {
		// 对token、timestamp和nonce按字典排序
		String[] paramArr = new String[] { token, timestamp, nonce };
		Arrays.sort(paramArr);

		// 将排序后的结果拼接成一个字符串
		String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);

		String ciphertext = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// 对接后的字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			ciphertext = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// 将sha1加密后的字符串与signature进行对比
		return ciphertext != null ? ciphertext.equals(signature.toUpperCase())
				: false;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}

	public static String getSignature(String noncestr, String jsapi_ticket,
			long timestamp, String url) {
		String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr="
				+ noncestr + "&timestamp=" + Long.toString(timestamp)
				+ "&url=" + url ;
		String ciphertext = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// 对接后的字符串进行sha1加密
			byte[] digest = md.digest(string1.getBytes());
			ciphertext = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return ciphertext.toLowerCase();
	}
	
	   public static String md5(String input) {
	        if (input == null)
	            return null;

	        try {
	            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
	            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	            // 输入的字符串转换成字节数组
	            byte[] inputByteArray = input.getBytes("utf-8");
	            // inputByteArray是输入字符串转换得到的字节数组
	            messageDigest.update(inputByteArray);
	            // 转换并返回结果，也是字节数组，包含16个元素
	            byte[] resultByteArray = messageDigest.digest();
	            // 字符数组转换成字符串返回
	            return byteArrayToHex(resultByteArray);
	        } catch (NoSuchAlgorithmException e) {
	            return null;
	        } catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
	    }
	    private static String byteArrayToHex(byte[] byteArray) {
	        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
	        char[] resultCharArray = new char[byteArray.length * 2];
	        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
	        int index = 0;
	        for (byte b : byteArray) {
	            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
	            resultCharArray[index++] = hexDigits[b & 0xf];
	        }

	        // 字符数组组合成字符串返回
	        return new String(resultCharArray);

	    }
}
