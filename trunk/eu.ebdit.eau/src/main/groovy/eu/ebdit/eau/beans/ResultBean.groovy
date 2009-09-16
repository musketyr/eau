package eu.ebdit.eau.beans

import eu.ebdit.eau.Result

/**
 * @author Vladim�r Oran�
 *
 */
public class ResultBean implements Result{
    	String suiteName
	String message
	boolean passed
	String checkName

	boolean passed(){ return passed}
}
