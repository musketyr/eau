package eu.ebdit.eau.util
import java.lang.IllegalArgumentExceptionimport eu.ebdit.eau.Scoreimport eu.ebdit.eau.Resultimport eu.ebdit.eau.beans.ResultBean;
/**
 * @author Vladimir Orany
 *
 */
public class XmlTestReportParser{
	List<Result> parse(toBeParsed){
		def testsuite = new XmlParser().parse(toBeParsed);
		if (testsuite.name() == 'testsuite') {
			List<Result> ret = []
			testsuite.testcase.each{
				String message = ''				boolean passed = true
				if (it.error){
					passed = false
					message = it.error.text()
				}
				if (it.failure) {
				    	passed = false
					message = it.failure.text()
				}
				ret << new ResultBean(suiteName: it.'@classname', checkName: it.'@name', passed: passed, message: message)
			}
			return ret
		}
		throw new IllegalArgumentException("Cannot parse $toBeParsed");
	}
	
}
