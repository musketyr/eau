package eu.ebdit.eau;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.common.base.Predicate;

/**
 * Use this annotation if you want to apply assignment only for particular inputs which
 * pass the filter constraint.
 * @author Vladimir Orany
 * @since 0.0.3
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InputFilter {
	/**
	 * @return predicate class which method apply determines whether or not the particular input
	 * should be assessed using annotated assignment
	 */
	Class<? extends Predicate<?>> value(); 
}
