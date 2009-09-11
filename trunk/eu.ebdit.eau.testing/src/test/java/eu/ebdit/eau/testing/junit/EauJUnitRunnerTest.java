package eu.ebdit.eau.testing.junit;

import eu.ebdit.eau.testing.BaseTest;
import eu.ebdit.eau.testing.TestResult;
import eu.ebdit.eau.testing.TestScore;


public class EauJUnitRunnerTest extends BaseTest {
    
    private EauRunListener listener;
    
    public EauJUnitRunnerTest() throws Exception {
	setUpListener();
    }
    
    public final void setUpListener() throws Exception{
	listener = new EauJUnitRunner().collectResults(Class.forName("org.example.TestClass"));
    }
    
    
    @Override
    protected Iterable<TestResult> getResultList() throws Exception {
	return listener.getResults();
    }
    
    @Override
    protected Iterable<TestScore> getScoreList() throws Exception {
        return listener.getScores();
    }
    
    @Override
    public void testSelfCreateResult() throws Exception {}
    
    @Override
    public void testSelfCreateTestScore() throws Exception {}

}
