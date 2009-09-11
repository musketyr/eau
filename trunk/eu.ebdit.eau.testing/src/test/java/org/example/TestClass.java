package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.ebdit.eau.testing.annotations.Bonus;
import eu.ebdit.eau.testing.annotations.Description;
import eu.ebdit.eau.testing.annotations.Points;


public class TestClass {

	
	@Points(0.5) @Description("Test one is for 0.5 points")
	@Test public void testOne() throws Exception {
		
	}
	
	@Points(0.75)
	@Test public void testTwo() throws Exception {
		assertTrue("I've failed", false);
	}
	
	@Bonus @Points(1) @Description("Bonus")
	@Test public void testThree() throws Exception {
		throw new Exception();
	}
	
	@Test public void testDummy(){
		// ok :)
	}
	
}

