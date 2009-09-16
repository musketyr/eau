package eu.ebdit.eau.util;

import junit.framework.Assert;
import eu.ebdit.eau.Score;

public class ScoreAnnotationCollectorTest extends AbstractScoreCollectorTest {

    @Override
    protected Iterable<Score> getScores() {
	try {
	    return new ScoreAnnotationCollector().collectFrom(Class.forName("org.example.TestClass"));
	} catch (ClassNotFoundException e) {
	    Assert.fail(e.getMessage());
	    return null;
	}
    }

}
