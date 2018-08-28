package org.litespring.beans;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月25日 下午6:30:54
 * @version 1.0
 * @description 类型转换器
 */
public interface TypeConverter {
	//<T> 由传进来的requireType决定
   <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;
}
