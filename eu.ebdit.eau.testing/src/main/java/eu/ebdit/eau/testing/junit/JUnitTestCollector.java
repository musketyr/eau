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

class JUnitTestCollector extends RunListener implements TestCollector {

    private JUnitTestCollector() {
	// prevents instance creation and subtyping
    }

    public static TestCollector collectResults(Class<?>... classes) {
	JUnitTestCollector erl = new JUnitTestCollector();
	JUnitCore core = new JUnitCore();
	core.addListener(erl);
	core.run(classes);
	return erl;
    }

    private static final Pattern PATTERN = Pattern.compile("(.*?)\\((.*?)\\)");

    private TestResultBean lastResult;
    private List<TestResult> results = Lists.newArrayList();
    private List<TestScore> scores = Lists.newArrayList();

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
    public void testFailure(Failure failure) throws Exception {
	handleFailure(failure);
    }

    @Override
    public void testFinished(Description description) throws Exception {
	results.add(lastResult);
	lastResult = null;
    }

    @Override
    public void testStarted(Description description) throws Exception {
	initResult();
	initNames(description);
	addScoreIfNeeded(description);
    }

    private void handleFailure(Failure failure) {
	if (failure.getException() instanceof AssertionError) {
	    lastResult.setStatus(Status.FAILED);
	} else {
	    lastResult.setStatus(Status.ERROR);
	}
	lastResult.setMessage(failure.getMessage() == null ? failure.getTrace()
		: failure.getMessage());
    }

    private void addScoreIfNeeded(Description description) {
	TestScoreBean score = new TestScoreBean();
	score.setClassFQName(lastResult.getClassFQName());
	score.setTestName(lastResult.getTestName());
	Points points = description.getAnnotation(Points.class);
	if (points != null) {
	    score.setPoints(points.value());

	    eu.ebdit.eau.testing.annotations.Description desc = description
		    .getAnnotation(eu.ebdit.eau.testing.annotations.Description.class);
	    if (desc != null) {
		score.setMessage(desc.value());
	    }
	    Details details = description.getAnnotation(Details.class);
	    if (details != null) {
		score.setDetails(details.value());
	    }
	    score.setBonus(description.getAnnotation(Bonus.class) != null);
	    scores.add(score);
	}
    }

    private void initNames(Description description) throws AssertionError {
	Matcher m = PATTERN.matcher(description.getDisplayName());
	if (m.matches()) {
	    lastResult.setClassFQName(m.group(2));
	    lastResult.setTestName(m.group(1));
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
