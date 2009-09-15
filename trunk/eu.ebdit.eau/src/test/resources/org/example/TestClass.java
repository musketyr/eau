package org.example;


import junit.framework.TestCase;

import org.junit.Test;

import eu.ebdit.eau.Bonus;
import eu.ebdit.eau.Description;
import eu.ebdit.eau.Details;
import eu.ebdit.eau.Points;

// THIS CLASS IS FOR TESTING PURPOSE ONLY!
// IT IS NOT COMPILED AUTOMATICALLY
// RESULTING CLASS FILE MUST BE COPIED IT SRC/TEST/RESOURCES/ORG/EXAMPLE DIRECTORY
// MANUALLY
public class TestClass extends TestCase {

	
	@Points(0.5) @Description("Test one is for 0.5 points")
	@Test public void testOne() throws Exception {//NOPMD
		
	}
	
	@Points(0.75)
	@Test public void testTwo() throws Exception {//NOPMD
		assertTrue("I've failed", false);//NOPMD
	}
	
	@Bonus @Points(1) @Description("Bonus")
	@Details(
		"These are details explaining" +
		" why you have bonus points for this" +
		" test."
	)
	@Test public void testThree() throws Exception {//NOPMD
		throw new Exception();//NOPMD
	}
	
	@Test public void testDummy(){//NOPMD
		// ok :)
	}
	
}

