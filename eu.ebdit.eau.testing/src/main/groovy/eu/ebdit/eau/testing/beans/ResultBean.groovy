package eu.ebdit.eau.testing.beans

import eu.ebdit.eau.testing.Status
import eu.ebdit.eau.testing.Result



/**
 * @author Vladim�r Oran�
 *
 */
@Immutable public final class ResultBean implements Result{
	String classFQName
	String message
	Status status
	String testName

}
