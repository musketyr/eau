package eu.ebdit.eau.junit;

import java.util.Collections;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import eu.ebdit.eau.Collector;
import eu.ebdit.eau.Result;
import eu.ebdit.eau.Score;
import eu.ebdit.eau.beans.ResultBean;
import eu.ebdit.eau.util.Classes;

final class JUnit4ResultCollector extends RunListener implements
	Collector<Result> {

    @Override
    public boolean canCollectFrom(final Object input) {
	return !Iterables.isEmpty(Classes.asClassIterable(input));
    }
    
    public Iterable<Result> collectFrom(final Object input) {
	if (!canCollectFrom(input)) {
	    return Collections.emptyList();
	}
	final JUnitCore core = new JUnitCore();
	core.addListener(this);
	core.run(Classes.asClassArray(input));
	return results;
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
	lastResult = new ResultBean();
	lastResult.init();
	lastResult.setFullName(description.getDisplayName());
    }
    
    private void handleFailure(final Failure failure) {
	lastResult.setPassed(false);
	lastResult.setMessage(failure.getMessage() == null ? failure.getTrace()
		: failure.getMessage());
    }

}
