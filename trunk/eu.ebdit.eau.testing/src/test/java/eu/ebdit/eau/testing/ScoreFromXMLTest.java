package eu.ebdit.eau.testing;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

    private static final String CANNOT_PARSE = "Cannot parse ";

    protected List<TestScore> getScoreList() throws URISyntaxException {
	return new TestScoreXMLParser().parse(new File(ScoreFromXMLTest.class.getResource(
		"/TestClass.points.eau.xml").toURI()));
    }

    @Test
    public void testCannotParse() throws Exception {
	try {
	    new TestScoreXMLParser().parse(new File(TestFromXMLTest.class
		    .getResource("/TestClass.xml").toURI()));
	    fail("Should throw exception");
	} catch (IllegalArgumentException exception) {
	    assertTrue(exception.getMessage().startsWith(CANNOT_PARSE));
	}

    }
    
    @Override @Test
    public void testSelfCreateTestScore() { /* we are not using mocking in this class so we don't need this test*/} //NOPMD
}
