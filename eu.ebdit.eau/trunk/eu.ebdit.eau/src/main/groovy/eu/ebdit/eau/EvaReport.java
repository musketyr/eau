package eu.ebdit.eau;

import java.util.Collection;

public interface EvaReport {

	double getMaxPointsBonusIncluded();
	double getMaxPoints();
	double getPoints();
	double getSuccessPercentage();
	String getMessage();
	String getDetails();
	Collection<EvaReport> getChildReports();
	
}
