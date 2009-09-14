package eu.ebdit.eau.testing.junit;

import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import com.google.common.collect.Lists;

import eu.ebdit.eau.Result;
import eu.ebdit.eau.Score;
import eu.ebdit.eau.testing.ResultCollector;
import eu.ebdit.eau.testing.beans.ResultBean;

final class JUnit4ResultCollector extends RunListener implements
	ResultCollector {

    private JUnit4ResultCollector() {
	// prevents instance creation and subtyping
    }

    public static ResultCollector collectResults(final Class<?>... classes) {
	final JUnit4ResultCollector erl = new JUnit4ResultCollector();
	final JUnitCore core = new JUnitCore();
	core.addListener(erl);
	core.run(classes);
	return erl;
    }

    private transient ResultBean lastResult;
    private final transient List<Result> results = Lists.newArrayList();
    private final transient List<Score> scores = Lists.newArrayList();

    /*
     * (non-Javadoc)
     * 
     * @see eu.ebdit.eau.testing.junit.TestCollector#getResults()
     */
    public Iterable<Result> getResults() {
	return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see eu.ebdit.eau.testing.junit.TestCollector#getScores()
     */
    public Iterable<Score> getScores() {
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
