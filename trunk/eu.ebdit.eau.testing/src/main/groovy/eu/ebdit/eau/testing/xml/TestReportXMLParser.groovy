package eu.ebdit.eau.testing.xml
import java.lang.IllegalArgumentExceptionimport eu.ebdit.eau.Scoreimport eu.ebdit.eau.Resultimport eu.ebdit.eau.beans.ResultBean;import eu.ebdit.eau.util.DefaultStatus;
/**
 * @author Vladimir Orany
 *
 */
public class TestReportXMLParser{
	List<Result> parse(toBeParsed){
		def testsuite = new XmlParser().parse(toBeParsed);
		if (testsuite.name() == 'testsuite') {
			List<Result> ret = []
			testsuite.testcase.each{
				DefaultStatus status = DefaultStatus.OK
				String message = ''
				if (it.error){
					status = DefaultStatus.ERROR
					message = it.error.text()
				}
				if (it.failure) {
					status = DefaultStatus.FAILED
					message = it.failure.text()
				}
				ret << new ResultBean(suiteName: it.'@classname', checkName: it.'@name', status: status, message: message)
			}
			return ret
		}
		throw new IllegalArgumentException("Cannot parse $toBeParsed");
	}
	
}
