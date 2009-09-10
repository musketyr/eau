package eu.ebdit.eau.reports;

import java.util.Collection;

import com.google.common.collect.ImmutableList;

import eu.ebdit.eau.EauReport;

public class ReportContainer implements EauReport {

    private final ImmutableList<EauReport> children;
    private final String message;

    private ReportContainer(ImmutableList<EauReport> children, String message) {
	this.children = children;
	this.message = message;
    }

    @SuppressWarnings("unchecked")
    public static ReportContainer of(String message,
	    Iterable<EauReport> theChildren) {
	ImmutableList<EauReport> children;
	if (theChildren instanceof ImmutableList) {
	    children = (ImmutableList<EauReport>) theChildren;
	} else {
	    children = ImmutableList.copyOf(theChildren);
	}
	return new ReportContainer(children, message);
    }

    public Collection<EauReport> getChildReports() {
	return children;
    }

    public double getMaxPoints() {
	double ret = 0;
	for (EauReport er : children) {
	    ret += er.getMaxPoints();
	}
	return ret;
    }

    public double getMaxPointsBonusIncluded() {
	double ret = 0;
	for (EauReport er : children) {
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
	for (EauReport er : children) {
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

    private static String ident(int ident) {
	String ret = "";
	for (int i = 0; i < ident; i++) {
	    ret += IDENT_BLOCK;
	}
	return ret;
    }

    private String toString(int ident) {
	String ret = ident(ident)
		+ String.format("%06.2f%% - %s", getSuccessPercentage() * 100,
			getMessage()) + " {\n";
	for (EauReport er : children) {
	    if (er == null) {
		continue;
	    }
	    if (er instanceof ReportContainer) {
		ret += ((ReportContainer) er).toString(ident + 1);
	    } else {
		ret += ident(ident + 1) + er.toString();
	    }
	    ret += "\n";
	}
	ret += ident(ident) + "}";
	return ret;
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
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	ReportContainer other = (ReportContainer) obj;
	if (children == null) {
	    if (other.children != null)
		return false;
	} else if (!children.equals(other.children))
	    return false;
	if (message == null) {
	    if (other.message != null)
		return false;
	} else if (!message.equals(other.message))
	    return false;
	return true;
    }

}
