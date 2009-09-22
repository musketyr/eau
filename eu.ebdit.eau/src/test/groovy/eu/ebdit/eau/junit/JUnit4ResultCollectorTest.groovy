package eu.ebdit.eau.junit;

import eu.ebdit.eau.Result;
import eu.ebdit.eau.spi.Collector;

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
