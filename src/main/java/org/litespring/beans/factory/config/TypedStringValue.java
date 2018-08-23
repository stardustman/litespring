package org.litespring.beans.factory.config;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月22日 下午4:04:24
 * @version 1.0
 * @description
 */
public class TypedStringValue {
      private String value;
      public TypedStringValue(String value) {
	      this.value = value;
      }
      
      public String getValue() {
		return value;
	}
      public void setValue(String value) {
		this.value = value;
	}
      
}
