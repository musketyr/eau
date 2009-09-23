package eu.ebdit.eau.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.collect.Iterables;

import eu.ebdit.eau.Result;
import eu.ebdit.eau.Score;
import eu.ebdit.eau.junit.JUnitResultCollector;
import eu.ebdit.eau.spi.Collector;

public class ClassesTest {

    @Test
    public void testClassForName() throws Exception {
	assertNull(Classes.existingClass(null));
	assertNull(Classes.existingClass(new StringBuilder("no.such.Class")));
	assertEquals(System.class, Classes.existingClass("java.lang.System"));
	assertEquals(System.class, Classes.existingClass(new Object() {
	    @Override
	    public String toString() {
		return "java.lang.System";
	    }
	}));
    }

    @Test
    public void testAsClassIterable() {
	assertTrue(Iterables.isEmpty(Classes.asClassIterable(null)));
	assertTrue(Iterables.isEmpty(Classes.asClassIterable(new Object())));
	assertTrue(Iterables.isEmpty(Classes
		.asClassIterable(new Object[] { "Bla" })));
	assertTrue(Iterables
		.contains(Classes
			.asClassIterable(new Class<?>[] { System.class }),
			System.class));
	assertTrue(Iterables.contains(Classes
		.asClassIterable(new Object[] { System.class }), System.class));
	assertTrue(Iterables.contains(Classes.asClassIterable(System.class),
		System.class));
	assertTrue(Iterables.contains(Classes
		.asClassIterable("java.lang.System"), System.class));
	assertTrue(Iterables.contains(Classes.asClassIterable(new Object[] {
		System.class, "bla bla" }), System.class));
	assertEquals(1, Iterables.size(Classes.asClassIterable(new Object[] {
		System.class, "bla bla" })));
	assertTrue(Iterables.contains(Classes.asClassIterable(new Object[] {
		System.class, "java.lang.String" }), System.class));
	assertTrue(Iterables.contains(Classes.asClassIterable(new Object[] {
		System.class, "java.lang.String" }), String.class));
	assertEquals(2, Iterables.size(Classes.asClassIterable(new Object[] {
		System.class, "java.lang.String" })));
    }

    @Test
    public void testAsClassArray() {
	assertArrayEquals(new Class<?>[] {}, Classes.asClassArray(null));
	assertArrayEquals(new Class<?>[] {}, Classes
		.asClassArray(new Object[] { "Bla" }));
	assertArrayEquals(new Class<?>[] { System.class, String.class },
		Classes.asClassArray(new Object[] { System.class,
			"java.lang.String" }));
	assertArrayEquals(new Class<?>[] { String.class }, Classes
		.asClassArray(new Object[] { "java.lang.String" }));
	assertArrayEquals(new Class<?>[] { String.class }, Classes
		.asClassArray("java.lang.String"));
	assertArrayEquals(new Class<?>[] { String.class }, Classes
		.asClassArray(String.class));
	assertArrayEquals(new Class<?>[] { String.class }, Classes
		.asClassArray(new Object[] { "java.lang.String", "Bla bla" }));
    }
    
    @Test
    public void testSimilarTo() {
	assertTrue(Classes.isSimilarTo(null, XmlResultParser.class));
	assertTrue(Classes.isSimilarTo(null, null));
	assertFalse(Classes.isSimilarTo(JUnitResultCollector.class, null));
	assertTrue(Classes.isSimilarTo(JUnitResultCollector.class, 
		JUnitResultCollector.class));
	
	assertTrue(Classes.isSimilarTo(JUnitResultCollector.class, 
		Collectors.RESULT_COLLECTOR_FOOTPRINT.getClass()));
	assertFalse(Classes.isSimilarTo(JUnitResultCollector.class, 
		Collectors.SCORE_COLLECTOR_FOOTPRINT.getClass()));
	
	assertTrue(Classes.isSimilarTo(new JUnitResultCollector(), 
		Collectors.RESULT_COLLECTOR_FOOTPRINT.getClass()));
	assertFalse(Classes.isSimilarTo(new JUnitResultCollector(), 
		Collectors.SCORE_COLLECTOR_FOOTPRINT.getClass()));
	
	assertTrue(Classes.isSimilarTo(new JUnitResultCollector(), 
		Collectors.RESULT_COLLECTOR_FOOTPRINT));
	assertFalse(Classes.isSimilarTo(new JUnitResultCollector(), 
		Collectors.SCORE_COLLECTOR_FOOTPRINT));
	
	assertTrue(Classes.isSimilarTo(new JUnitResultCollector(), 
		new Collector<Result>() {

		    @Override
		    public Iterable<Result> collectFrom(final Object input) {
			return null;
		    }
		}));
	assertFalse(Classes.isSimilarTo(new JUnitResultCollector(), 
		new Collector<Score>() {

		    @Override
		    public Iterable<Score> collectFrom(final Object input) {
			return null;
		    }
		}));
	
    }

}
