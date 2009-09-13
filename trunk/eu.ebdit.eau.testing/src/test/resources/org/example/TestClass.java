package org.example;


import static org.junit.Assert.*;//NOPMD

import org.junit.Test;

import eu.ebdit.eau.testing.annotations.Bonus;
import eu.ebdit.eau.testing.annotations.Description;
import eu.ebdit.eau.testing.annotations.Points;

// THIS CLASS IS FOR TESTING PURPOSE ONLY!
// IT IS NOT COMPILED AUTOMATICALLY
// RESULTING CLASS FILE MUST BE COPIED IT SRC/TEST/RESOURCES/ORG/EXAMPLE DIRECTORY
// MANUALLY
public class TestClass {

	
	@Points(0.5) @Description("Test one is for 0.5 points")
	@Test public void testOne() throws Exception {//NOPMD
		
	}
	
	@Points(0.75)
	@Test public void testTwo() throws Exception {//NOPMD
		assertTrue("I've failed", false);//NOPMD
	}
	
	@Bonus @Points(1) @Description("Bonus")
	@Test public void testThree() throws Exception {//NOPMD
		throw new Exception();//NOPMD
	}
	
	@Test public void testDummy(){//NOPMD
		// ok :)
	}
	
}
