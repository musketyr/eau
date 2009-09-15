package eu.ebdit.eau.junit;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.concurrent.Callable;

import org.junit.Test;

import eu.ebdit.eau.Reporter;
import eu.ebdit.eau.helper.ClassSuppressor;
import eu.ebdit.eau.reports.AbstractTestReporterTest;

public class JUnitTestReporterTest extends AbstractTestReporterTest {

    @Override
    protected Reporter getReporter() throws ClassNotFoundException {
	ClassSuppressor suppressor = new ClassSuppressor();
	suppressor.suppressClass("org.junit.Test");
	return suppressor.runSuppressed(new Callable<Reporter>() {
	    @Override
	    public Reporter call() throws Exception {
		return JUnitTestReporter.of(Class
			.forName("org.example.TestClass"));
	    }
	});
    }

    @Test
    public void testClasses() throws ClassNotFoundException {
	final Class<?>[] classes = new Class<?>[] { Class
		.forName("org.example.TestClass") };
	final Class<?>[] original = Arrays.copyOf(classes, classes.length);
	final JUnitTestReporter reporter = (JUnitTestReporter) JUnitTestReporter
		.of(classes);
	assertArrayEquals(original, reporter.getClasses());// NOPMD
	classes[0] = getClass();
	assertArrayEquals(original, reporter.getClasses());// NOPMD
    }

}
