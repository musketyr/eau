package eu.ebdit.eau.junit;

import org.junit.Ignore;
import org.junit.Test;

import eu.ebdit.eau.Result;
import eu.ebdit.eau.reports.TestReporterTest;




public class JUnit4ResultCollectorTest extends TestReporterTest {
    

    @Override
    protected Iterable<Result> getResultList() throws ClassNotFoundException {
	return JUnit4ResultCollector.collectResults(Class.forName("org.example.TestClass")).getResults();
    }
    
    @Override @Test @Ignore
    public void testSelfCreateResult() {//NOPMD
        // not needed
    }

}
