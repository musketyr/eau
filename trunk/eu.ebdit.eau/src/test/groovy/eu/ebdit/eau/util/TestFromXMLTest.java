/**
 * 
 */
package eu.ebdit.eau.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import eu.ebdit.eau.Result;
import eu.ebdit.eau.reports.TestReporterTest;
import eu.ebdit.eau.util.XmlTestReportParser;

/**
 * @author Vladimir Orany
 * 
 */
public class TestFromXMLTest extends TestReporterTest {

    private static final String CANNOT_PARSE = "Cannot parse ";

    protected List<Result> getResultList() throws URISyntaxException {
	return new XmlTestReportParser().parse(new File(TestFromXMLTest.class
		.getResource("/TestClass.xml").toURI()));
    }

    @Test
    public void testCannotParse() throws URISyntaxException {
	try {
	    new XmlTestReportParser().parse(new File(TestFromXMLTest.class
		    .getResource("/TestClass.points.eau.xml").toURI()));
	    fail("Should throw exception");
	} catch (IllegalArgumentException exception) {
	    assertTrue(exception.getMessage().startsWith(CANNOT_PARSE));//NOPMD
	}

    }

    @Override
    @Test @Ignore
    public void testSelfCreateResult() { // NOPMD
	/*
	 * we are not using mocking in this test so we don't need this test
	 */
    }
}
