package eu.ebdit.eau.reports;

import java.util.Collection;

import com.google.common.collect.ImmutableList;

import eu.ebdit.eau.Report;

public class ReportContainer implements Report {

    private final ImmutableList<Report> children;
    private final String message;

    private ReportContainer(final Iterable<Report> theChildren, final String message) {
	this.children = ImmutableList.copyOf(theChildren);
	this.message = message;
    }

    public static ReportContainer of(final String message,
	    final Iterable<Report> theChildren) {
	return new ReportContainer(theChildren, message);
    }

    public Collection<Report> getChildReports() {
	return children;
    }

    public double getMaxPoints() {
	double ret = 0;
	for (Report er : children) {
	    ret += er.getMaxPoints();
	}
	return ret;
    }

    public double getMaxPointsBonusIncluded() {
	double ret = 0;
	for (Report er : children) {
	    ret += er.getMaxPointsBonusIncluded();
	}
	return ret;
    }

    public String getMessage() {
	return message;
    }

    public String getDetails() {
	return toString();
    }

    public double getPoints() {
	double ret = 0;
	for (Report er : children) {
	    ret += er.getPoints();
	}
	return ret;
    }

    public double getSuccessPercentage() {
	return getPoints() / getMaxPoints();
    }

    @Override
    public String toString() {
	return toString(0);
    }

    private static final String IDENT_BLOCK = "   ";

    private static String ident(final int ident) {
	StringBuilder ret = new StringBuilder();
	for (int i = 0; i < ident; i++) {
	    ret.append(IDENT_BLOCK);
	}
	return ret.toString();
    }

    private String toString(final int ident) {
	StringBuilder ret = new StringBuilder();
	ret.append(ident(ident)).append(String.format("%06.2f%% - %s", getSuccessPercentage() * 100,
			getMessage())).append(" {\n");
	for (Report er : children) {
	    if (er == null) {
		continue;
	    }
	    if (er instanceof ReportContainer) {
		ret.append(((ReportContainer) er).toString(ident + 1));
	    } else {
		ret.append(ident(ident + 1) + er.toString());
	    }
	    ret.append("\n");
	}
	ret.append(ident(ident)).append("}");
	return ret.toString();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((children == null) ? 0 : children.hashCode());
	result = prime * result + ((message == null) ? 0 : message.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	ReportContainer other = (ReportContainer) obj;
	if (children == null) {
	    if (other.children != null) {
		return false;
	    }
	} else if (!children.equals(other.children)) {
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
