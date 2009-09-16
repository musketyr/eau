package eu.ebdit.eau;

/**
 * Score represents quantification of single check.
 * 
 * @author Vladimír Oraný
 * 
 */
public interface Score {

    /**
     * Short description of particular check. It should not be longer than 100
     * characters
     * 
     * @return description of particular check which is going to be quantified
     */
    String getDescription();

    /**
     * Detailed information about particular check. It should contain longer
     * description of check and also provide information how fix unsuccessful
     * check
     * 
     * @return detailed information about particular check
     */
    String getDetails();

    /**
     * Number of points for successful check. The number should be higher than
     * zero. Zero points might cause reporting problems. Negative numbers shall
     * be converted to their absolute value for reporting purposes
     * 
     * @return number of points for successful check
     * @see Points
     */
    double getPoints();

    /**
     * @return whether underlying check is bonus or not
     * @see Bonus
     * @see Report#getMaxPointsWithBonus()
     */
    boolean isBonus();

    /**
     * @return name of the suite which is scored check part of
     */
    String getSuiteName();

    /**
     * @return name of the check which is quantified by this score
     */
    String getCheckName();

}
