package eu.ebdit.eau.testing.junit;

import java.util.Arrays;

import eu.ebdit.eau.Report;
import eu.ebdit.eau.Reporter;
import eu.ebdit.eau.testing.TestResultCollector;
import eu.ebdit.eau.testing.TestReporter;
import eu.ebdit.eau.testing.annotations.ScoreAnnotationCollector;

public final class JUnitTestReporter implements Reporter {

    private final Class<?>[] classes;//NOPMD

    private JUnitTestReporter(final Class<?>[] classes) {
	this.classes = Arrays.copyOf(classes, classes.length);
    }

    public static Reporter of(final Class<?>... classes) {//NOPMD
	return new JUnitTestReporter(classes);
    }

    public Report report() {
	final TestResultCollector col = getCollector();
	return TestReporter.of(new ScoreAnnotationCollector().check(classes), col.getResults()).report();
    }

    private TestResultCollector getCollector() {
	try {
	    Class.forName("org.junit.JUnitCore");
	    return JUnit4TestResultCollector.collectResults(classes);
	} catch (ClassNotFoundException e) {
	    return JUnit3TestResultCollector.collectResults(classes);
	}
    }

    public Class<?>[] getClasses() {
	return Arrays.copyOf(classes, classes.length);
    }
    
}