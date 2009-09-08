package eu.ebdit.eau.testing;

import java.util.List;



public class ScoreFromAnnotationTest extends BaseTest {

	@Override protected List<TestScore> getScoreList() throws Exception {
		ScoreAnnotationFinder finder = new ScoreAnnotationFinder();
		return finder.check("org.example.TestClass");
	}
	
}
