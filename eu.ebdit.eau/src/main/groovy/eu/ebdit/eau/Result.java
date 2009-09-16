package eu.ebdit.eau;

/**
 * Result of the check. Check can be test run or style check or something other.
 * 
 * @author Vladimír Oraný
 * 
 */
public interface Result {

    /**
     * @return the message from checking or <code>null</code> if there is no
     * message from the check (usually on passed check).
     */
    String getMessage();

    /**
     * Returns whether the check passed. That means whether the test was
     * successful and did ended by error of failure or whether the check didn't
     * find any problems.
     * 
     * @return whether the check was successful
     */
    boolean isSuccess();

    /**
     * @return name of the suite which is resulted check part of
     */
    String getSuiteName();

    /**
     * @return name of the resulted check 
     */
    String getCheckName();

}
