package org.litespring.beans.factory.config;

import org.litespring.beans.factory.BeanFactory;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月12日 下午6:44:17
 * @version 1.0
 * @description
 */
public interface ConfigurableBeanFactory extends BeanFactory{
	//设置Bean的classLoader
     void setBeanClassLoader(ClassLoader beanClassLoader);
     //得到Bean的ClassLoader
     ClassLoader getBeanClassLoader();
}
