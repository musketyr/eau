package eu.ebdit.eau.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;

import eu.ebdit.eau.Score;
import eu.ebdit.eau.reports.TestReporterTest;
import eu.ebdit.eau.util.XmlScoreParser;

/**
 * @author Vladimir Orany
 * 
 */
public class ScoreFromXMLTest extends TestReporterTest {

    private static final String CANNOT_PARSE = "Cannot parse ";

    protected List<Score> getScoreList() throws URISyntaxException {
	return new XmlScoreParser().parse(new File(ScoreFromXMLTest.class.getResource(
		"/TestClass.points.eau.xml").toURI()));
    }

    @Test
    public void testCannotParse() throws URISyntaxException {
	try {
	    new XmlScoreParser().parse(new File(ScoreFromXMLTest.class.getResource(
		"/TestClass.xml").toURI()));
	    fail("Should throw exception");
	} catch (IllegalArgumentException exception) {
	    assertTrue(exception.getMessage().startsWith(CANNOT_PARSE));//NOPMD
	}

    }
    
    @Override @Test
    public void testSelfCreateScore() { /* we are not using mocking in this class so we don't need this test*/} //NOPMD
}
