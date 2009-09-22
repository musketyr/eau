package eu.ebdit.eau.util

import java.io.Writer;

import eu.ebdit.eau.Printer;

public class PlainTextPrinter implements Printer {

    @Override
    void printReport(report, Writer writer) {
        writer << '\n'
	printReport(report, writer, 0)
	println writer
    }

    private printReport(report, Writer writer, int ident){
        String success = String.format('%6.2f', (double) report.successPercentage * 100d)
        writer << ' ' * (3 * ident)
        writer << success.padLeft((6) - success.size()) + '% ' + report.message
        writer << '\n'
        writer << ' ' * (8 + 3 * ident)
        writer << "points: ${report.points}/${report.maxPoints}"
        if (report.maxPoints != report.maxPointsWithBonus) {
	    writer << "(${report.maxPointsWitBonus})"
	}
        writer << '\n'
        if (report.details) {
            // TODO handle longer details
            writer << ' ' * (8 + 3 * ident)
            writer << "detail: ${report.details}"
            writer << '\n'
	}
        for(child in report.reports){
            printReport(child, writer, ident + 1)
        }
    }
    
}