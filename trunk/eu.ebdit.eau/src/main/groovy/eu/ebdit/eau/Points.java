package eu.ebdit.eau;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Number of points obtained if the test is successful. If you want the test to
 * be counted into reports you must always annotate it with this annotation.
 * Other way score collectors shall not collect information from such method.
 * 
 * @author Vladimír Oraný
 * @see Score#getPoints()
 * @see Report#getPoints()
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Points {
    /**
     * Number of points for successful test run. This number should be positive.
     * Absolute value is used if negative number is provided. Do not use zero as
     * a value. If value is zero then the test might be reflected as
     * unsuccessful in the reports an any occasion. The default value is
     * <code>1</code> giving all tests same weight.
     * 
     * @returns the number of points for successful test run
     */
    double value() default 1;
}
