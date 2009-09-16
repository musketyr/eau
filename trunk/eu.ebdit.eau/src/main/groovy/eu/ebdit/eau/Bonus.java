package eu.ebdit.eau;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks test method as bonus one. Points from this method shall not be counted
 * into points maximum {@link Report#getMaxPoints()} but shall be reflected into
 * points maximum with bonus {@link Report#getMaxPointsWithBonus()}. Passing or
 * not passing test annotated with this annotation shall not change value of
 * success percentage {@link Report#getSuccessPercentage()}
 * 
 * @author Vladimír Oraný
 * @see Report#getMaxPointsWithBonus()
 * @see Score#isBonus()
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bonus {

}
