/**
 * 
 */
package eu.ebdit.eau.util;

import java.net.URL;

import org.junit.Test;

import eu.ebdit.eau.Result;
import eu.ebdit.eau.spi.Collector;

/**
 * @author Vladimir Orany
 * 
 */
public class XmlResultParserTest extends AbstractResultCollectorTest {
	
	@Override
	protected Object getInputForResults() {
	    return "/TestClass.xml"
	}

	@Override
	protected Collector<Result> newCollector() {
	    return new XmlResultParser()
	}

	@Override
	protected Iterable<Object> getInputsToFail() {
		return ["/TestClass.score.xml"
		        ,"org.example.TestClass"
		        ,"org/example/TestClass.java"
		        ,XmlScoreParserTest.class]
	}

	@Override
	protected Iterable<Object> getInputsToSucceed() {
	    URL resourceUrl = XmlResultParserTest.class.getResource("/TestClass.xml");
	    return ["/TestClass.xml", 
    		        resourceUrl, 
    		        resourceUrl.toURI(),
    		        new File(resourceUrl.toURI()),
    		        ["/TestClass.xml", "no-such.file"]
	         ]
	}
	
	
}
