package eu.ebdit.eau.testing;

import java.util.List;

import eu.ebdit.eau.testing.annotations.ScoreAnnotationFinder;



public class ScoreFromAnnotationTest extends BaseTest {

	@Override protected List<TestScore> getScoreList() throws Exception {
		ScoreAnnotationFinder finder = new ScoreAnnotationFinder();
		return finder.check("org.example.TestClass");
	}
	
}