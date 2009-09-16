package eu.ebdit.eau.util;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import eu.ebdit.eau.Score;

public abstract class AbstractScoreCollectorTest {
	
	private static final double EPSILON = 0.01
	
	protected abstract Iterable<Score> getScores();
	
	@Test
	public final void testScores() {
		Iterable<Score> scores = getScores()
		
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
