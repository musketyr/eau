package eu.ebdit.eau.util;

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue

import eu.ebdit.eau.Score;

public abstract class AbstractScoreCollectorTest extends AbstractCollectorTest<Score>{
	
	private static final double EPSILON = 0.01
	
	protected final void assertCollectedItems(Iterable<Score> scores) {
		for (Score score : scores) {
			assertEquals("org.example.TestClass", score.suiteName)
			if (score.getCheckName() == 'testOne') {
				assertEquals(0.5, score.points, EPSILON)
				assertEquals("Test one is for 0.5 points", score.description)
				assertEquals(null, score.details)
				assertFalse(score.bonus)
			} else if (score.getCheckName() == 'testTwo') {
				assertEquals(0.75, score.points, EPSILON)
				assertEquals(null, score.description)
				assertEquals(null, score.details)
				assertFalse(score.bonus);
			} else if (score.getCheckName() == 'testThree') {
				assertEquals(1, score.points, EPSILON)
				assertEquals("Bonus", score.description)
				assertEquals(
						"These are details explaining" +
						" why you have bonus points for this" +
						" test.", score.details)
				assertTrue(score.bonus)
			} else {
				fail("No other names allowed")
			}
		}
		
	}
	
}
