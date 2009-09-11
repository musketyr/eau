/**
 * 
 */
package eu.ebdit.eau.testing.xml

import eu.ebdit.eau.testing.TestScore
import eu.ebdit.eau.testing.TestResult
import eu.ebdit.eau.testing.beans.TestScoreBean



/**
 * @author Vladimir Orany
 *
 */
public class TestScoreXMLParser{
	
	List<TestScore> parse(toBeParsed){
		def points = new XmlParser().parse(toBeParsed);
		if (points.name() == 'points') {
			List<TestResult> ret = []
			points.'class'.'test'.each{
				ret << new TestScoreBean(
						classFQName: it.parent().'@name', 
						testName: it.'@name', 
						points: it.'@points'.toDouble(),
						bonus: it.'@bonus'? true : false, 
						message: it.'@message', 
						details: it.text()
				)
			}
			return ret
		}
		throw new IllegalArgumentException("Cannot parse $toBeParsed");
	}
	
}
