package eu.ebdit.eau.testing.annotations;

import java.util.List;

import org.junit.Test;

import eu.ebdit.eau.testing.TestReporterTest;
import eu.ebdit.eau.testing.TestScore;

public class ScoreFromAnnotationTest extends TestReporterTest {

    @Override
    protected List<TestScore> getScoreList() throws ClassNotFoundException {
	return new ScoreAnnotationFinder().check(Class.forName("org.example.TestClass"));
    }

    @Override @Test
    public void testSelfCreateTestScore() {/* we don't use mocking so we don't need this test at all */}//NOPMD

}
