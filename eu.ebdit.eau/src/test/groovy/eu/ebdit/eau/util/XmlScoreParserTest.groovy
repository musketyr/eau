package eu.ebdit.eau.util;

import static org.junit.Assert.fail;

import java.io.File;
import java.net.URISyntaxException;

import eu.ebdit.eau.Score;

/**
 * @author Vladimir Orany
 * 
 */
public class XmlScoreParserTest extends AbstractScoreCollectorTest {

    protected Iterable<Score> getScores(){
	try {
	    return new XmlScoreParser().collectFrom(new File(XmlScoreParserTest.class
	    	.getResource("/TestClass.points.eau.xml").toURI()));
	} catch (URISyntaxException e) {
	    fail(e.getMessage());
	    return null;
	}
    }
}
