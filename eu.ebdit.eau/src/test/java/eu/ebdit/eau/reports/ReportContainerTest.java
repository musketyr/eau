package eu.ebdit.eau.reports;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

import eu.ebdit.eau.Report;

public class ReportContainerTest {

    private static final String FIXTURE2_MESSAGE = "Fixture2";
    private static final String FIXTURE1_MESSAGE = "Fixture1";
    private static final double EPSILON = 0.01;
    private Report fixture1;
    private Report fixture2;
    private transient Iterable<Report> fixture1Items;
    private transient Iterable<Report> fixture2Items;

    @Before
    public void setUp() {
	fixture1Items = getFixture1Items();
	fixture1 = ReportContainer.of(FIXTURE1_MESSAGE, fixture1Items);
	fixture2Items = getFixture2Items();
	fixture2 = ReportContainer.of(FIXTURE2_MESSAGE, fixture2Items);
    }

    @Test
    public void testMaxPoints() {
	assertEquals(5, fixture1.getMaxPoints(), EPSILON);
	assertEquals(4, fixture2.getMaxPoints(), EPSILON);
    }

    @Test
    public void testSuccessPercentage() {
	assertEquals(3d / 5d, fixture1.getSuccessPercentage(), EPSILON);
	assertEquals(5d / 4d, fixture2.getSuccessPercentage(), EPSILON);
    }

    @Test
    public void testMaxPointsWithBonuses() {
	assertEquals(6, fixture1.getMaxPointsWithBonus(), EPSILON);
	assertEquals(7, fixture2.getMaxPointsWithBonus(), EPSILON);
    }

    @Test
    public void testPoints()  {
	assertEquals(3, fixture1.getPoints(), EPSILON);
	assertEquals(5, fixture2.getPoints(), EPSILON);
    }

    @Test
    public void testChildren() {
	assertEquals(fixture1Items, fixture1.getReports());//NOPMD
	assertEquals(fixture2Items, fixture2.getReports());//NOPMD
    }

    @Test
    public void testMessage() {
	assertEquals(FIXTURE1_MESSAGE, fixture1.getMessage());//NOPMD
	assertEquals(FIXTURE2_MESSAGE, fixture2.getMessage());//NOPMD
    }

    @Test
    public void testEqualsAndHashCode() {
	assertEquals(fixture1, ReportContainer.of(FIXTURE1_MESSAGE, //NOPMD
		fixture1Items));
	assertEquals(fixture1.hashCode(), ReportContainer.of(FIXTURE1_MESSAGE, //NOPMD
		fixture1Items).hashCode());
    }

    private Iterable<Report> getFixture1Items() {
	return ImmutableList.of(getReport(2, 1, 2), getReport(3, 2, 4));
    }

    private Iterable<Report> getFixture2Items() {
	return ImmutableList.of(getReport(2, 3, 3), getReport(2, 2, 4));
    }

    private Report getReport(final double maxPoints, final double points,
	    final double pointsWithBonuses) {
	final Report report = mock(Report.class);
	when(report.getMaxPoints()).thenReturn(maxPoints);
	when(report.getPoints()).thenReturn(points);
	when(report.getMaxPointsWithBonus()).thenReturn(pointsWithBonuses);
	return report;
    }
    
    public Report getFixture1() {
	return fixture1;
    }
    
    public Report getFixture2() {
	return fixture2;
    }

}
