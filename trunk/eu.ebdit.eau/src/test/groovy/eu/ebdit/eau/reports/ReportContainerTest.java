package eu.ebdit.eau.reports;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

import eu.ebdit.eau.Report;

/**
 * Tests {@link ReportContainer}.
 * @author Vladimir Orany
 *
 */
public final class ReportContainerTest {

    private static final int F2I_POINTS = 5;
    private static final int F1I_POINTS = 3;
    private static final int F2I_MAX_WB = 7;
    private static final int F1I_MAX_WB = 6;
    private static final double F2I_SUCCESS = 1.25;
    private static final double F1I_SUCCESS = 0.6;
    private static final int F2I_MAX_POINTS = 4;
    private static final int F1I_MAX_POINTS = 5;
    private static final String FIXTURE2_MESSAGE = "Fixture2";
    private static final String FIXTURE1_MESSAGE = "Fixture1";
    private static final double EPSILON = 0.01;
    private transient Report fixture1;
    private transient Report fixture2;
    private final transient Iterable<Report> fixture1Items = ImmutableList.of(
		getReport(2, 1, 2), 
		getReport(3, 2, 4));
    private final transient Iterable<Report> fixture2Items = ImmutableList.of(
		getReport(2, 3, 3),
		getReport(2, 2, 4));

    /**
     * Sets up fixtures.
     */
    @Before
    public void setUp() {
	fixture1 = ReportContainer.of(FIXTURE1_MESSAGE, fixture1Items);
	fixture2 = ReportContainer.of(FIXTURE2_MESSAGE, fixture2Items);
    }

    /**
     * Test for {@link ReportContainer#getMaxPoints()}.
     */
    @Test
    public void testMaxPoints() {
	assertEquals(F1I_MAX_POINTS, fixture1.getMaxPoints(), EPSILON); // NOPMD
	assertEquals(F2I_MAX_POINTS, fixture2.getMaxPoints(), EPSILON); // NOPMD
    }

    /**
     * Test for {@link ReportContainer#getSuccessPercentage()}.
     */
    @Test
    public void testSuccessPercentage() {
	assertEquals(F1I_SUCCESS, // NOPMD
		fixture1.getSuccessPercentage(), EPSILON);
	assertEquals(F2I_SUCCESS, // NOPMD
		fixture2.getSuccessPercentage(), EPSILON);
    }

    /**
     * Test for {@link ReportContainer#getMaxPoints()}.
     */
    @Test
    public void testMaxPointsWithBonuses() {
	assertEquals(F1I_MAX_WB, fixture1.getMaxPointsWithBonus(), EPSILON);
	assertEquals(F2I_MAX_WB, fixture2.getMaxPointsWithBonus(), EPSILON);
    }

    /**
     * Test for {@link ReportContainer#getPoints()}.
     */
    @Test
    public void testPoints() {
	assertEquals(F1I_POINTS, fixture1.getPoints(), EPSILON);
	assertEquals(F2I_POINTS, fixture2.getPoints(), EPSILON);
    }

    /**
     * Test for {@link ReportContainer#getReports()}.
     */
    @Test
    public void testReports() {
	assertEquals(fixture1Items, fixture1.getReports()); // NOPMD
	assertEquals(fixture2Items, fixture2.getReports()); // NOPMD
    }

    /**
     * Test for {@link ReportContainer#getDescription()}.
     */
    @Test
    public void testDescription() {
	assertEquals(FIXTURE1_MESSAGE, fixture1.getDescription()); // NOPMD
	assertEquals(FIXTURE2_MESSAGE, fixture2.getDescription()); // NOPMD
    }

    /**
     * Test for {@link ReportContainer#equals(Object)} and 
     * {@link ReportContainer#hashCode()}.
     */
    @Test
    public void testEqualsAndHashCode() {
	assertEquals(fixture1, ReportContainer.of(FIXTURE1_MESSAGE, // NOPMD
		fixture1Items));
	assertEquals(fixture1.hashCode(), // NOPMD
		ReportContainer.of(FIXTURE1_MESSAGE, 
		fixture1Items).hashCode());
    }

    private static Report getReport(final double maxPoints, final double points,
	    final double pointsWithBonuses) {
	final Report report = mock(Report.class);
	when(report.getMaxPoints()).thenReturn(maxPoints);
	when(report.getPoints()).thenReturn(points);
	when(report.getMaxPointsWithBonus()).thenReturn(pointsWithBonuses);
	return report;
    }

}
