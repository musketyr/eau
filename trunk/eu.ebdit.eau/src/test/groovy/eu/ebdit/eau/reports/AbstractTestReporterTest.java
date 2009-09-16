package eu.ebdit.eau.reports;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Test;

import eu.ebdit.eau.Report;
import eu.ebdit.eau.Reporter;

public abstract class AbstractTestReporterTest {

    protected static final double EPSILON = 0.01;

    public AbstractTestReporterTest() {
	super();
    }

    @Test
    public void testScoring() throws Exception {//NOPMD
        final Reporter reporter = getReporter();
        final Report report = reporter.report();
        assertNotNull(report);//NOPMD
        assertNotNull(report.getDescription());//NOPMD
        assertEquals(0.5, report.getPoints(), EPSILON);//NOPMD
        assertEquals(1.25, report.getMaxPoints(), EPSILON);//NOPMD
        assertEquals(2.25, report.getMaxPointsWithBonus(), EPSILON);//NOPMD
        assertEquals(0.4, report.getSuccessPercentage(), EPSILON);//NOPMD
        assertEquals(3, report.getReports().size());//NOPMD
    }

    protected abstract Reporter getReporter() throws Exception;//NOPMD

}