package org.litespring.beans;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月22日 下午2:59:50
 * @version 1.0
 * @description
 */
public class PropertyValue {
	//bean 的<property name="itemDao" ref="itemDao"></property>
	private final String name;
	private final Object value;
	private boolean convertedValue = false; //beanRuntimeReference -> bean instance
	public PropertyValue(String name,Object value){
		this.name = name;
		this.value = value;
	}

	public boolean isConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(boolean convertedValue) {
		this.convertedValue = convertedValue;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}
	
	

}
