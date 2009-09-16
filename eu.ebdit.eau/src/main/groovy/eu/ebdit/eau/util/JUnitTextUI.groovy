package eu.ebdit.eau.util

import eu.ebdit.eau.junit.JUnitResultCollector;
import eu.ebdit.eau.reports.TestReporter;

public class JUnitTextUI {

    public static void main(String[] args) {
	def classes = [];
	args.each {
	    try {
		classes << Class.forName(it)
	    } catch (ClassNotFoundException e) {
		println "$it was not found on the classpath"
	    }
	}
	def reporter = TestReporter.of(
		new ScoreAnnotationCollector().collectFrom(classes as Class[]),
		new JUnitResultCollector().collectFrom(classes as Class[]));
	println reporter.report();
    }
    
}
