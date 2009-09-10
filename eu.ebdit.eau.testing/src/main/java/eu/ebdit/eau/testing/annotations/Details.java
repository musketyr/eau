package eu.ebdit.eau.testing.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Detailed description of given test.
 * 
 * @author Vladimir Orany
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Details {
    /** @returns detailed description of given test */
    String value();
}
