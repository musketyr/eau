package eu.ebdit.eau.junit;

import eu.ebdit.eau.Collector;
import eu.ebdit.eau.Result;

public final class JUnitResultCollector implements Collector<Result> {

    private final Collector<Result> currentImplementation;
    
    public JUnitResultCollector() {
	this.currentImplementation = getCollector();
    }


    private Collector<Result> getCollector() {
	try {
	    Class.forName("org.junit.Test");
	    return new JUnit4ResultCollector();
	} catch (ClassNotFoundException e) {
	    return new JUnit3ResultCollector();
	}
    }
    
    @Override
    public Iterable<Result> collectFrom(final Object input) {
        return currentImplementation.collectFrom(input);
    }


}
