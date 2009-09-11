/**
 * 
 */
package eu.ebdit.eau.testing;

import java.io.File;
import java.util.List;

import eu.ebdit.eau.testing.xml.TestReportXMLParser;

/**
 * @author Vladimir Orany
 * 
 */
public class TestFromXMLTest extends TestReporterTest {

    protected List<TestResult> getResultList() throws Exception {
	return new TestReportXMLParser().parse(new File(getClass().getResource(
		"/TestClass.xml").toURI()));
    }

    @Override
    public void testSelfCreateResult() throws Exception {
	// do nothing
    }
}
