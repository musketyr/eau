package eu.ebdit.eau.util
import java.lang.IllegalArgumentExceptionimport eu.ebdit.eau.Collector;import eu.ebdit.eau.Scoreimport eu.ebdit.eau.Resultimport eu.ebdit.eau.beans.ResultBean;
/** * @author Vladimir Orany * */
public class XmlResultParser implements Collector<Result>{		boolean canCollectFrom(toBeParsed){	    return normalize(toBeParsed).every{ 		new XmlParser().parse(it).name() == 'testsuite'	    };	}	
	Iterable<Result> collectFrom(toBeParsed){
		if (!canCollectFrom(toBeParsed)) {			return  [];		}		List<Result> ret = []                normalize(toBeParsed).each {			def testsuite = new XmlParser().parse(it);			testsuite.testcase.each{				String message = null				boolean passed = true				if (it.error){					passed = false					message = it.error.text()				}				if (it.failure) {					passed = false					message = it.failure.text()				}				ret << new ResultBean(suiteName: it.'@classname', checkName: it.'@name', success: passed, message: message)			}		}				return ret
	}	private normalize(toBeParsed){	    if (toBeParsed.getClass().isArray() || (toBeParsed instanceof Iterable)) {		return toBeParsed	    }	    return [toBeParsed]	}	
}


