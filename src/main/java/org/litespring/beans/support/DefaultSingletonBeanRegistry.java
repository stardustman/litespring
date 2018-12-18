package org.litespring.beans.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.text.StyledEditorKit.UnderlineAction;

import org.litespring.beans.factory.config.SingletonBeanFactory;
import org.litespring.util.Assert;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月12日 下午7:44:36
 * @version 1.0
 * @description 默认的单例注册类
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanFactory {
	private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(64);

	public void registerSingleton(String beanName, Object singletonObject) {
		Assert.notNull(beanName, "'beanName' must not be null");
		Object oldObject = this.singletonObjects.get(beanName);
		if (oldObject != null) {
			throw new IllegalStateException("Could not register object [" + singletonObject + "]" + "under bean name'"
					+ beanName + "': there is alreay object [" + oldObject + "]");
		}
		this.singletonObjects.put(beanName, singletonObject);

	}

	public Object getSingleton(String beanName) {
		return this.singletonObjects.get(beanName);
	}

}
