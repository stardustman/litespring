package org.litespring.beans.factory.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author yang'aoyun
 * @email 2652207782@qq.com
 * @date 2018年9月9日 下午3:56:05
 * @version 1.0
 * @description
 */
@Target({
	ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.METHOD,ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
	/**
	 * Declared whether the annotated dependency is required.
	 * <p>Defaults to {@code true}</p>
	 * @return
	 */
   boolean required() default true;
}
