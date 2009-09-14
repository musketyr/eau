package eu.ebdit.eau;

import java.util.Collection;

public interface Report {

    double getMaxPointsWithBonus();

    double getMaxPoints();

    double getPoints();

    double getSuccessPercentage();

    String getMessage();

    String getDetails();

    Collection<Report> getReports();

}
