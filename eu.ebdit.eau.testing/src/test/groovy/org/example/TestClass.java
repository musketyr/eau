package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.ebdit.eau.testing.annotations.Bonus;
import eu.ebdit.eau.testing.annotations.Description;
import eu.ebdit.eau.testing.annotations.Points;


public class TestClass {

	
	/* @Test */ @Points(0.5) @Description("Test one is for 0.5 points")
	public void testOne() throws Exception {
		
	}
	
	/* @Test */ @Points(0.75)
	public void testTwo() throws Exception {
		assertTrue(false);
	}
	
	/* @Test */@Bonus @Points(1) @Description("Bonus")
	public void testThree() throws Exception {
		throw new Exception();
	}
	
	@Test public void testDummy(){
		// ok :)
	}
	
}

