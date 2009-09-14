package eu.ebdit.eau.testing.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Number of points obtained whether the test is successful.
 * @author Vladimir Orany
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Points {
    /** @returns the number of points */
    double value();
}
