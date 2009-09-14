package eu.ebdit.eau.testing.junit;

import org.junit.Test;

import eu.ebdit.eau.testing.TestReporterTest;
import eu.ebdit.eau.testing.TestResult;
import eu.ebdit.eau.testing.TestResultCollector;

public abstract class AbstractJUnitTestResultCollectorTest extends TestReporterTest {


    protected abstract TestResultCollector getTestCollector() throws ClassNotFoundException;


    @Override
    protected Iterable<TestResult> getResultList() throws ClassNotFoundException{
        return getTestCollector().getResults();
    }


    @Override
    @Test
    public void testSelfCreateResult() { /* not needed since we are not mocking */}//NOPMD

}