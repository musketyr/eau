
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
	
	static TestScoreBean of(String className, String testName, double points, String message = '', boolean bonus = false, String details = ''){
		return new TestScoreBean(classFQName: className, testName: testName, points: points, message: message, bonus: bonus, details: details)
	}
	
}
