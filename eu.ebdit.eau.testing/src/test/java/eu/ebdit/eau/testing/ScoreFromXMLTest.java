package eu.ebdit.eau.testing;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;

import eu.ebdit.eau.testing.xml.TestScoreXMLParser;

/**
 * @author Vladimir Orany
 * 
 */
public class ScoreFromXMLTest extends TestReporterTest {

    protected List<TestScore> getScoreList() throws URISyntaxException {
	return new TestScoreXMLParser().parse(new File(ScoreFromXMLTest.class.getResource(
		"/TestClass.points.eau.xml").toURI()));
    }

    @Override @Test
    public void testSelfCreateTestScore() { /* we are not using mocking in this class so we don't need this test*/} //NOPMD
}
