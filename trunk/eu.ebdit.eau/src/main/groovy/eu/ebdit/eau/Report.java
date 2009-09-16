package eu.ebdit.eau;

import java.util.Collection;

/**
 * Report giving useful information about checks and suites reported by the
 * {@link Reporter}.
 * 
 * @author Vladimír Oraný
 */
public interface Report {

    /**
     * @return maximum of all points counted from score definitions (included
     *         bonus one)
     */
    double getMaxPointsWithBonus();

    /**
     * @return maximum of points counted from score definitions excluded ones
     *         marked as bonus
     */
    double getMaxPoints();

    /**
     * Number of points counted for reported check or suite. For single check it
     * is usually zero or {@link #getMaxPointsWithBonuses() max points with
     * bonuses}. For suites it is number which should be never higher then
     * {@link #getMaxPointsWithBonuses() max points with bonuses}. The must be
     * always higher or equal to zero.
     * 
     * @return number of points counted for reported check or suite
     */
    double getPoints();

    /**
     * @return number between zero and {@link #getMaxPointsWithBonus() max
     *         points with bonuses} divided by {@link #getMaxPoints() max
     *         points}
     */
    double getSuccessPercentage();

    /**
     * @return description of reported check or suite
     */
    String getDescription();

    /**
     * @return detailed information about reported check or suite
     */
    String getDetails();

    /**
     * Child reports of this report. Must return empty collection if there are
     * no child reports. Shall never return <code>null</code>.
     * 
     * @return child reports of this report or empty {@link Collection
     *         collection} if there are no children
     */
    Collection<Report> getReports();

}
