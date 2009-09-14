package eu.ebdit.eau.junit;

import java.util.Collection;

import junit.framework.Test;
import junit.runner.BaseTestRunner;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import eu.ebdit.eau.Result;
import eu.ebdit.eau.ResultCollector;
import eu.ebdit.eau.beans.ResultBean;

public final class JUnit3ResultCollector extends BaseTestRunner implements
	ResultCollector {

    private JUnit3ResultCollector() {
	// prevents instance creation and subtyping
    }

    public static ResultCollector collectResults(final Class<?>... classes) {
	final JUnit3ResultCollector erl = new JUnit3ResultCollector();
	for (Class<?> clazz : classes) {
	    final Test test = erl.getTest(clazz.getName());
	    erl.doRun(test);
	}
	return erl;
    }

    private transient ResultBean lastResult;
    private final transient  Collection<Result> results = Lists
	    .newArrayList();

    public final junit.framework.TestResult doRun(final Test suite) {
	final junit.framework.TestResult result = createResult();
	suite.run(result);
	return result;
    }

    @Override
    // NOPMD
    public void testEnded(final String testName) {
	handleTestFinished();
    }

    @Override
    // NOPMD
    public void testStarted(final String testName) {
	lastResult = JUnitTestHelper.initResult();
	JUnitTestHelper.initNames(lastResult, testName);
    }

    @Override
    // NOPMD
    public void testFailed(final int status, final Test test,
	    final Throwable trowable) {
	JUnitTestHelper.handleStatus(lastResult, trowable);
	lastResult.setMessage(getFilteredTrace(trowable));
    }

    @Override
    protected void runFailed(final String message) { /* not needed */
    }

    @Override
    public final Iterable<Result> getResults() {
	return ImmutableList.copyOf(results);
    }

    private junit.framework.TestResult createResult() {
	final junit.framework.TestResult result = new junit.framework.TestResult();
	result.addListener(this);
	return result;
    }

    private void handleTestFinished() {
	results.add(lastResult);
	lastResult = null;
    }

}
