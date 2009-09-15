package eu.ebdit.eau.junit;

import java.util.Arrays;

import eu.ebdit.eau.Report;
import eu.ebdit.eau.Reporter;
import eu.ebdit.eau.ResultCollector;
import eu.ebdit.eau.reports.TestReporter;
import eu.ebdit.eau.util.ScoreAnnotationCollector;

public final class JUnitTestReporter implements Reporter {

    private final Class<?>[] classes; // NOPMD

    private JUnitTestReporter(final Class<?>[] classes) {
	this.classes = Arrays.copyOf(classes, classes.length);
    }

    public static Reporter of(final Class<?>... classes) {// NOPMD
	return new JUnitTestReporter(classes);
    }

    public Report report() {
	final ResultCollector col = getCollector();
	return TestReporter.of(new ScoreAnnotationCollector().check(classes),
		col.getResults()).report();
    }

    private ResultCollector getCollector() {
	try {
	    Class.forName("org.junit.Test");
	    return JUnit4ResultCollector.collectResults(classes);
	} catch (ClassNotFoundException e) {
	    return JUnit3ResultCollector.collectResults(classes);
	}
    }

    public Class<?>[] getClasses() {
	return Arrays.copyOf(classes, classes.length);
    }

}
