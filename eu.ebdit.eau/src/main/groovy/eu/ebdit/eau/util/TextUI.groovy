package eu.ebdit.eau.util

import org.slf4j.LoggerFactory;

import com.google.common.collect.Iterables;

import eu.ebdit.eau.Reporter;


public class TextUI {
	
	private static final SEPARATOR_PATTERN = /\s*,\s*/
	private static final logger = LoggerFactory.getLogger(TextUI)
	
	public static void main(String[] args) {
		
		logger.info "\nReporting started...\n"
		logger.info("Using classpath: " 
				+ System.getProperty("java.class.path"))
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
		
		def report =  builder.build().report(opt.arguments())
		logger.info ' Start of Report '.center(80, '=')
		println getPrinter(opt).printReport(report, new StringWriter())
		logger.info ' End of the report '.center(80, '=')
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
			logger.info "Selected collectors: $fromWhat"
			collectors = 
					Iterables.transform(
					Iterables.filter(
					Classes.asClassIterable(fromWhat.split(SEPARATOR_PATTERN)), 
					Classes.isSimilarTo(footprint)
					),
					Classes.toInstance()
					)
			logger.info "Following collectors was found from selection: $collectors"
		}
		if (!collectors) {
			collectors = defaultCollectors
			logger.info "No $what collectors defined, using defaults: $collectors"
		}
		whereTo(collectors)
	}
}
