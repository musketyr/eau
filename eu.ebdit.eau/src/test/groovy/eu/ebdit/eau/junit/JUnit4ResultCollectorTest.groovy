package eu.ebdit.eau.junit;

import org.junit.Ignore;
import org.junit.Test;

import eu.ebdit.eau.Result;
import eu.ebdit.eau.util.AbstractResultCollectorTest;




public class JUnit4ResultCollectorTest extends AbstractResultCollectorTest {
    

    @Override
    protected Iterable<Result> getResults() {
	return new JUnit4ResultCollector().collectFrom(Class.forName("org.example.TestClass"));
    }

}
