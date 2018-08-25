package org.litespring.utils;


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
	
	 public static String trimWhitespace(String str)
	  {
	    if (!hasLength(str)) {
	      return str;
	    }
	    StringBuilder sb = new StringBuilder(str);
	    while ((sb.length() > 0) && (Character.isWhitespace(sb.charAt(0)))) {
	      sb.deleteCharAt(0);
	    }
	    while ((sb.length() > 0) && (Character.isWhitespace(sb.charAt(sb.length() - 1)))) {
	      sb.deleteCharAt(sb.length() - 1);
	    }
	    return sb.toString();
	  }
	 
	 public static String trimAllWhitespace(String str)
	  {
	    if (!hasLength(str)) {
	      return str;
	    }
	    StringBuilder sb = new StringBuilder(str);
	    int index = 0;
	    while (sb.length() > index) {
	      if (Character.isWhitespace(sb.charAt(index))) {
	        sb.deleteCharAt(index);
	      } else {
	        index++;
	      }
	    }
	    return sb.toString();
	  }
     
	
}
