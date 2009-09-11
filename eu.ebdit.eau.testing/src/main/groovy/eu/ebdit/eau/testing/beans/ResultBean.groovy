package eu.ebdit.eau.testing.beans

import eu.ebdit.eau.testing.Status
import eu.ebdit.eau.testing.Result



/**
 * @author Vladimír Oraný
 *
 */
public class ResultBean implements Result{

    	String classFQName
	String message
	Status status
	String testName

	static ResultBean of(String classFQName, String testName, Status status, String message){
	    return new ResultBean(classFQName: classFQName, testName: testName, status: status, message: message);
	}
	
}
