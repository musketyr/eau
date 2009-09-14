package eu.ebdit.eau.testing.junit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.ebdit.eau.Status;
import eu.ebdit.eau.testing.beans.TestResultBean;

final class JUnitTestHelper {

    private static final Pattern PATTERN = Pattern.compile("(.*?)\\((.*?)\\)");

    private JUnitTestHelper() {
	// prevents instance creation and subtyping
    }

    static TestResultBean handleStatus(final TestResultBean result,
	    final Throwable exception) {
	if (exception instanceof AssertionError) {
	    result.setStatus(Status.FAILED);
	} else {
	    result.setStatus(Status.ERROR);
	}
	return result;
    }

    static TestResultBean initNames(final TestResultBean result,
	    final String withClassName) {
	final Matcher matcher = PATTERN.matcher(withClassName);
	if (matcher.matches()) {
	    result.setClassName(matcher.group(2));
	    result.setTestName(matcher.group(1));
	} else {
	    throw new AssertionError("Matcher must match!");
	}
	return result;
    }

    static TestResultBean initResult() {
	final TestResultBean result = new TestResultBean();
	result.setStatus(Status.OK);
	result.setMessage("");
	return result;
    }
}
