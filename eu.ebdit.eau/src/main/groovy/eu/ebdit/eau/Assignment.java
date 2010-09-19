package eu.ebdit.eau;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marking annotation which can help client tools to find out all assignments on the classpath.
 * 
 * The annotation is not mandatory but some tools can depends on it.
 * @author Vladimir Orany
 * @since 0.0.2
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Assignment {
	
	/**
	 * @return the name or description of the assignment
	 */
	String value();
	
}
