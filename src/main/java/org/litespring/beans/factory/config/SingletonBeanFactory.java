package org.litespring.beans.factory.config;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月12日 下午7:42:05
 * @version 1.0
 * @description 为了实现单例bean抽象出来的接口
 */
public interface SingletonBeanFactory {
	void registerSingleton(String beanName,Object singletonObject);
	Object getSingleton(String beanName);

}
