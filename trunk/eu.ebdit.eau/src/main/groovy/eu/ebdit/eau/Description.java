package eu.ebdit.eau;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Meaningful description of the test. For example "Working addition" for test
 * checking whether calculator adds appropriately.
 * @author Vladimir Orany
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {
    /** @returns the description */
    String value();
}
