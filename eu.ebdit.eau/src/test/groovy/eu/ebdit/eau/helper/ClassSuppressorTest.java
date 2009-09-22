package eu.ebdit.eau.helper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Callable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.ebdit.eau.util.Classes;

/**
 * Test for {@link ClassSuppressor}.
 * 
 * @author Vladimir Orany
 * 
 */
public final class ClassSuppressorTest {

    private static final String NOT_SUPRESSED = "eu.ebdit.eau.Points";
    private static final String SUPPRESSED_CLASS = "org.junit.Test";
    private static final String SUPPRESSED_PACKAGE = "org.junit"; // NOPMD
    private static final String SUPPRESSED_REGEX = "org\\.junit\\..*";

    private transient ClassSuppressor fixture;

    /**
     * Sets up fixture.
     */
    @Before
    public void setUp() {
	fixture = new ClassSuppressor();
    }

    /**
     * Tears down fixture.
     */
    @After
    public void tearDown() {
	fixture = null;
    }

    /**
     * Test for {@link ClassSuppressor#suppressClass(String)}.
     */
    @Test
    public void testSuppressClass() {
	assertTestClassesFound();
	fixture.suppressClass(SUPPRESSED_CLASS);
	assertClassSuppressed();
    }

    /**
     * Test for {@link ClassSuppressor#suppressPackage(String)}.
     */
    @Test
    public void testSuppressPackage() {
	assertTestClassesFound();
	fixture.suppressPackage(SUPPRESSED_PACKAGE);
	assertClassSuppressed();
    }

    /**
     * Test for {@link ClassSuppressor#suppressByRegExp(String)}.
     */
    @Test
    public void testSuppressRegex() {
	assertTestClassesFound();
	fixture.suppressByRegExp(SUPPRESSED_REGEX);
	assertClassSuppressed();

    }

    private void assertClassSuppressed() {
	assertTrue(NOT_SUPRESSED + " was supressed!", fixture
		.runSuppressed(new Callable<Boolean>() {

		    @Override
		    public Boolean call() {
			return isFound(NOT_SUPRESSED);
		    }
		}));
	assertFalse(SUPPRESSED_CLASS + " was not supressed!", fixture
		.runSuppressed(new Callable<Boolean>() {

		    @Override
		    public Boolean call() {
			return isFound(SUPPRESSED_CLASS);
		    }
		}));
    }

    private void assertTestClassesFound() {
	assertTrue(NOT_SUPRESSED + " not found!", isFound(NOT_SUPRESSED));
	assertTrue(SUPPRESSED_CLASS + " not found!", isFound(SUPPRESSED_CLASS));
    }

    private boolean isFound(final String className) {
	return Classes.existingClass(className) != null;
    }

}
