package eu.ebdit.eau.reports;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import eu.ebdit.eau.EauReport;

public class SimpleEauReportTest {

    private static final String F2_MESSAGE = "F2";
    private static final String F1_MESSAGE = "F1";
    private static final double EPSILON = 0.01;
    private static final String F2_DETAILS = "F2 DETAILS";
    private EauReport f1;
    private EauReport f2;

    @Before
    public void setUp() {
	f1 = new SimpleEauReport(F1_MESSAGE, 1, 1);
	f2 = new SimpleEauReport(F2_MESSAGE, 3, 2, 5, F2_DETAILS);
    }

    @Test
    public void testMaxPoints() throws Exception {
	assertEquals(1, f1.getMaxPoints(), EPSILON);
	assertEquals(2, f2.getMaxPoints(), EPSILON);
    }

    @Test
    public void testSuccessPercentage() throws Exception {
	assertEquals(1, f1.getSuccessPercentage(), EPSILON);
	assertEquals(3d / 2d, f2.getSuccessPercentage(), EPSILON);
    }

    @Test
    public void testMaxPointsWithBonuses() throws Exception {
	assertEquals(1, f1.getMaxPointsBonusIncluded(), EPSILON);
	assertEquals(5, f2.getMaxPointsBonusIncluded(), EPSILON);
    }

    @Test
    public void testPoints() throws Exception {
	assertEquals(1, f1.getPoints(), EPSILON);
	assertEquals(3, f2.getPoints(), EPSILON);
    }

    @Test
    public void testChildren() throws Exception {
	assertEquals(0, f1.getChildReports().size());
	assertEquals(0, f2.getChildReports().size());
    }

    @Test
    public void testMessage() throws Exception {
	assertEquals(F1_MESSAGE, f1.getMessage());
	assertEquals(F2_MESSAGE, f2.getMessage());
    }

    @Test
    public void testdETAILS() throws Exception {
	assertNull(f1.getDetails());
	assertEquals(F2_DETAILS, f2.getDetails());
    }

    @Test
    public void testEquals() throws Exception {
	assertEquals(new SimpleEauReport(F1_MESSAGE, 1, 2),
		new SimpleEauReport(F1_MESSAGE, 1, 2));
	assertEquals(new SimpleEauReport(F1_MESSAGE, 1, 2).hashCode(),
		new SimpleEauReport(F1_MESSAGE, 1, 2).hashCode());
    }

    @Test
    public void testToString() throws Exception {
	assertEquals(String.format("%06.2f%% of %06.2f (%06.2f) - "
		+ F1_MESSAGE, 100d, 1d, 1d), f1.toString());
	assertEquals(String.format("%06.2f%% of %06.2f (%06.2f) - "
		+ F2_MESSAGE, 150d, 2d, 5d), f2.toString());
    }

}
