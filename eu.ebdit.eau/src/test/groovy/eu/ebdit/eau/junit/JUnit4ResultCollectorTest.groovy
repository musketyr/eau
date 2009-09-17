package eu.ebdit.eau.junit;

import eu.ebdit.eau.Collector;
import eu.ebdit.eau.Result;


public class JUnit4ResultCollectorTest extends AbstractJUnitResultCollectorTest {
	
	protected Collector<Result> newCollector() {
		new JUnit4ResultCollector()
	}
	
}
