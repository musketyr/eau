package eu.ebdit.eau.junit;

import eu.ebdit.eau.helper.ClassSuppressor;
import eu.ebdit.eau.Result;
import eu.ebdit.eau.util.AbstractResultCollectorTest;

public class JUnitResultCollectorTest extends AbstractResultCollectorTest {

    @Override
    protected Iterable<Result> getResults(){
	ClassSuppressor suppressor = new ClassSuppressor();
	suppressor.suppressClass("org.junit.Test");
	return suppressor.runSuppressed(new Callable<Iterable<Result>>() {
	    @Override
	    public Iterable<Result> call() throws Exception {
		return new JUnitResultCollector().collectFrom(Class
			.forName("org.example.TestClass"));
	    }
	});
    }

}
