package eu.ebdit.eau;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.ImmutableList;

import eu.ebdit.eau.beans.ScoreBean;
import eu.ebdit.eau.spi.Collector;

/**
 * Test for {@link Reporter}.
 * 
 * @author Vladimir Orany
 * 
 */
public final class ReporterTest {

    private static final int EXP_REPORTS_SIZE = 3;
    private static final double EXP_PERCENTAGE = 0.4;
    private static final double EXP_MAX_POINTS_WB = 2.25;
    private static final double EXP_MAX_POINTS = 1.25;
    private static final double EXP_POINTS = 0.5;
    private static final int SCORE_3_POINTS = 1;
    private static final double SCORE_2_POINTS = 0.75;
    private static final double SCORE_1_POINTS = 0.5;
    private static final double EPSILON = 0.01;
    private static final String CLASS_FQNAME = "org.example.TestClass";
    private static final String SCORE_1_MESSAGE = "Test one is for 0.5 points";
    private static final String SCORE_2_MESSAGE = null;
    private static final String SCORE_3_MESSAGE = "Bonus";
    private static final String METHOD_1_MESSAGE = "Everything was all right";
    private static final String METHOD_2_MESSAGE = "I've failed!";
    private static final String METHOD_3_MESSAGE = "Error occured!";
    private static final String METHOD_NAME_1 = "testOne";
    private static final String METHOD_NAME_2 = "testTwo";
    private static final String METHOD_NAME_3 = "testThree";

    /**
     * Creates new test of {@link Reporter}.
     */
    public ReporterTest() {
	super();
    }

    /**
     * Tests whether reporter returns expected result.
     */
    @Test
    public void testScoring() {
	final Reporter reporter = getReporter();
	final Report report = reporter.report(null);
	assertNotNull("Report is null!", report);
	assertNotNull("Description is null!", report.getDescription());
	assertEquals(EXP_POINTS, report.getPoints(), EPSILON);
	assertEquals(EXP_MAX_POINTS, report.getMaxPoints(), EPSILON); 
	assertEquals(EXP_MAX_POINTS_WB, report.getMaxPointsWithBonus(), 
		EPSILON);
	assertEquals(EXP_PERCENTAGE, report.getSuccessPercentage(), EPSILON); 
	assertEquals(EXP_REPORTS_SIZE, report.getReports().size()); // NOPMD
    }

    private Iterable<Result> getResultList() {
	return ImmutableList.of(createResult(CLASS_FQNAME, METHOD_NAME_1, true,
		METHOD_1_MESSAGE), createResult(CLASS_FQNAME, METHOD_NAME_2,
		false, METHOD_2_MESSAGE), createResult(CLASS_FQNAME,
		METHOD_NAME_3, false, METHOD_3_MESSAGE));
    }

    private Iterable<Score> getScoreList() {
	return ImmutableList.of(
		createScore(CLASS_FQNAME, METHOD_NAME_1, SCORE_1_POINTS, 
			SCORE_1_MESSAGE, false), 
		createScore(CLASS_FQNAME, METHOD_NAME_2, SCORE_2_POINTS,
			SCORE_2_MESSAGE, false), 
		createScore(CLASS_FQNAME, METHOD_NAME_3, SCORE_3_POINTS, 
			SCORE_3_MESSAGE, true));
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
    private Reporter getReporter() {
	final Collector<Score> scoreCollector = Mockito.mock(Collector.class);
	when(scoreCollector.collectFrom(Mockito.any())).thenReturn(
		getScoreList());
	final Collector<Result> resultCollector = Mockito.mock(Collector.class);
	when(resultCollector.collectFrom(Mockito.any())).thenReturn(
		getResultList());
	return Reporter.withResultCollectors(resultCollector)
		.withScoreCollectors(scoreCollector).build();
    }

}