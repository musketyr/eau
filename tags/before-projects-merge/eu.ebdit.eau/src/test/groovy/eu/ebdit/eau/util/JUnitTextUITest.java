package eu.ebdit.eau.util;

import org.junit.Test;

import eu.ebdit.eau.util.JUnitTextUI;


public class JUnitTextUITest {

    @Test public void testRunMain(){//NOPMD
	JUnitTextUI.main("org.example.TestClass", "no.such.Class");
    }
    
}
