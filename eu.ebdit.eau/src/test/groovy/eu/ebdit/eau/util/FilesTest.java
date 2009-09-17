package eu.ebdit.eau.util;

import static org.junit.Assert.*;

import java.net.URISyntaxException;

import org.junit.Test;

import com.google.common.collect.Iterables;

/**
 * Test for class {@link Files}.
 */
public final class FilesTest {

    /**
     * Test for method {@link Files#existingFile(Object)}.
     */
    @Test
    public void testExistingFile() {
	assertNull(Files.existingFile("no-such.file"));
	assertNotNull(Files.existingFile("/TestClass.xml"));
    }

    /**
     * Test for method {@link Files#asFileIterable(Object)}.
     */
    @Test
    public void testAsFilesIterable() {
	assertTrue(Iterables.isEmpty(Files.asFileIterable("no-such.file")));
	assertTrue(Iterables.contains(Files.asFileIterable("/TestClass.xml"),
		Files.existingFile("/TestClass.xml")));
	assertTrue(Iterables.contains(Files.asFileIterable(Files
		.existingFile("/TestClass.xml")), Files
		.existingFile("/TestClass.xml")));
	assertTrue(Iterables.contains(Files.asFileIterable(FilesTest.class
		.getResource("/TestClass.xml")), Files
		.existingFile("/TestClass.xml")));
	try {
	    assertTrue(Iterables.contains(Files.asFileIterable(FilesTest.class
		    .getResource("/TestClass.xml").toURI()), Files
		    .existingFile("/TestClass.xml")));
	} catch (URISyntaxException e) {
	    fail(e.getMessage());
	}
	assertTrue(Iterables.contains(Files.asFileIterable(new String[] {
		"/TestClass.xml", "no-such.file" }), Files
		.existingFile("/TestClass.xml")));
    }

}
