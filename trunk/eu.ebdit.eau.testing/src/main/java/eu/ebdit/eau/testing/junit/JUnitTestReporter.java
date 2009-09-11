package eu.ebdit.eau.testing.junit;

import eu.ebdit.eau.Report;
import eu.ebdit.eau.Reporter;
import eu.ebdit.eau.testing.TestReporter;

public class JUnitTestReporter implements Reporter {

    private final Class<?>[] classes;

    private JUnitTestReporter(Class<?>[] classes) {
	this.classes = classes;
    }

    public static Reporter of(Class<?>... classes) {
	return new JUnitTestReporter(classes);
    }

    public Report report() {
	TestCollector col = JUnitTestCollector.collectResults(classes);
	return TestReporter.of(col.getScores(), col.getResults()).report();
    }

}
