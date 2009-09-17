package eu.ebdit.eau.util;

import static org.junit.Assert.*;
import org.junit.Test;


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
	    	.getResource("/TestClass.score.xml").toURI()));
	} catch (URISyntaxException e) {
	    fail(e.getMessage());
	    return null;
	}
    }

    @Test
    public void testInputs() throws Exception {
	XmlScoreParser parser = new XmlScoreParser();
	assertFalse parser.collectFrom("/TestClass.score.xml").isEmpty();
	assertTrue parser.collectFrom("org.example.TestClass").isEmpty();
	assertTrue parser.collectFrom("org/example/TestClass.java").isEmpty();
	assertTrue parser.collectFrom(XmlScoreParserTest.class).isEmpty();
	
    }
}
