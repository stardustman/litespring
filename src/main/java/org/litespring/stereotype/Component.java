package org.litespring.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
   
	/**
	 * The value may indicate a suggest for a logic component name,
	 * to be turned into a Spring bean in case of an auto detected component,
	 * @return the suggested component name, if any
	 */
	
	String value() default "";
}
