package eu.ebdit.eau.junit;

import org.junit.Ignore;
import org.junit.Test;

import eu.ebdit.eau.spi.Collector;
import eu.ebdit.eau.Result;
import eu.ebdit.eau.junit.JUnit3ResultCollector;
import eu.ebdit.eau.util.AbstractResultCollectorTest;

/**
 * Abstract parent for JUnit result collectors. Sets up default values for
 * getInput* methods.
 * 
 * @author Vladimir Orany
 *
 */
public abstract class AbstractJUnitResultCollectorTest 
	extends AbstractResultCollectorTest {

    @Override
    protected final Object getInputForResults() {
	"org.example.TestClass"
    }

    @Override
    protected final Iterable<Object> getInputsToFail() {
        ["no.such.Class", "no-such.file"]
    }

    @Override
    protected final Iterable<Object> getInputsToSucceed() {
	"org.example.TestClass"
    }
    
}
