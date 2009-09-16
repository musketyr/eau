package eu.ebdit.eau;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Short and meaningful description of the test. For example
 * "Working addition of numbers" for test checking whether calculator adds
 * appropriately. This description will be shown in output report as identifier
 * of test.
 * 
 * @author Vladimír Oraný
 * @see Score#getDescription()
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {
    /**
     * @returns the meaningful description of the test not longer than 100
     *          characters
     */
    String value();
}
