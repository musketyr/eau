package eu.ebdit.eau.junit;

import eu.ebdit.eau.helper.ClassSuppressor;
import eu.ebdit.eau.Result;
import eu.ebdit.eau.spi.Collector;
import eu.ebdit.eau.util.AbstractResultCollectorTest;
import java.util.concurrent.Callable;

/**
 * Test for {@link JUnitResultCollector}.
 * 
 * @author Vladimir Orany
 *
 */
public class JUnitResultCollectorTest extends AbstractJUnitResultCollectorTest {
	
	@Override
	protected Collector<Result> newCollector(){
		ClassSuppressor suppressor = new ClassSuppressor()
			.suppressClass("org.junit.Test");
		return suppressor.runSuppressed({
			return new JUnitResultCollector()
			
		} as Callable<Iterable<Result>>)
	}
	
}
