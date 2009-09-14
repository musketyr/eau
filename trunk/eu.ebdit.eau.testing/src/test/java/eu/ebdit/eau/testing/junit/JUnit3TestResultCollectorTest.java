package eu.ebdit.eau.testing.junit;

import eu.ebdit.eau.testing.TestReporterTest;
import eu.ebdit.eau.testing.TestResult;

public class JUnit3TestResultCollectorTest extends TestReporterTest {

    @Override
    protected Iterable<TestResult> getResultList() throws Exception {
	return JUnit3TestResultCollector.collectResults(Class.forName("org.example.TestClass")).getResults();
    }
    
    @Override //NOPMD
    public void testSelfCreateResult() {
        // not needed
    }
    
}
