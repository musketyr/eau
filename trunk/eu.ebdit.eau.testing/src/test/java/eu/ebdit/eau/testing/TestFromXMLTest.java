/**
 * 
 */
package eu.ebdit.eau.testing;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;

import eu.ebdit.eau.testing.xml.TestReportXMLParser;

/**
 * @author Vladimir Orany
 * 
 */
public class TestFromXMLTest extends TestReporterTest {

    private static final String CANNOT_PARSE = "Cannot parse ";

    protected List<TestResult> getResultList() throws URISyntaxException {
	return new TestReportXMLParser().parse(new File(TestFromXMLTest.class
		.getResource("/TestClass.xml").toURI()));
    }

    @Test
    public void testCannotParse() throws Exception {
	try {
	    new TestReportXMLParser().parse(new File(TestFromXMLTest.class
		    .getResource("/TestClass.points.eau.xml").toURI()));
	    fail("Should throw exception");
	} catch (IllegalArgumentException exception) {
	    assertTrue(exception.getMessage().startsWith(CANNOT_PARSE));
	}

    }

    @Override
    @Test
    public void testSelfCreateResult() { // NOPMD
	/*
	 * we are not using mocking in this test so we don't need this test
	 */
    }
}
