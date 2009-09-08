/**
 * 
 */
package eu.ebdit.eau.testing.xml

import eu.ebdit.eau.testing.TestScore
import eu.ebdit.eau.testing.Result
import eu.ebdit.eau.testing.beans.TestScoreBean



/**
 * @author Vladimir Orany
 *
 */
public class TestScoreXMLParser{

	private final toBeParsed
	
	TestScoreXMLParser(def toBeParsed){
		this.toBeParsed = toBeParsed
	}
	
	List<TestScore> parse(){
		def points = new XmlParser().parse(toBeParsed);
		if (points.name() == 'points') {
			List<Result> ret = []
			points.'class'.'test'.each{
				ret << new TestScoreBean(
						classFQName: it.parent().'@name', 
						testName: it.'@name', 
						points: it.'@points'.toDouble(),
						bonus: it.'@bonus', 
						message: it.'@message', 
						detail: it.text()
				)
			}
			return ret
		}
		throw new IllegalArgumentException("Cannot parse $toBeParsed");
	}
	
}
