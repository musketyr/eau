package eu.ebdit.eau.annotations;

import java.util.List;

import org.junit.Test;

import eu.ebdit.eau.Score;
import eu.ebdit.eau.reports.TestReporterTest;
import eu.ebdit.eau.util.ScoreAnnotationCollector;

public class ScoreFromAnnotationTest extends TestReporterTest {

    @Override
    protected List<Score> getScoreList() throws ClassNotFoundException {
	return new ScoreAnnotationCollector().check(Class.forName("org.example.TestClass"));
    }

    @Override @Test
    public void testSelfCreateScore() {/* we don't use mocking so we don't need this test at all */}//NOPMD

}
