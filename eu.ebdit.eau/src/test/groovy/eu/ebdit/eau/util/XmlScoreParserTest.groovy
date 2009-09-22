package eu.ebdit.eau.util;

import java.net.URL;

import eu.ebdit.eau.Score;
import eu.ebdit.eau.spi.Collector;


/**
 * @author Vladimir Orany
 * 
 */
public class XmlScoreParserTest extends AbstractScoreCollectorTest {
	
	protected Collector<Score> newCollector() {
		new XmlScoreParser()
	}
	
	@Override
	protected Object getInputForResults() {
		"/TestClass.score.xml";
	}
	
	@Override
	protected Iterable<Object> getInputsToFail() {
		[
		"/TestClass.score.xml",
		"org.example.TestClass",
		"org/example/TestClass.java",
		XmlScoreParserTest.class,
		]
	}
	
	@Override
	protected Iterable<Object> getInputsToSucceed() {
		URL resourceUrl = XmlScoreParserTest.class
		.getResource("/TestClass.score.xml")
		[
			"/TestClass.score.xml",
			new File(XmlScoreParserTest.class
				.getResource("/TestClass.score.xml").toURI()),
			resourceUrl,
			resourceUrl.toURI()
		]
	}
	
	protected Iterable<Score> getScores(){
		try {
			return new XmlScoreParser().collectFrom();
		} catch (URISyntaxException e) {
			fail(e.getMessage());
			return null;
		}
	}
}
