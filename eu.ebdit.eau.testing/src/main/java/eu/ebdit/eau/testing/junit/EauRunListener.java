package eu.ebdit.eau.testing.junit;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import com.google.common.collect.Lists;

import eu.ebdit.eau.testing.Result;
import eu.ebdit.eau.testing.Status;
import eu.ebdit.eau.testing.beans.ResultBean;

public class EauRunListener extends RunListener{
    
    private static final Pattern PATTERN = Pattern.compile("(.*?)\\((.*?)\\)");
    
    private List<Result> results = Lists.newArrayList();
    private Status lastStatus;
    private String lastMessage;
    private String lastClassName = null;
    private String lastTestName = null;

    @Override
    public void testStarted(Description description) throws Exception {
        lastStatus = Status.OK;
        lastMessage = "";
	Matcher m = PATTERN.matcher(description.getDisplayName());
        if (m.matches()) {
            lastClassName = m.group(2);
            lastTestName = m.group(1);
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
	ResultBean bean = new ResultBean();
	bean.setClassFQName(lastClassName);
	bean.setTestName(lastTestName);
	bean.setStatus(lastStatus);
	bean.setMessage(lastMessage);
	results.add(bean);
    }

    public Iterable<Result> getResults() {
	return results;
    }

    private void handleFailure(Failure failure) {
        if (failure.getException() instanceof AssertionError) {
            lastStatus = Status.FAILED;
        } else {
            lastStatus = Status.ERROR;
        }
        lastMessage = failure.getMessage() == null ? failure.getTrace() : failure.getMessage();
    }
    
    
    
    
}
