package eu.ebdit.eau.testing.junit;

import org.junit.Test;

import eu.ebdit.eau.testing.TestReporterTest;
import eu.ebdit.eau.testing.TestResult;

public class JUnit3TestResultCollectorTest extends TestReporterTest {

    @Override
    protected Iterable<TestResult> getResultList() throws ClassNotFoundException {
	return JUnit3TestResultCollector.collectResults(Class.forName("org.example.TestClass")).getResults();
    }
    
    @Override @Test
    public void testSelfCreateResult() { //NOPMD
        // not needed
    }
    
}
