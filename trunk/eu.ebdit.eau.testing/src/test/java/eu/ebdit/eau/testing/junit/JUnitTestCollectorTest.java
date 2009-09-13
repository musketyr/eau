package eu.ebdit.eau.testing.junit;

import org.junit.Test;

import eu.ebdit.eau.testing.TestReporterTest;
import eu.ebdit.eau.testing.TestResult;
import eu.ebdit.eau.testing.TestScore;


public class JUnitTestCollectorTest extends TestReporterTest {
    
    private transient TestCollector collector;
    
    public JUnitTestCollectorTest() throws ClassNotFoundException {
	setUpListener();
    }
    
    public final void setUpListener() throws ClassNotFoundException {
	collector = JUnitTestCollector.collectResults(Class.forName("org.example.TestClass"));
    }
    
    
    @Override
    protected Iterable<TestResult> getResultList() {
	return collector.getResults();
    }
    
    @Override
    protected Iterable<TestScore> getScoreList() {
        return collector.getScores();
    }
    
    @Override @Test
    public void testSelfCreateResult() { /* not needed since we are not mocking */}//NOPMD
    
    @Override @Test
    public void testSelfCreateTestScore() { /* not needed since we are not mocking */ }//NOPMD

}
