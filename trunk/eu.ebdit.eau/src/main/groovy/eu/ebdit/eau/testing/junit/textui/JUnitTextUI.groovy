package eu.ebdit.eau.testing.junit.textui

import eu.ebdit.eau.testing.junit.JUnitTestReporter;

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
	def reporter = JUnitTestReporter.of(classes as Class[]);
	println reporter.report();
    }
    
}
