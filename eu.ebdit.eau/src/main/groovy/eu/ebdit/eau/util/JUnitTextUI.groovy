package eu.ebdit.eau.util

import eu.ebdit.eau.junit.JUnitResultCollector;
import eu.ebdit.eau.Reporter;
import groovy.util.CliBuilder;
import groovy.util.OptionAccessor;

public class JUnitTextUI {

    public static void main(String[] args) {

	CliBuilder cli = new CliBuilder()
	cli.h(longOpt: 'help', "prints usage information")
	cli.p(longOpt: 'printer', 'specify report printer', args: 1,)
	
	def opt = cli.parse(args)
	if(!opt) return

	if(opt.h) {
	    cli.usage()
	}
	println ' Start of Report '.center(80, '=')
	def printer = null
	if(opt.p){
	    def printerClass = Classes.existingClass(opt.p)
	    if (printerClass != null) {
		try {
		    printer = printerClass.newInstance()
		} catch (e){
		    e.printStackTrace()
		}
	    }
	}
	if (printer == null) {
	    printer = new PlainTextPrinter()
	}
	def reporter = Reporter
		.withScoreCollectors(new ScoreAnnotationCollector())
		.withResultCollectors(new JUnitResultCollector())
		.build();
	def writer = new StringWriter()
	printer.printReport(reporter.report(opt.arguments()), writer);
	println writer
	println ' End of the report '.center(80, '=')
    }
    
}
