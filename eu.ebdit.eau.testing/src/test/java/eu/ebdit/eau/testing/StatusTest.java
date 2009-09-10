package eu.ebdit.eau.testing;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatusTest {

    @Test
    public void testIsOK() {
	assertTrue(Status.OK.isOK());
	assertFalse(Status.FAILED.isOK());
	assertFalse(Status.ERROR.isOK());
    }

}
