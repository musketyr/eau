package eu.ebdit.eau.junit;

import org.junit.Test;

import eu.ebdit.eau.Result;
import eu.ebdit.eau.junit.JUnit3ResultCollector;
import eu.ebdit.eau.reports.TestReporterTest;

public class JUnit3ResultCollectorTest extends TestReporterTest {

    @Override
    protected Iterable<Result> getResultList() throws ClassNotFoundException {
	return JUnit3ResultCollector.collectResults(Class.forName("org.example.TestClass")).getResults();
    }
    
    @Override @Test
    public void testSelfCreateResult() { //NOPMD
        // not needed
    }
    
}
