package eu.ebdit.eau.reports;

import java.util.Collection;
import java.util.Collections;

import eu.ebdit.eau.Report;

public final class SimpleReport implements Report {

    private final double points;
    private final double max;
    private final double maxWithBonuses;
    private final String message;
    private final String details;

    public SimpleReport(final String message, final double points,
	    final double max, final double maxWithBonuses, final String details) {
	this.points = points;
	this.max = max;
	this.maxWithBonuses = maxWithBonuses;
	this.message = message;
	this.details = details;
    }

    public SimpleReport(final String message, final double points,
	    final double max, final double maxWithBonuses) {
	this(message, points, max, maxWithBonuses, null);
    }

    public SimpleReport(final String message, final double points,
	    final double max, final String details) {
	this(message, points, max, max, details);
    }

    public SimpleReport(final String message, final double points,
	    final double max) {
	this(message, points, max, max, null);
    }

    public Collection<Report> getChildReports() {
	return Collections.emptyList();
    }

    public double getMaxPoints() {
	return max;
    }

    public double getMaxPointsBonusIncluded() {
	return maxWithBonuses;
    }

    public String getMessage() {
	return message;
    }

    public String getDetails() {
	return details;
    }

    public double getPoints() {
	return points;
    }

    public double getSuccessPercentage() {
	double theMax = max;
	if (theMax == 0) {
	   theMax = maxWithBonuses;
	}
	if (theMax == 0) {
	    return 0;
	}
	return points / theMax;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(max);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(maxWithBonuses);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + ((message == null) ? 0 : message.hashCode());
	temp = Double.doubleToLongBits(points);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	return result;
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	SimpleReport other = (SimpleReport) obj;
	if (Double.doubleToLongBits(max) != Double.doubleToLongBits(other.max)) {
	    return false;
	}
	if (Double.doubleToLongBits(maxWithBonuses) != Double
		.doubleToLongBits(other.maxWithBonuses)) {
	    return false;
	}
	if (message == null) {
	    if (other.message != null) {
		return false;
	    }
	} else if (!message.equals(other.message)) {
	    return false;
	}
	if (Double.doubleToLongBits(points) != Double
		.doubleToLongBits(other.points)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return String.format("%06.2f%% of %06.2f (%06.2f) - %s",
		getSuccessPercentage() * 100, getMaxPoints(),
		getMaxPointsBonusIncluded(), getMessage());
    }
}
