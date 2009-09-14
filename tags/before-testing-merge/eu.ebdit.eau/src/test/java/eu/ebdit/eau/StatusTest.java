package eu.ebdit.eau;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import eu.ebdit.eau.util.DefaultStatus;

public class StatusTest {

    @Test
    public void testIsOK() {
	assertTrue(DefaultStatus.OK.isOK());//NOPMD
	assertFalse(DefaultStatus.FAILED.isOK());//NOPMD
	assertFalse(DefaultStatus.ERROR.isOK());//NOPMD
    }

}
