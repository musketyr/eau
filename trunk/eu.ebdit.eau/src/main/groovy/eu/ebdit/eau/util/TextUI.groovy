package eu.ebdit.eau.util

import com.google.common.collect.Iterables;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import eu.ebdit.eau.junit.JUnitResultCollector;
import eu.ebdit.eau.Reporter;
import groovy.util.CliBuilder;
import groovy.util.OptionAccessor;

public class TextUI {

    private static final SEPARATOR_PATTERN = /\s*,\s*/

    public static void main(String[] args) {

	def cli = initCli()
	def opt = cli.parse(args)
	if (!opt) return

	if (opt.h) {
	    cli.usage()
	}
	Reporter.Builder builder = new Reporter.Builder()
	
	initCollectors ('result', opt.r, Collectors.RESULT_COLLECTOR_FOOTPRINT, 
		Collectors.getResultCollectors(), builder.&withResultCollectors)
	initCollectors ('score', opt.s, Collectors.SCORE_COLLECTOR_FOOTPRINT,
		Collectors.getScoreCollectors(), builder.&withScoreCollectors)
	
	def reporter = builder.build();

	println ' Start of Report '.center(80, '=')
	println getPrinter(opt).printReport(reporter.report(opt.arguments()), 
		new StringWriter())
	println ' End of the report '.center(80, '=')
    }

    
    
    private static final getPrinter(opt){
	if(opt.p){
	    def printerClass = Classes.existingClass(opt.p)
	    if (printerClass != null) {
		try {
		    return printerClass.newInstance()
		} catch (e){
		    e.printStackTrace()
		}
	    }
	}
	return new PlainTextPrinter()
    }

    private static final initCli(){
	CliBuilder cli = new CliBuilder()
	cli.h(longOpt: 'help', "prints usage information")
	cli.p(longOpt: 'printer', 'specify report printer', args: 1)
	cli.r(longOpt: 'result-collectors', 'comma seperated names of enabled '
		+ 'result collectors', args: 1)
	cli.s(longOpt: 'score-collectors', 'comma seperated names of enabled '
		+ 'score collectors', args: 1)
	return cli
    }

    private static final initCollectors(what, fromWhat, footprint, defaultCollectors, whereTo){
	def collectors = null;
	if (fromWhat) {
	    println "Selected collectors: $fromWhat"
	    collectors = 
		    Iterables.transform(
			    Iterables.filter(
				    Classes.asClassIterable(fromWhat.split(SEPARATOR_PATTERN)), 
				    Classes.isSimilarTo(footprint)
			    ),
			    Classes.toInstance()
		    )
	    println "Following collectors was found from selection: $collectors"
	}
	if (!collectors) {
	    collectors = defaultCollectors
	    println "No $what collectors defined, using defaults: $collectors"
	}
	    whereTo(collectors)
    }
    
}
