package eu.ebdit.eau.testing;

import java.util.List;

import eu.ebdit.eau.testing.annotations.ScoreAnnotationFinder;

public class ScoreFromAnnotationTest extends BaseTest {

    @Override
    protected List<TestScore> getScoreList() throws Exception {
	ScoreAnnotationFinder finder = new ScoreAnnotationFinder();
	return finder.check(Class.forName("org.example.TestClass"));
    }

    @Override
    public void testSelfCreateTestScore() throws Exception {
	// do nothing
    }

}
