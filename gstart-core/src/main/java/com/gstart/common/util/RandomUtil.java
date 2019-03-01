package com.gstart.common.util;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.util.*;

public class RandomUtil extends RandomUtils{
	public static enum TYPE {
		/**
		 * 小写
		 */
		LETTER,
		/**
		 * 数字
		 */
		NUMBER,
		/**
		 * 大写
		 */
		CAPITAL,
		/**
		 * 符号
		 */
		SIGN,
		/**
		 * 小写+大写
		 */
		LETTER_CAPITAL,
		/**
		 * 小写+数字
		 */
		LETTER_NUMBER,
		/**
		 * 小写+字符
		 */
		LETTER_SIGN,
		/**
		 * 小写+字符+大写
		 */
		LETTER_CAPITAL_SIGN,
		/**
		 * 小写+大写+字符+数字
		 */
		LETTER_CAPITAL_SIGN_NUBER,
		/**
		 * 小写+大写+数字
		 */
		LETTER_CAPITAL_NUMBER
	}

	private static Random random = new Random();
	private static String[] lowercase = { "a", "b", "c", "d", "e", "f", "g",
			"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
			"u", "v", "w", "x", "y", "z" };

	private static String[] capital = { "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };

	private static String[] number = { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "0" };

	private static String[] sign = { "~", "!", "@", "#", "$", "%", "^", "&",
			"*", "(", ")", "_", "+", "`", "-", "=", "{", "}", "|", ":", "\"",
			"<", ">", "?", "[", "]", "\\", ";", "'", ",", ".", "/" };

	public static String getRandomString(int num, TYPE type) {
		List<String> temp = new ArrayList();
		if (type == TYPE.LETTER) {
			temp.addAll(Arrays.asList(lowercase));
		} else if (type == TYPE.CAPITAL) {
			temp.addAll(Arrays.asList(capital));
		} else if (type == TYPE.NUMBER) {
			temp.addAll(Arrays.asList(number));
		} else if (type == TYPE.SIGN) {
			temp.addAll(Arrays.asList(sign));
		} else if (type == TYPE.LETTER_CAPITAL) {
			temp.addAll(Arrays.asList(lowercase));
			temp.addAll(Arrays.asList(capital));
		} else if (type == TYPE.LETTER_NUMBER) {
			temp.addAll(Arrays.asList(lowercase));
			temp.addAll(Arrays.asList(number));
		} else if (type == TYPE.LETTER_CAPITAL_NUMBER) {
			temp.addAll(Arrays.asList(lowercase));
			temp.addAll(Arrays.asList(capital));
			temp.addAll(Arrays.asList(number));
		} else if (type == TYPE.LETTER_CAPITAL_SIGN_NUBER) {
			temp.addAll(Arrays.asList(lowercase));
			temp.addAll(Arrays.asList(capital));
			temp.addAll(Arrays.asList(number));
			temp.addAll(Arrays.asList(sign));
		}else if(type== TYPE.LETTER_CAPITAL_SIGN) {
			temp.addAll(Arrays.asList(lowercase));
			temp.addAll(Arrays.asList(capital));
			temp.addAll(Arrays.asList(sign));
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			sb.append(temp.get(random.nextInt(temp.size())));
		}
		return sb.toString();
	}


}
