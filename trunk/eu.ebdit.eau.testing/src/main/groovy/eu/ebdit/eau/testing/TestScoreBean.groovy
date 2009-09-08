
package eu.ebdit.eau.testing

/**
 * @author Vladimir Orany
 *
 */
@Immutable public final class TestScoreBean implements TestScore{

	String classFQName
	String testName
	double points
	String message
	String details
	boolean bonus
}
