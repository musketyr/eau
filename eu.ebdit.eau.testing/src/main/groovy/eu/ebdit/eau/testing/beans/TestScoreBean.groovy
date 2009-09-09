
package eu.ebdit.eau.testing.beans

import eu.ebdit.eau.testing.TestScore

/**
 * @author Vladimir Orany
 *
 */
@Immutable public final class TestScoreBean implements TestScore{

	boolean bonus
	double points
	String classFQName
	String testName
	String message
	String details
}
