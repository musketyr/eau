package eu.ebdit.eau.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.collect.Iterables;

public class ClassesTest {

    @Test
    public void testClassForName() throws Exception {
	assertNull(Classes.classForName(null));
	assertNull(Classes.classForName(new StringBuilder("no.such.Class")));
	assertEquals(System.class, Classes.classForName("java.lang.System"));
	assertEquals(System.class, Classes.classForName(new Object() {
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

}
