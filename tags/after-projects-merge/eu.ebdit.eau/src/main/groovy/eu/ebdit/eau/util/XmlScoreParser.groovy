/**
 * 
 */
package eu.ebdit.eau.util

import eu.ebdit.eau.Score
import eu.ebdit.eau.Result
import eu.ebdit.eau.beans.ScoreBean;



/**
 * @author Vladimir Orany
 *
 */
public class XmlScoreParser{
	
	List<Score> parse(toBeParsed){
		def points = new XmlParser().parse(toBeParsed);

		if (points.name() != 'score') {
		    throw new IllegalArgumentException("Cannot parse $toBeParsed");
		}
		
		List<Score> ret = []
		points.'suite'.'check'.each{
			ret << new ScoreBean(
			suiteName: it.parent().'@name', 
			checkName: it.'@name', 
			points: it.'@points'.toDouble(),
			bonus: it.'@bonus'? true : false, 
			message: it.'@message', 
			details: it.text()
			)
		}
		return ret
	}
	
}
