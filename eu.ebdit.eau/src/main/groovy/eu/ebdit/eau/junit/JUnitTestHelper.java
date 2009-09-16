package eu.ebdit.eau.junit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.ebdit.eau.beans.ResultBean;

final class JUnitTestHelper {

    private static final Pattern PATTERN = Pattern.compile("(.*?)\\((.*?)\\)");

    private JUnitTestHelper() {
	// prevents instance creation and subtyping
    }

    static ResultBean handleStatus(final ResultBean result,
	    final Throwable exception) {
	result.setPassed(false);
	return result;
    }

    static ResultBean initNames(final ResultBean result,
	    final String withClassName) {
	final Matcher matcher = PATTERN.matcher(withClassName);
	if (matcher.matches()) {
	    result.setSuiteName(matcher.group(2));
	    result.setCheckName(matcher.group(1));
	} else {
	    throw new AssertionError("Matcher must match!");
	}
	return result;
    }

    static ResultBean initResult() {
	final ResultBean result = new ResultBean();
	result.setPassed(true);
	result.setMessage("");
	return result;
    }
}
