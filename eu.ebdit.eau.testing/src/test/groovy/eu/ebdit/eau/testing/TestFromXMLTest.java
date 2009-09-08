/**
 * 
 */
package eu.ebdit.eau.testing;

import java.io.File;
import java.util.List;



/**
 * @author Vladimir Orany
 *
 */
public class TestFromXMLTest extends BaseTest{
	
	protected List<Result> getResultList() throws Exception{
		return new TestReportXMLParser(new File(getClass().getResource("/TestClass.xml").toURI())).parse();
	}
}
