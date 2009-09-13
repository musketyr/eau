package eu.ebdit.eau.testing;

import eu.ebdit.eau.Result;

public interface TestResult extends Result {

    String getClassName();

    String getTestName();

}
