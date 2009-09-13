package eu.ebdit.eau.testing.junit;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import com.google.common.collect.Lists;

import eu.ebdit.eau.Status;
import eu.ebdit.eau.testing.TestResult;
import eu.ebdit.eau.testing.TestScore;
import eu.ebdit.eau.testing.annotations.Bonus;
import eu.ebdit.eau.testing.annotations.Details;
import eu.ebdit.eau.testing.annotations.Points;
import eu.ebdit.eau.testing.beans.TestResultBean;
import eu.ebdit.eau.testing.beans.TestScoreBean;

final class JUnitTestCollector extends RunListener implements TestCollector {

    private JUnitTestCollector() {
	// prevents instance creation and subtyping
    }

    public static TestCollector collectResults(final Class<?>... classes) {
	final JUnitTestCollector erl = new JUnitTestCollector();
	final JUnitCore core = new JUnitCore();
	core.addListener(erl);
	core.run(classes);
	return erl;
    }

    private static final Pattern PATTERN = Pattern.compile("(.*?)\\((.*?)\\)");

    private transient TestResultBean lastResult;
    private transient final List<TestResult> results = Lists.newArrayList();
    private transient final List<TestScore> scores = Lists.newArrayList();

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

    @Override //NOPMD
    public void testFailure(final Failure failure) throws Exception {//NOPMD
	handleFailure(failure);
    }

    @Override //NOPMD
    public void testFinished(final Description description) throws Exception {//NOPMD
	results.add(lastResult);
	lastResult = null;
    }

    @Override //NOPMD
    public void testStarted(final Description description) throws Exception {//NOPMD
	initResult();
	initNames(description);
	addScoreIfNeeded(description);
    }

    private void handleFailure(final Failure failure) {
	if (failure.getException() instanceof AssertionError) {
	    lastResult.setStatus(Status.FAILED);
	} else {
	    lastResult.setStatus(Status.ERROR);
	}
	lastResult.setMessage(failure.getMessage() == null ? failure.getTrace()
		: failure.getMessage());
    }

    private void addScoreIfNeeded(final Description description) {
	final TestScoreBean score = new TestScoreBean();
	score.setClassName(lastResult.getClassName());
	score.setTestName(lastResult.getTestName());
	final Points points = description.getAnnotation(Points.class);
	if (points != null) {
	    score.setPoints(points.value());

	    final eu.ebdit.eau.testing.annotations.Description desc = description
		    .getAnnotation(eu.ebdit.eau.testing.annotations.Description.class);
	    if (desc != null) {
		score.setMessage(desc.value());
	    }
	    final Details details = description.getAnnotation(Details.class);
	    if (details != null) {
		score.setDetails(details.value());
	    }
	    score.setBonus(description.getAnnotation(Bonus.class) != null);
	    scores.add(score);
	}
    }

    private void initNames(final Description description) throws AssertionError {
	final Matcher matcher = PATTERN.matcher(description.getDisplayName());
	if (matcher.matches()) {
	    lastResult.setClassName(matcher.group(2));
	    lastResult.setTestName(matcher.group(1));
	} else {
	    throw new AssertionError("Matcher must match!");
	}
    }

    private void initResult() {
	lastResult = new TestResultBean();
	lastResult.setStatus(Status.OK);
	lastResult.setMessage("");
    }

}
