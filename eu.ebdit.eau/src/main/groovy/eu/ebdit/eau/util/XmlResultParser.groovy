package eu.ebdit.eau.util
import java.lang.IllegalArgumentExceptionimport eu.ebdit.eau.Collector;import eu.ebdit.eau.Scoreimport eu.ebdit.eau.Resultimport eu.ebdit.eau.beans.ResultBean;
/** * @author Vladimir Orany * */
public class XmlResultParser implements Collector<Result>{		boolean canCollectFrom(Object[] toBeParsed){	    return toBeParsed.every{ 		new XmlParser().parse(it).name() == 'testsuite'	    };	}	
	Iterable<Result> collectFrom(Object[] toBeParsed){
		if (!canCollectFrom(toBeParsed)) {			return  [];		}		List<Result> ret = []		toBeParsed.each {			def testsuite = new XmlParser().parse(it);			testsuite.testcase.each{				String message = ''				boolean passed = true				if (it.error){					passed = false					message = it.error.text()				}				if (it.failure) {					passed = false					message = it.failure.text()				}				ret << new ResultBean(suiteName: it.'@classname', checkName: it.'@name', passed: passed, message: message)			}		}				return ret
	}
}


