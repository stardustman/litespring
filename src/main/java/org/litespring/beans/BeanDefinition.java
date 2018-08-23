package org.litespring.beans;

import java.util.List;

/**
 * 通过反射创建实例 bean definition属于内部概念,没必要让client知道
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年8月7日 下午12:35:49
 * @version 1.0
 * @description
 */
public interface BeanDefinition {
    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";
    public static final String SCOPE_DEFAULT = "singleton";
	public boolean isPrototype();
    public boolean isSingleton();
    String getScope();
    void setScope(String scope);
    
	public String getBeanClassName();
	
	public List<PropertyValue> getPropertyValues();

}
