package eu.ebdit.eau.util;

import org.junit.Test;

import eu.ebdit.eau.util.TextUI;

public class JUnitTextUITest {

    @Test
    public void testRunMain() { // NOPMD
	TextUI.main("org.example.TestClass", "no.such.Class");
    }

}
