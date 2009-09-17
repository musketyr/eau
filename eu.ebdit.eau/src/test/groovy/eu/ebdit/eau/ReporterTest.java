package eu.ebdit.eau;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.google.common.collect.ImmutableList;

import eu.ebdit.eau.Report;
import eu.ebdit.eau.Reporter;
import eu.ebdit.eau.Result;
import eu.ebdit.eau.Score;
import eu.ebdit.eau.beans.ScoreBean;

public class ReporterTest {

    protected static final double EPSILON = 0.01;
    private static final String CLASS_FQNAME = "org.example.TestClass";
    private static final String SCORE_1_MESSAGE_OK = "Test one is for 0.5 points";
    private static final String SCORE_2_MESSAGE_OK = null;
    private static final String SCORE_3_MESSAGE_OK = "Bonus";
    private static final String METHOD_1_MESSAGE = "Everything was all right";
    private static final String METHOD_2_MESSAGE = "I've failed!";
    private static final String METHOD_3_MESSAGE = "Error occured!";
    private static final String METHOD_NAME_1 = "testOne";
    private static final String METHOD_NAME_2 = "testTwo";
    private static final String METHOD_NAME_3 = "testThree";

    public ReporterTest() {
	super();
    }

    @Test
    public void testScoring() throws Exception {// NOPMD
	final Reporter reporter = getReporter();
	final Report report = reporter.report(null);
	assertNotNull(report);// NOPMD
	assertNotNull(report.getDescription());// NOPMD
	assertEquals(0.5, report.getPoints(), EPSILON);// NOPMD
	assertEquals(1.25, report.getMaxPoints(), EPSILON);// NOPMD
	assertEquals(2.25, report.getMaxPointsWithBonus(), EPSILON);// NOPMD
	assertEquals(0.4, report.getSuccessPercentage(), EPSILON);// NOPMD
	assertEquals(3, report.getReports().size());// NOPMD
    }

    @Test
    public void testSelfCreateResult() {
	final Result result = createResult(CLASS_FQNAME, METHOD_NAME_1, true,
		METHOD_1_MESSAGE);
	assertEquals(CLASS_FQNAME, result.getSuiteName());// NOPMD
	assertEquals(METHOD_NAME_1, result.getCheckName());// NOPMD
	assertTrue(result.isSuccess());// NOPMD
	assertEquals(METHOD_1_MESSAGE, result.getMessage());// NOPMD
    }

    @Test
    public void testSelfCreateScore() {
	final Score score = createScore(CLASS_FQNAME, METHOD_NAME_1, 0.5,
		SCORE_1_MESSAGE_OK, false);
	assertEquals(CLASS_FQNAME, score.getSuiteName());// NOPMD
	assertEquals(METHOD_NAME_1, score.getCheckName());// NOPMD
	assertEquals(0.5, score.getPoints(), EPSILON);// NOPMD
	assertEquals(SCORE_1_MESSAGE_OK, score.getDescription());// NOPMD
	assertEquals(false, score.isBonus());// NOPMD
    }

    protected Iterable<Result> getResultList() throws Exception {// NOPMD
	return ImmutableList.of(createResult(CLASS_FQNAME, METHOD_NAME_1, true,
		METHOD_1_MESSAGE), createResult(CLASS_FQNAME, METHOD_NAME_2,
		false, METHOD_2_MESSAGE), createResult(CLASS_FQNAME,
		METHOD_NAME_3, false, METHOD_3_MESSAGE));
    }

    protected Iterable<Score> getScoreList() throws Exception {// NOPMD
	return ImmutableList.of(createScore(CLASS_FQNAME, METHOD_NAME_1, 0.5,
		SCORE_1_MESSAGE_OK, false), createScore(CLASS_FQNAME,
		METHOD_NAME_2, 0.75, SCORE_2_MESSAGE_OK, false), createScore(
		CLASS_FQNAME, METHOD_NAME_3, 1, SCORE_3_MESSAGE_OK, true));
    }

    private Score createScore(final String className, final String testName,
	    final double score, final String message, final boolean bonus) {
	final ScoreBean bean = new ScoreBean();
	bean.setBonus(bonus);
	bean.setSuiteName(className);
	bean.setCheckName(testName);
	bean.setDescription(message);
	bean.setPoints(score);
	return bean;
    }

    private Result createResult(final String className,
	    final String methodName, final boolean passed, final String message) {
	return Result.ofNames(className, methodName, passed, message);
    }

    @SuppressWarnings("unchecked")
    protected Reporter getReporter() throws Exception {// NOPMD
	Collector<Score> scoreCollector = Mockito.mock(Collector.class);
	when(scoreCollector.collectFrom(Mockito.any()))
	.thenReturn(getScoreList());
	Collector<Result> resultCollector = Mockito.mock(Collector.class);
	when(resultCollector.collectFrom(Mockito.any()))
	.thenReturn(getResultList());
	return Reporter
		.withResultCollectors(resultCollector)
		.withScoreCollectors(scoreCollector).build();
    }

}