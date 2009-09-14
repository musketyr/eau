package eu.ebdit.eau.testing.xml
import java.lang.IllegalArgumentException
/**
 * @author Vladimir Orany
 *
 */
public class TestReportXMLParser{
	List<TestResult> parse(toBeParsed){
		def testsuite = new XmlParser().parse(toBeParsed);
		if (testsuite.name() == 'testsuite') {
			List<TestResult> ret = []
			testsuite.testcase.each{
				Status status = Status.OK
				String message = ''
				if (it.error){
					status = Status.ERROR
					message = it.error.text()
				}
				if (it.failure) {
					status = Status.FAILED
					message = it.failure.text()
				}
				ret << new TestResultBean(className: it.'@classname', testName: it.'@name', status: status, message: message)
			}
			return ret
		}
		throw new IllegalArgumentException("Cannot parse $toBeParsed");
	}
	
}