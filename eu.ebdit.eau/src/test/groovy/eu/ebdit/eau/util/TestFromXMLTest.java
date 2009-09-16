/**
 * 
 */
package eu.ebdit.eau.util;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Ignore;
import org.junit.Test;

import eu.ebdit.eau.Result;
import eu.ebdit.eau.reports.TestReporterTest;

/**
 * @author Vladimir Orany
 * 
 */
public class TestFromXMLTest extends TestReporterTest {

    protected Iterable<Result> getResultList() throws URISyntaxException {
	return new XmlResultParser().collectFrom(new File(TestFromXMLTest.class
		.getResource("/TestClass.xml").toURI()));
    }

    @Test
    public void testCannotParse() throws URISyntaxException {
	assertFalse(new XmlResultParser().canCollectFrom(new File(
		TestFromXMLTest.class.getResource("/TestClass.points.eau.xml")
			.toURI())));

    }

    @Override
    @Test
    @Ignore
    public void testSelfCreateResult() { // NOPMD
	/*
	 * we are not using mocking in this test so we don't need this test
	 */
    }
}
