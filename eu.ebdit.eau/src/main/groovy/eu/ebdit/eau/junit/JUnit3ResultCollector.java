package eu.ebdit.eau.junit;

import java.util.Collection;
import java.util.Collections;

import junit.framework.Test;
import junit.runner.BaseTestRunner;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import eu.ebdit.eau.Collector;
import eu.ebdit.eau.Result;
import eu.ebdit.eau.util.Classes;

final class JUnit3ResultCollector extends BaseTestRunner implements
	Collector<Result> {

    private transient Result lastResult;

    private transient Collection<Result> results = Lists.newArrayList();

    private boolean canCollectFrom(final Object input) {
	return !Iterables.isEmpty(Classes.asClassIterable(input));
    }

    @Override
    public Iterable<Result> collectFrom(final Object input) {
	if (!canCollectFrom(input)) {
	    return Collections.emptyList();
	}
	reset();
	for (Class<?> clazz : Classes.asClassIterable(input)) {
	    final Test test = getTest(clazz.getName());
	    doRun(test);
	}
	return results;
    }

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
    public void testFailed(final int status, final Test test,
	    final Throwable trowable) {
	lastResult = Result.ofNames(lastResult.getSuiteName(), lastResult
		.getCheckName(), false, getFilteredTrace(trowable));
    }

    @Override
    // NOPMD
    public void testStarted(final String testName) {
	lastResult = Result.ofFullName(testName);
    }

    @Override
    protected void runFailed(final String message) { /* not needed */
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

    private void reset() {
	lastResult = null;
	results = Lists.newArrayList();
    }

}
