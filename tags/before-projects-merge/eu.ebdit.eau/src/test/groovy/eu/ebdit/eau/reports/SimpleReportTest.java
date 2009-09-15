package eu.ebdit.eau.reports;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import eu.ebdit.eau.Report;

public class SimpleReportTest {

    private static final String F2_MESSAGE = "F2";
    private static final String F1_MESSAGE = "F1";
    private static final double EPSILON = 0.01;
    private static final String F2_DETAILS = "F2 DETAILS";
    private Report fixture1;
    private Report fixture2;

    @Before
    public void setUp() {
	fixture1 = new SimpleReport(F1_MESSAGE, 1, 1);
	fixture2 = new SimpleReport(F2_MESSAGE, 3, 2, 5, F2_DETAILS);
    }

    @Test
    public void testMaxPoints()  {
	assertEquals(1, fixture1.getMaxPoints(), EPSILON);//NOPMD
	assertEquals(2, fixture2.getMaxPoints(), EPSILON);//NOPMD
    }

    @Test
    public void testSuccessPercentage() {
	assertEquals(1, fixture1.getSuccessPercentage(), EPSILON);//NOPMD
	assertEquals(3d / 2d, fixture2.getSuccessPercentage(), EPSILON);//NOPMD
	final SimpleReport sr1 = new SimpleReport(F1_MESSAGE, 0, 0, 1);//NOPMD
	assertEquals(0, sr1.getSuccessPercentage(), EPSILON);//NOPMD
	final SimpleReport sr2 = new SimpleReport(F1_MESSAGE, 1, 0, 1);//NOPMD
	assertEquals(1, sr2.getSuccessPercentage(), EPSILON);//NOPMD
	final SimpleReport sr3 = new SimpleReport(F1_MESSAGE, 0.5, 0, 1);//NOPMD
	assertEquals(0.5, sr3.getSuccessPercentage(), EPSILON);//NOPMD
    }

    @Test
    public void testMaxPointsWithBonuses() {
	assertEquals(1, fixture1.getMaxPointsWithBonus(), EPSILON);//NOPMD
	assertEquals(5, fixture2.getMaxPointsWithBonus(), EPSILON);//NOPMD
    }

    @Test
    public void testPoints(){
	assertEquals(1, fixture1.getPoints(), EPSILON);//NOPMD
	assertEquals(3, fixture2.getPoints(), EPSILON);//NOPMD
    }

    @Test
    public void testChildren(){
	assertEquals(0, fixture1.getReports().size());//NOPMD
	assertEquals(0, fixture2.getReports().size());//NOPMD
    }

    @Test
    public void testMessage(){
	assertEquals(F1_MESSAGE, fixture1.getMessage());//NOPMD
	assertEquals(F2_MESSAGE, fixture2.getMessage());//NOPMD
    }

    @Test
    public void testDetails(){
	assertNull(fixture1.getDetails());//NOPMD
	assertEquals(F2_DETAILS, fixture2.getDetails());//NOPMD
    }

    @Test
    public void testConstructors()  {
	final SimpleReport sr1 = new SimpleReport(F1_MESSAGE, 1, 2, 2, null);
	final SimpleReport sr2 = new SimpleReport(F1_MESSAGE, 1, 2, 2);
	final SimpleReport sr3 = new SimpleReport(F1_MESSAGE, 1, 2);
	final SimpleReport sr4 = new SimpleReport(F1_MESSAGE, 1, 2, null);
	assertEquals(sr1, sr2);//NOPMD
	assertEquals(sr1, sr3);//NOPMD
	assertEquals(sr1, sr4);//NOPMD
	assertEquals(sr2, sr3);//NOPMD
	assertEquals(sr2, sr4);//NOPMD
	assertEquals(sr3, sr4);//NOPMD
    }
    
    @Test
    public void testEquals() {
	assertEquals(new SimpleReport(F1_MESSAGE, 1, 2),//NOPMD
		new SimpleReport(F1_MESSAGE, 1, 2));
	assertEquals(new SimpleReport(F1_MESSAGE, 1, 2).hashCode(),//NOPMD
		new SimpleReport(F1_MESSAGE, 1, 2).hashCode());
    }

    @Test
    public void testToString() {
	assertEquals(String.format("%06.2f%% of %06.2f (%06.2f) - "//NOPMD
		+ F1_MESSAGE, 100d, 1d, 1d), fixture1.toString());
	assertEquals(String.format("%06.2f%% of %06.2f (%06.2f) - "//NOPMD
		+ F2_MESSAGE, 150d, 2d, 5d), fixture2.toString());
    }
    
    public Report getFixture1() {
	return fixture1;
    }
    
    public Report getFixture2() {
	return fixture2;
    }

}
