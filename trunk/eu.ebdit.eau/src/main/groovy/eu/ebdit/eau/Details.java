package eu.ebdit.eau;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Detailed description of given test. Tools can use {@link #value()} of this
 * annotation to show additional information about reported test. It should
 * also provide guidance to fix unsuccessful test.
 * 
 * @author Vladimír Oraný
 * @see Score#getDetails()
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Details {

    /** @returns additional information about annotated test */
    String value();
}
