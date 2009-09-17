package eu.ebdit.eau.util
import java.lang.IllegalArgumentExceptionimport eu.ebdit.eau.Collector;import eu.ebdit.eau.Scoreimport eu.ebdit.eau.Result
/** * @author Vladimir Orany * */
public class XmlResultParser implements Collector<Result>{	
	Iterable<Result> collectFrom(toBeParsed){		List<Result> ret = []                Files.asFileIterable(toBeParsed).each {			def testsuite = new XmlParser().parse(it);			testsuite.testcase.each{				String message = null				boolean passed = true				if (it.error){					passed = false					message = it.error.text()				}				if (it.failure) {					passed = false					message = it.failure.text()				}				ret << new Result(suiteName: it.'@classname', checkName: it.'@name', success: passed, message: message)			}		}				return ret
	}	
}


