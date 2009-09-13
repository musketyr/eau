package eu.ebdit.eau;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StatusTest {

    @Test
    public void testIsOK() {
	assertTrue(Status.OK.isOK());//NOPMD
	assertFalse(Status.FAILED.isOK());//NOPMD
	assertFalse(Status.ERROR.isOK());//NOPMD
    }

}
