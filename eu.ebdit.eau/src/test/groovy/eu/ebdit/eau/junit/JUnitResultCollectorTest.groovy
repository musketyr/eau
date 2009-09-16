package eu.ebdit.eau.junit;

import eu.ebdit.eau.helper.ClassSuppressor;
import eu.ebdit.eau.Result;
import eu.ebdit.eau.util.AbstractResultCollectorTest;
import java.util.concurrent.Callable;

public class JUnitResultCollectorTest extends AbstractResultCollectorTest {
	
	@Override
	protected Iterable<Result> getResults(){
		ClassSuppressor suppressor = new ClassSuppressor()
			.suppressClass("org.junit.Test");
		return suppressor.runSuppressed({
			return new JUnitResultCollector().collectFrom(Class
			.forName("org.example.TestClass"));
			
		} as Callable<Iterable<Result>>)
	}
	
}
