package eu.ebdit.eau.util

import java.io.Writer;

import eu.ebdit.eau.Printer;

public class PlainTextPrinter implements Printer {

    private static final int NUM_OF_IDENT_CHAR = 3;
    private static final int BASE_IDENT = 8;
    
    @Override
    void printReport(report, Writer writer) {
        writer << '\n'
	printReport(report, writer, 0)
	println writer
    }

    private printReport(report, Writer writer, int ident){
        String success = String.format('%6.2f', 
        	(double) report.successPercentage * 100d)
        writer << ' ' * (NUM_OF_IDENT_CHAR * ident)
        writer << success.padLeft((6) - success.size()) + '% ' + report.message
        writer << '\n'
        writer << ' ' * (BASE_IDENT + NUM_OF_IDENT_CHAR * ident)
        writer << "points: ${report.points}/${report.maxPoints}"
        if (report.maxPointsWithBonus != null && report.maxPoints 
        	!= report.maxPointsWithBonus) {
	    writer << "(${report.maxPointsWithBonus})"
	}
        writer << '\n'
        if (report.details) {
            // TODO handle longer details
            writer << ' ' * (BASE_IDENT + NUM_OF_IDENT_CHAR * ident)
            writer << "detail: ${report.details}"
            writer << '\n'
	}
        for(child in report.reports){
            printReport(child, writer, ident + 1)
        }
    }
    
}