package eu.ebdit.eau.testing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import eu.ebdit.eau.Reporter;
import eu.ebdit.eau.Report;
import eu.ebdit.eau.Status;

public class BaseTest {

    private static final double EPSILON = 0.01;
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

    @Test
    public void testScoring() throws Exception {
	Reporter eauReporter = getReporter();
	Report er = eauReporter.report();
	// System.out.println(er);
	assertNotNull(er);
	assertNotNull(er.getMessage());
	assertEquals(0.5, er.getPoints(), EPSILON);
	assertEquals(1.25, er.getMaxPoints(), EPSILON);
	assertEquals(2.25, er.getMaxPointsBonusIncluded(), EPSILON);
	assertEquals(0.4, er.getSuccessPercentage(), EPSILON);
	assertEquals(3, er.getChildReports().size());
    }

    @Test
    public void testSelfCreateResult() throws Exception {
	TestResult r = createResult(CLASS_FQNAME, METHOD_NAME_1, Status.OK,
		METHOD_1_MESSAGE);
	assertEquals(CLASS_FQNAME, r.getClassFQName());
	assertEquals(METHOD_NAME_1, r.getTestName());
	assertEquals(Status.OK, r.getStatus());
	assertEquals(METHOD_1_MESSAGE, r.getMessage());
    }

    @Test
    public void testSelfCreateTestScore() throws Exception {
	TestScore ts = createTestScore(CLASS_FQNAME, METHOD_NAME_1, 0.5,
		SCORE_1_MESSAGE_OK, false);
	assertEquals(CLASS_FQNAME, ts.getClassFQName());
	assertEquals(METHOD_NAME_1, ts.getTestName());
	assertEquals(0.5, ts.getPoints(), EPSILON);
	assertEquals(SCORE_1_MESSAGE_OK, ts.getMessage());
	assertEquals(false, ts.isBonus());
    }

    protected Reporter getReporter() throws Exception {
	return TestReporter.of(getScoreList(), (List<TestResult>) getResultList());
    }

    protected Iterable<TestResult> getResultList() throws Exception {
	return ImmutableList.of(createResult(CLASS_FQNAME, METHOD_NAME_1,
		Status.OK, METHOD_1_MESSAGE), createResult(CLASS_FQNAME,
		METHOD_NAME_2, Status.FAILED, METHOD_2_MESSAGE), createResult(
		CLASS_FQNAME, METHOD_NAME_3, Status.ERROR, METHOD_3_MESSAGE));
    }

    protected Iterable<TestScore> getScoreList() throws Exception {
	return ImmutableList.of(createTestScore(CLASS_FQNAME, METHOD_NAME_1,
		0.5, SCORE_1_MESSAGE_OK, false), createTestScore(CLASS_FQNAME,
		METHOD_NAME_2, 0.75, SCORE_2_MESSAGE_OK, false),
		createTestScore(CLASS_FQNAME, METHOD_NAME_3, 1,
			SCORE_3_MESSAGE_OK, true));
    }

    private TestScore createTestScore(String classFQName, String testName,
	    double score, String message, boolean bonus) {
	TestScore ts = mock(TestScore.class);
	when(ts.getClassFQName()).thenReturn(classFQName);
	when(ts.getTestName()).thenReturn(testName);
	when(ts.getPoints()).thenReturn(score);
	when(ts.getMessage()).thenReturn(message);
	when(ts.isBonus()).thenReturn(bonus);
	return ts;
    }

    private TestResult createResult(String classFQName, String methodName,
	    Status status, String message) {
	TestResult r1 = mock(TestResult.class);
	when(r1.getClassFQName()).thenReturn(classFQName);
	when(r1.getTestName()).thenReturn(methodName);
	when(r1.getStatus()).thenReturn(status);
	when(r1.getMessage()).thenReturn(message);
	return r1;
    }

}
