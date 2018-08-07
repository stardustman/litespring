package org.litespring.beans.support;

import org.litespring.beans.BeanDefinition;
/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月7日 下午1:01:32
 * @version 1.0
 * @description 获取bean 注册bean
 */
public interface BeanDefinitionRegistry {
    BeanDefinition getBeanDefinition(String beanID);
	void registerBeanDefinition(String beanID, BeanDefinition bd);

}
