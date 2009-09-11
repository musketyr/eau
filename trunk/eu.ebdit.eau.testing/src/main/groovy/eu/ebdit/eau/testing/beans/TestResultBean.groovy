package eu.ebdit.eau.testing.beans

import eu.ebdit.eau.Status;
import eu.ebdit.eau.testing.TestResult

/**
 * @author Vladimír Oraný
 *
 */
public class TestResultBean implements TestResult{
    	String classFQName
	String message
	Status status
	String testName	
}
