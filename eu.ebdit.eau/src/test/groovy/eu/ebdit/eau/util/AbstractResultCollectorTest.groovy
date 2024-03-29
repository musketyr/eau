package eu.ebdit.eau.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import eu.ebdit.eau.Result;


public abstract class AbstractResultCollectorTest extends AbstractCollectorTest<Result> {
	
	private static final double EPSILON = 0.01
	
	private static final String TEST_3_FAILURE_START = '''
	    java.lang.Exception
		at org.example.TestClass.testThree(TestClass.java:
	'''
	
	protected final void assertCollectedItems(Iterable<Result> results) {
		for (Result result : results) {
			assertEquals("org.example.TestClass", result.suiteName)
			if (result.getCheckName() == 'testOne'
			|| result.checkName == 'testDummy'
			) {
				assertEquals(null, result.message)
				assertTrue(result.isSuccess())
			} else if (result.getCheckName() == 'testTwo') {
				assertTrue(result.message.contains("I've failed"));
				assertFalse(result.isSuccess())
			} else if (result.getCheckName() == 'testThree') {
				assertTrue(
					"Should start with \n\n${cleanUpWhitespaces(TEST_3_FAILURE_START)}\n" +
						" but was \n\n${cleanUpWhitespaces(result.message)}",
						cleanUpWhitespaces(result.message).startsWith(
						cleanUpWhitespaces(TEST_3_FAILURE_START)))
				assertFalse(result.isSuccess())
			}  else {
				fail("No other names allowed")
			}
		}
	}
	
	private String cleanUpWhitespaces(String original){
		return original.trim().replaceAll(/\s\s+/, '\n')
	}
	
}
