package eu.ebdit.eau.testing.junit.textui;

import org.junit.Test;


public class JUnitTextUITest {

    @Test public void testRunMain(){//NOPMD
	JUnitTextUI.main("org.example.TestClass", "no.such.Class");
    }
    
}
