package eu.ebdit.eau.reports;

import java.io.StringWriter;

import com.google.common.collect.ImmutableList;

import eu.ebdit.eau.Report;
import eu.ebdit.eau.util.PlainTextPrinter;

/**
 * Report container holds zero or more single reports and shows summary 
 * statistics for them.
 * 
 * @author Vladimir Orany
 * 
 */
public final class ReportContainer implements Report { // NOPMD

    private final ImmutableList<Report> reports;
    private final String message;

    private ReportContainer(final Iterable<Report> theChildren,
	    final String theMessage) {
	this.reports = ImmutableList.copyOf(theChildren);
	this.message = theMessage;
    }

    /**
     * Creates new report container of specified message and children.
     * 
     * @param message description of report container
     * @param theChildren children of report container
     * @return new report container of specified description and children
     */
    public static ReportContainer of(final String message, // NOPMD
	    final Iterable<Report> theChildren) {
	return new ReportContainer(theChildren, message);
    }

    @Override
    public ImmutableList<Report> getReports() {
	return reports;
    }

    @Override
    public double getMaxPoints() {
	double ret = 0;
	for (Report er : reports) {
	    ret += er.getMaxPoints();
	}
	return ret;
    }

    @Override
    public double getMaxPointsWithBonus() {
	double ret = 0;
	for (Report er : reports) {
	    ret += er.getMaxPointsWithBonus();
	}
	return ret;
    }

    @Override
    public String getDescription() {
	return message;
    }

    @Override
    public String getDetails() {
	return null;
    }

    @Override
    public double getPoints() {
	double ret = 0;
	for (Report er : reports) {
	    ret += er.getPoints();
	}
	return ret;
    }

    @Override
    public double getSuccessPercentage() {
	return getPoints() / getMaxPoints();
    }

    @Override
    public String toString() {
	return PlainTextPrinter.INSTANCE.printReport(this, new StringWriter())
		.toString();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((reports == null) ? 0 : reports.hashCode());
	result = prime * result + ((message == null) ? 0 : message.hashCode());
	return result;
    }

    @Override
    public boolean equals(final Object obj) { // NOPMD
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final ReportContainer other = (ReportContainer) obj;
	if (reports == null) {
	    if (other.reports != null) {
		return false;
	    }
	} else if (!reports.equals(other.reports)) {
	    return false;
	}
	if (message == null) {
	    if (other.message != null) {
		return false;
	    }
	} else if (!message.equals(other.message)) {
	    return false;
	}
	return true;
    }

}
