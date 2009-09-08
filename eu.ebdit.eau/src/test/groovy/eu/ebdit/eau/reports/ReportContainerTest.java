package eu.ebdit.eau.reports;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

import eu.ebdit.eau.EauReport;


public class ReportContainerTest {

	private static final String FIXTURE2_MESSAGE = "Fixture2";
	private static final String FIXTURE1_MESSAGE = "Fixture1";
	private static final double EPSILON = 0.01;
	private EauReport fixture1;
	private EauReport fixture2;
	private Iterable<EauReport> fixture1Items;
	private Iterable<EauReport> fixture2Items;

	@Before public void setUp(){
		fixture1Items = getFixture1Items();
		fixture1 = ReportContainer.of(FIXTURE1_MESSAGE, fixture1Items);
		fixture2Items = getFixture2Items();
		fixture2 = ReportContainer.of(FIXTURE2_MESSAGE, fixture2Items);
	}
	
	@Test
	public void testMaxPoints() throws Exception {
		assertEquals(5, fixture1.getMaxPoints(), EPSILON);
		assertEquals(4, fixture2.getMaxPoints(), EPSILON);
	}
	
	@Test
	public void testSuccessPercentage() throws Exception {
		assertEquals(3d/5d, fixture1.getSuccessPercentage(), EPSILON);
		assertEquals(5d/4d, fixture2.getSuccessPercentage(), EPSILON);
	}
	
	@Test
	public void testMaxPointsWithBonuses() throws Exception {
		assertEquals(6, fixture1.getMaxPointsBonusIncluded(), EPSILON);
		assertEquals(7, fixture2.getMaxPointsBonusIncluded(), EPSILON);
	}
	
	@Test
	public void testPoints() throws Exception {
		assertEquals(3, fixture1.getPoints(), EPSILON);
		assertEquals(5, fixture2.getPoints(), EPSILON);
	}
	
	@Test
	public void testChildren() throws Exception {
		assertEquals(fixture1Items, fixture1.getChildReports());
		assertEquals(fixture2Items, fixture2.getChildReports());
	}
	
	@Test
	public void testMessage() throws Exception {
		assertEquals(FIXTURE1_MESSAGE, fixture1.getMessage());
		assertEquals(FIXTURE2_MESSAGE, fixture2.getMessage());
	}
	
	@Test
	public void testEqualsAndHashCode() throws Exception {
		assertEquals(fixture1, ReportContainer.of(FIXTURE1_MESSAGE, fixture1Items));
		assertEquals(fixture1.hashCode(), ReportContainer.of(FIXTURE1_MESSAGE, fixture1Items).hashCode());
	}
	
	private Iterable<EauReport> getFixture1Items() {
		return ImmutableList.of(
				getReport(2, 1, 2),
				getReport(3, 2, 4)
		);
	}
	
	private Iterable<EauReport> getFixture2Items() {
		return ImmutableList.of(
				getReport(2, 3, 3),
				getReport(2, 2, 4)
		);
	}
	private EauReport getReport(double maxPoints, double points, double pointsWithBonuses){
		EauReport er = mock(EauReport.class);
		when(er.getMaxPoints()).thenReturn(maxPoints);
		when(er.getPoints()).thenReturn(points);
		when(er.getMaxPointsBonusIncluded()).thenReturn(pointsWithBonuses);
		return er;
	}
	
}
