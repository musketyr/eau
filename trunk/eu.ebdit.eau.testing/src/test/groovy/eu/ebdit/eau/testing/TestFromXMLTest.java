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
public class TestFromXMLTest extends BaseTest{
	
	protected List<Result> getResultList() throws Exception{
		return new TestReportXMLParser().parse(new File(getClass().getResource("/TestClass.xml").toURI()));
	}
}
