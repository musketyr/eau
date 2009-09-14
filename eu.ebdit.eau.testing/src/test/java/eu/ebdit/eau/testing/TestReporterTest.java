package eu.ebdit.eau.testing;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import eu.ebdit.eau.Reporter;
import eu.ebdit.eau.Result;
import eu.ebdit.eau.Score;
import eu.ebdit.eau.Status;

public class TestReporterTest extends AbstractTestReporterTest {

    private static final String CLASS_FQNAME = "org.example.TestClass";
    private static final String SCORE_1_MESSAGE_OK = "Test one is for 0.5 points";//NOPMD
    private static final String SCORE_2_MESSAGE_OK = null;//NOPMD
    private static final String SCORE_3_MESSAGE_OK = "Bonus";//NOPMD
    private static final String METHOD_1_MESSAGE = "Everything was all right";
    private static final String METHOD_2_MESSAGE = "I've failed!";
    private static final String METHOD_3_MESSAGE = "Error occured!";
    private static final String METHOD_NAME_1 = "testOne";
    private static final String METHOD_NAME_2 = "testTwo";
    private static final String METHOD_NAME_3 = "testThree";

    @Test
    public void testSelfCreateResult() {
	final Result result = createResult(CLASS_FQNAME, METHOD_NAME_1, Status.OK,
		METHOD_1_MESSAGE);
	assertEquals(CLASS_FQNAME, result.getSuiteName());//NOPMD
	assertEquals(METHOD_NAME_1, result.getCheckName());//NOPMD
	assertEquals(Status.OK, result.getStatus());//NOPMD
	assertEquals(METHOD_1_MESSAGE, result.getMessage());//NOPMD
    }

    @Test
    public void testSelfCreateScore() {
	final Score score = createScore(CLASS_FQNAME, METHOD_NAME_1, 0.5,
		SCORE_1_MESSAGE_OK, false);
	assertEquals(CLASS_FQNAME, score.getSuiteName());//NOPMD
	assertEquals(METHOD_NAME_1, score.getCheckName());//NOPMD
	assertEquals(0.5, score.getPoints(), EPSILON);//NOPMD
	assertEquals(SCORE_1_MESSAGE_OK, score.getMessage());//NOPMD
	assertEquals(false, score.isBonus());//NOPMD
    }

    protected Iterable<Result> getResultList() throws Exception {//NOPMD
	return ImmutableList.of(createResult(CLASS_FQNAME, METHOD_NAME_1,
		Status.OK, METHOD_1_MESSAGE), createResult(CLASS_FQNAME,
		METHOD_NAME_2, Status.FAILED, METHOD_2_MESSAGE), createResult(
		CLASS_FQNAME, METHOD_NAME_3, Status.ERROR, METHOD_3_MESSAGE));
    }

    protected Iterable<Score> getScoreList() throws Exception {//NOPMD
	return ImmutableList.of(createScore(CLASS_FQNAME, METHOD_NAME_1,
		0.5, SCORE_1_MESSAGE_OK, false), createScore(CLASS_FQNAME,
		METHOD_NAME_2, 0.75, SCORE_2_MESSAGE_OK, false),
		createScore(CLASS_FQNAME, METHOD_NAME_3, 1,
			SCORE_3_MESSAGE_OK, true));
    }

    private Score createScore(final String className, final String testName,
	    final double score, final String message, final boolean bonus) {
	final Score Score = mock(Score.class);
	when(Score.getSuiteName()).thenReturn(className);
	when(Score.getCheckName()).thenReturn(testName);
	when(Score.getPoints()).thenReturn(score);
	when(Score.getMessage()).thenReturn(message);
	when(Score.isBonus()).thenReturn(bonus);
	return Score;
    }

    private Result createResult(final String className, final String methodName,
	    final Status status, final String message) {
	final Result result = mock(Result.class);
	when(result.getSuiteName()).thenReturn(className);
	when(result.getCheckName()).thenReturn(methodName);
	when(result.getStatus()).thenReturn(status);
	when(result.getMessage()).thenReturn(message);
	return result;
    }

    @Override
    protected Reporter getReporter() throws Exception {//NOPMD
        return TestReporter.of(getScoreList(), (List<Result>) getResultList());
    }

}
