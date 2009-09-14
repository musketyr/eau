package eu.ebdit.eau.testing.junit;

import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import com.google.common.collect.Lists;

import eu.ebdit.eau.testing.TestResult;
import eu.ebdit.eau.testing.TestResultCollector;
import eu.ebdit.eau.testing.TestScore;
import eu.ebdit.eau.testing.beans.TestResultBean;

final class JUnit4TestResultCollector extends RunListener implements
	TestResultCollector {

    private JUnit4TestResultCollector() {
	// prevents instance creation and subtyping
    }

    public static TestResultCollector collectResults(final Class<?>... classes) {
	final JUnit4TestResultCollector erl = new JUnit4TestResultCollector();
	final JUnitCore core = new JUnitCore();
	core.addListener(erl);
	core.run(classes);
	return erl;
    }

    private transient TestResultBean lastResult;
    private final transient List<TestResult> results = Lists.newArrayList();
    private final transient List<TestScore> scores = Lists.newArrayList();

    /*
     * (non-Javadoc)
     * 
     * @see eu.ebdit.eau.testing.junit.TestCollector#getResults()
     */
    public Iterable<TestResult> getResults() {
	return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see eu.ebdit.eau.testing.junit.TestCollector#getScores()
     */
    public Iterable<TestScore> getScores() {
	return scores;
    }

    @Override
    // NOPMD
    public void testFailure(final Failure failure) throws Exception {// NOPMD
	handleFailure(failure);
    }

    @Override
    // NOPMD
    public void testFinished(final Description description) throws Exception {// NOPMD
	handleTestFinished();
    }

    private void handleTestFinished() {
	results.add(lastResult);
	lastResult = null;
    }

    @Override
    // NOPMD
    public void testStarted(final Description description) throws Exception {// NOPMD
	lastResult = JUnitTestHelper.initResult();
	initNames(description);
    }

    private void initNames(final Description description){
	JUnitTestHelper.initNames(lastResult, description.getDisplayName());
    }

    private void handleFailure(final Failure failure) {
	final Throwable exception = failure.getException();
	JUnitTestHelper.handleStatus(lastResult, exception);
	lastResult.setMessage(failure.getMessage() == null ? failure.getTrace()
		: failure.getMessage());
    }

}
