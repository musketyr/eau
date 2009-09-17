package eu.ebdit.eau.junit;

import org.junit.Ignore;
import org.junit.Test;

import eu.ebdit.eau.Collector;
import eu.ebdit.eau.Result;
import eu.ebdit.eau.junit.JUnit3ResultCollector;
import eu.ebdit.eau.util.AbstractResultCollectorTest;

public abstract class AbstractJUnitResultCollectorTest extends AbstractResultCollectorTest {

    @Override
    protected Object getInputForResults() {
	"org.example.TestClass"
    }

    @Override
    protected Iterable<Object> getInputsToFail() {
        ["no.such.Class", "no-such.file"]
    }

    @Override
    protected Iterable<Object> getInputsToSucceed() {
	"org.example.TestClass"
    }
    
}
