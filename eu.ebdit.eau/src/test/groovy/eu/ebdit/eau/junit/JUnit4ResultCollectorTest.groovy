package eu.ebdit.eau.junit;

import eu.ebdit.eau.Collector;
import eu.ebdit.eau.Result;

/**
 * Test for {@link JUnit4ResultCollector}.
 * 
 * @author Vladimir Orany
 *
 */
public class JUnit4ResultCollectorTest extends AbstractJUnitResultCollectorTest {
	
	protected Collector<Result> newCollector() {
		new JUnit4ResultCollector()
	}
	
}
