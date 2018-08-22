package org.litespring.utils;

import com.sun.org.apache.regexp.internal.recompile;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月22日 下午4:08:22
 * @version 1.0
 * @description
 */
public abstract class StringUtils {
     public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);		
	}

	private static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}
	
	public static boolean hasText(String str) {
		return hasText((CharSequence) str);
	}

	private static boolean hasText(CharSequence str) {
		if(!hasLength(str)){
			return false;
		}
		int strLen = str.length();
		
		for(int i = 0; i < strLen; i++){
			if(!Character.isWhitespace(str.charAt(i))){
				return true;
			}
		}
		return false;
	}
     
	
}
