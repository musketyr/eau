package eu.ebdit.eau.testing.junit;

import java.util.List;

import eu.ebdit.eau.testing.BaseTest;
import eu.ebdit.eau.testing.TestResult;
import eu.ebdit.eau.testing.junit.EauJUnitRunner;


public class EauJUnitRunnerTest extends BaseTest {
    
    @Override
    public void testSelfCreateTestScore() throws Exception {
        // do nothing
    }
    
    @Override
    protected List<TestResult> getResultList() throws Exception {
	return new EauJUnitRunner().collectFrom(Class.forName("org.example.TestClass"));
    }

}
