package eu.ebdit.eau.util

import eu.ebdit.eau.junit.JUnitResultCollector;
import eu.ebdit.eau.Reporter;

public class JUnitTextUI {

    public static void main(String[] args) {
	def reporter = Reporter
		.withScoreCollectors(new ScoreAnnotationCollector())
		.withResultCollectors(new JUnitResultCollector())
		.build();
	println reporter.report(args);
    }
    
}
