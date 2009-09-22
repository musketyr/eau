package eu.ebdit.eau.util

import eu.ebdit.eau.Printer;
import eu.ebdit.eau.Report;
import groovy.xml.MarkupBuilder;

public class XmlReportPrinter implements Printer{
	
	void printReport(theReport, Writer writer){
		def builder = new MarkupBuilder(writer)
		generateReport(builder, theReport)
	}
	
	private generateReport(builder, theReport){
		builder.report {
			builder.message theReport.message
			builder.successPercentage theReport.successPercentage
			builder.points theReport.points
			builder.maxPoints theReport.maxPoints
			if(theReport.maxPointsWithBonus && theReport.maxPointsWithBonus != theReport.maxPoints){
			    builder.maxPointsWithBonus theReport.maxPointsWithBonus
			}
			if (theReport.details) {
			    builder.details theReport.details
			}
			if (theReport.reports){
			    builder.reports{
				    for(childReport in theReport.reports){
					generateReport(builder, childReport)
				    }
			    }
			}
		}
	}
	
}
