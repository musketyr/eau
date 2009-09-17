package eu.ebdit.eau.util

import eu.ebdit.eau.junit.JUnitResultCollector;
import eu.ebdit.eau.Reporter;

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
	def reporter = Reporter
		.withScoreCollectors(new ScoreAnnotationCollector())
		.withResultCollectors(new JUnitResultCollector())
		.build();
	println reporter.report(classes);
    }
    
}
