/**
 * 
 */
package eu.ebdit.eau.testing



/**
 * @author Vladimir Orany
 *
 */
public class TestFromXMLTest extends BaseTest{
	
	protected List<Result> getResultList(){
		return new TestReportXMLParser(new File(getClass().getResource("TestClass.xml").toURI())).parse();
	}
}
