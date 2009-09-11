package eu.ebdit.eau.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Test;

import eu.ebdit.eau.Report;
import eu.ebdit.eau.Reporter;

public abstract class BaseTestReporterTest {

    protected static final double EPSILON = 0.01;

    public BaseTestReporterTest() {
	super();
    }

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

    protected abstract Reporter getReporter() throws Exception;

}