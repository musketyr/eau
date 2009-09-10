package eu.ebdit.eau.testing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import eu.ebdit.eau.Eau;
import eu.ebdit.eau.EauReport;

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
		Eau eau = getEva();
		EauReport er = eau.report();
		//System.out.println(er);
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
		Result r = createResult(CLASS_FQNAME, METHOD_NAME_1, Status.OK,
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
	
	protected Eau getEva() throws Exception{
		
		List<TestScore> scoreList = getScoreList();
		//System.out.println("score list:");
		//System.out.println(scoreList);
		List<Result> resultList = getResultList();
		//System.out.println("result list:");
		//System.out.println(resultList);
		return TestingEau.of(scoreList, resultList);
	}
	
	protected List<Result> getResultList() throws Exception {
		return ImmutableList.of(
				createResult(CLASS_FQNAME, METHOD_NAME_1, Status.OK, METHOD_1_MESSAGE), 
				createResult(CLASS_FQNAME, METHOD_NAME_2, Status.FAILED, METHOD_2_MESSAGE), 
				createResult(CLASS_FQNAME, METHOD_NAME_3, Status.ERROR, METHOD_3_MESSAGE));
	}
	
	protected List<TestScore> getScoreList() throws Exception {
		return ImmutableList.of(
				createTestScore(CLASS_FQNAME, METHOD_NAME_1, 0.5, SCORE_1_MESSAGE_OK, false), 
				createTestScore(CLASS_FQNAME, METHOD_NAME_2, 0.75, SCORE_2_MESSAGE_OK, false), 
				createTestScore(CLASS_FQNAME, METHOD_NAME_3, 1, SCORE_3_MESSAGE_OK, true));
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

	private Result createResult(String classFQName, String methodName,
			Status status, String message) {
		Result r1 = mock(Result.class);
		when(r1.getClassFQName()).thenReturn(classFQName);
		when(r1.getTestName()).thenReturn(methodName);
		when(r1.getStatus()).thenReturn(status);
		when(r1.getMessage()).thenReturn(message);
		return r1;
	}

}
