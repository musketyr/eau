package eu.ebdit.eau.junit;

import java.util.Arrays;

import eu.ebdit.eau.Collector;
import eu.ebdit.eau.Report;
import eu.ebdit.eau.Reporter;
import eu.ebdit.eau.Result;
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
	final Collector<Result> col = getCollector();
	return TestReporter.of(new ScoreAnnotationCollector().collectFrom((Object[])classes),
		col.collectFrom((Object[])classes)).report();
    }

    private Collector<Result> getCollector() {
	try {
	    Class.forName("org.junit.Test");
	    return new JUnit4ResultCollector();
	} catch (ClassNotFoundException e) {
	    return new JUnit3ResultCollector();
	}
    }

    public Class<?>[] getClasses() {
	return Arrays.copyOf(classes, classes.length);
    }

}
