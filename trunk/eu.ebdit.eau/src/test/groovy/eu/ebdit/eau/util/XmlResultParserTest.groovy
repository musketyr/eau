/**
 * 
 */
package eu.ebdit.eau.util;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import eu.ebdit.eau.Result;
import eu.ebdit.eau.reports.TestReporterTest;

/**
 * @author Vladimir Orany
 * 
 */
public class XmlResultParserTest extends AbstractResultCollectorTest {

    protected Iterable<Result> getResults(){
	try {
	    return new XmlResultParser().collectFrom(new File(XmlResultParserTest.class
	    	.getResource("/TestClass.xml").toURI()));
	} catch (URISyntaxException e) {
	    Assert.fail(e.getMessage());
	    return null;
	}
    }

    @Test
    public void testCannotParse() throws URISyntaxException {
	assertFalse(new XmlResultParser().canCollectFrom(new File(
		XmlResultParserTest.class.getResource("/TestClass.points.eau.xml")
			.toURI())));

    }
}
