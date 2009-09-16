/**
 * 
 */
package eu.ebdit.eau.util

import eu.ebdit.eau.Collector;
import eu.ebdit.eau.Score
import eu.ebdit.eau.Result
import eu.ebdit.eau.beans.ScoreBean;



/**
 * @author Vladimir Orany
 *
 */
public class XmlScoreParser implements Collector<Score>{
	
	boolean canCollectFrom(toBeParsed){
		return normalize(toBeParsed).every{ new XmlParser().parse(it).name() == 'score'};
	}
	
	Iterable<Score> collectFrom(toBeParsed){
		if (!canCollectFrom(toBeParsed)){
			return [];
		}
		List<Score> ret = []
		normalize(toBeParsed).each {
			def points = new XmlParser().parse(it);
			
			points.'suite'.'check'.each{
				ret << new ScoreBean(
				suiteName: it.parent().'@name', 
				checkName: it.'@name', 
				points: it.'@points'.toDouble(),
				bonus: it.'@bonus'? true : false, 
				message: it.'@message', 
				details: it.text() ? it.text() : null
				)
			}
			
		}
		
		return ret
	}
	
	private normalize(toBeParsed){
		if (toBeParsed.getClass().isArray() || (toBeParsed instanceof Iterable)) {
			return toBeParsed
		}
		return [toBeParsed]
	}
}
