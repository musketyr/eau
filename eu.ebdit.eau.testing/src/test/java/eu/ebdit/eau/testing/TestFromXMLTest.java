/**
 * 
 */
package eu.ebdit.eau.testing;

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

    protected List<TestResult> getResultList() throws URISyntaxException {
	return new TestReportXMLParser().parse(new File(getClass().getResource(
		"/TestClass.xml").toURI()));
    }

    @Override @Test
    public void testSelfCreateResult(){ /* we are not using mocking in this test so we don't need this test */ } //NOPMD
}
