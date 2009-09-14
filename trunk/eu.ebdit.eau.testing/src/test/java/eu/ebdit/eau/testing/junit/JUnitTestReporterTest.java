package eu.ebdit.eau.testing.junit;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

import eu.ebdit.eau.Reporter;
import eu.ebdit.eau.testing.AbstractTestReporterTest;


public class JUnitTestReporterTest extends AbstractTestReporterTest{

    @Override
    protected Reporter getReporter() throws ClassNotFoundException {
        return JUnitTestReporter.of(Class.forName("org.example.TestClass"));
    }
    @Test
    public void testClasses() throws ClassNotFoundException {
	final Class<?>[] classes = new Class<?>[]{Class.forName("org.example.TestClass")};
	final Class<?>[] original = Arrays.copyOf(classes, classes.length);
	final JUnitTestReporter reporter = (JUnitTestReporter) JUnitTestReporter.of(classes);
	assertArrayEquals(original, reporter.getClasses());//NOPMD
	classes[0] = getClass();
	assertArrayEquals(original, reporter.getClasses());//NOPMD
    }
    
}
