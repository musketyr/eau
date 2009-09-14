package eu.ebdit.eau.testing.junit;

import java.util.Collection;

import junit.framework.Test;
import junit.runner.BaseTestRunner;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import eu.ebdit.eau.testing.TestResult;
import eu.ebdit.eau.testing.TestResultCollector;
import eu.ebdit.eau.testing.beans.TestResultBean;

public final class JUnit3TestResultCollector extends BaseTestRunner implements
	TestResultCollector {

    private JUnit3TestResultCollector() {
	// prevents instance creation and subtyping
    }

    public static TestResultCollector collectResults(final Class<?>... classes) {
	final JUnit3TestResultCollector erl = new JUnit3TestResultCollector();
	for (Class<?> clazz : classes) {
	    Test test = erl.getTest(clazz.getName());
	    erl.doRun(test);
	}
	return erl;
    }

    private TestResultBean lastResult;
    private Collection<TestResult> results = Lists.newArrayList();

    public junit.framework.TestResult doRun(Test suite) {
	final junit.framework.TestResult result = createResult();
	suite.run(result);
	return result;
    }

    @Override
    public void testEnded(String testName) {
	handleTestFinished();
    }

    @Override
    public void testStarted(String testName) {
	lastResult = JUnitTestHelper.initResult();
	JUnitTestHelper.initNames(lastResult, testName);
    }

    @Override
    public void testFailed(int status, Test test, Throwable t) {
	JUnitTestHelper.handleStatus(lastResult, t);
	lastResult.setMessage(getFilteredTrace(t));
    }

    @Override
    protected void runFailed(String message) { /* not needed */}

    @Override
    public Iterable<TestResult> getResults() {
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
