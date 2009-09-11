package eu.ebdit.eau.testing.junit;

import eu.ebdit.eau.testing.TestReporterTest;
import eu.ebdit.eau.testing.TestResult;
import eu.ebdit.eau.testing.TestScore;


public class JUnitTestCollectorTest extends TestReporterTest {
    
    private TestCollector collector;
    
    public JUnitTestCollectorTest() throws Exception {
	setUpListener();
    }
    
    public final void setUpListener() throws Exception{
	collector = JUnitTestCollector.collectResults(Class.forName("org.example.TestClass"));
    }
    
    
    @Override
    protected Iterable<TestResult> getResultList() throws Exception {
	return collector.getResults();
    }
    
    @Override
    protected Iterable<TestScore> getScoreList() throws Exception {
        return collector.getScores();
    }
    
    @Override
    public void testSelfCreateResult() throws Exception {}
    
    @Override
    public void testSelfCreateTestScore() throws Exception {}

}
