package eu.ebdit.eau.testing.junit;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import com.google.common.collect.Lists;

import eu.ebdit.eau.Status;
import eu.ebdit.eau.testing.TestResult;
import eu.ebdit.eau.testing.beans.TestResultBean;

public class EauRunListener extends RunListener{
    
    private static final Pattern PATTERN = Pattern.compile("(.*?)\\((.*?)\\)");
    
    private List<TestResult> results = Lists.newArrayList();
    private TestResultBean lastResult;

    @Override
    public void testStarted(Description description) throws Exception {
	lastResult = new TestResultBean();
	lastResult.setStatus(Status.OK);
        lastResult.setMessage("");
	Matcher m = PATTERN.matcher(description.getDisplayName());
        if (m.matches()) {
            lastResult.setClassFQName(m.group(2));
            lastResult.setTestName(m.group(1));
        } else {
            throw new AssertionError("Matcher must match!");
        }
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
	handleFailure(failure);
    }

    @Override
    public void testFinished(Description description) throws Exception {
	results.add(lastResult);
	lastResult = null;
    }

    public Iterable<TestResult> getResults() {
	return results;
    }

    private void handleFailure(Failure failure) {
        if (failure.getException() instanceof AssertionError) {
            lastResult.setStatus(Status.FAILED);
        } else {
            lastResult.setStatus(Status.ERROR);
        }
        lastResult.setMessage(failure.getMessage() == null ? failure.getTrace() : failure.getMessage());
    }
    
    
    
    
}
