package eu.ebdit.eau.util;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Ignore;
import org.junit.Test;

import eu.ebdit.eau.Score;
import eu.ebdit.eau.reports.TestReporterTest;

/**
 * @author Vladimir Orany
 * 
 */
public class ScoreFromXMLTest extends TestReporterTest {

    protected Iterable<Score> getScoreList() throws URISyntaxException {
	return new XmlScoreParser().collectFrom(new File(ScoreFromXMLTest.class
		.getResource("/TestClass.points.eau.xml").toURI()));
    }

    @Test
    public void testCannotParse() throws URISyntaxException {
	assertFalse(new XmlScoreParser().canCollectFrom(new File(
		ScoreFromXMLTest.class.getResource("/TestClass.xml").toURI())));

    }

    @Override
    @Test
    @Ignore
    public void testSelfCreateScore() { /*
					 * we are not using mocking in this
					 * class so we don't need this test
					 */
    } // NOPMD
}
