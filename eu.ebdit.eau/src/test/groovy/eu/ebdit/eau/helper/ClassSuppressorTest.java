package eu.ebdit.eau.helper;

import static org.junit.Assert.*;

import java.util.concurrent.Callable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ClassSuppressorTest {

    private static final String NOT_SUPRESSED = "eu.ebdit.eau.Points";
    private static final String SUPPRESSED_CLASS = "org.junit.Test";
    private static final String SUPPRESSED_PACKAGE = "org.junit";
    private static final String SUPPRESSED_REGEX = "org\\.junit\\..*";
    
    
    private ClassSuppressor fixure;

    @Before public void setUp() {
	fixure = new ClassSuppressor();
    }
    
    @After public void tearDown() {
	fixure = new ClassSuppressor();
    }
    
    @Test
    public void testSuppressClass(){
	assertTestClassesFound();
	fixure.suppressClass(SUPPRESSED_CLASS);
	assertClassSuppressed();
    }

    @Test
    public void testSuppressPackage() {
	assertTestClassesFound();
	fixure.suppressPackage(SUPPRESSED_PACKAGE);
	assertClassSuppressed();
    }

    @Test
    public void testSuppressRegex() {
	assertTestClassesFound();
	fixure.suppressByRegExp(SUPPRESSED_REGEX);
	assertClassSuppressed();
	
    }
    
    private void assertClassSuppressed() {
	assertTrue(NOT_SUPRESSED + " was supressed!", fixure.runSuppressed(new Callable<Boolean>() {
	    
	    @Override public Boolean call() throws Exception{
		return isFound(NOT_SUPRESSED);
	    }
	}));
	assertFalse(SUPPRESSED_CLASS + " was not supressed!", fixure.runSuppressed(new Callable<Boolean>() {

	    @Override public Boolean call() throws Exception{
		return isFound(SUPPRESSED_CLASS);
	    }
	}));
    }
    
    private void assertTestClassesFound() {
	assertTrue(NOT_SUPRESSED + " not found!", isFound(NOT_SUPRESSED));
	assertTrue(SUPPRESSED_CLASS + " not found!",isFound(SUPPRESSED_CLASS));
    }

    private boolean isFound(final String className) {
	try {
	    Class.forName(className, true, Thread.currentThread().getContextClassLoader());
	    return true;
	} catch (ClassNotFoundException e) {
	   return false;
	}
    }
    
}
