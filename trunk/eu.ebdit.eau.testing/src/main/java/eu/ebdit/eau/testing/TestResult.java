package eu.ebdit.eau.testing;

import eu.ebdit.eau.Result;

public interface TestResult extends Result {

    String getClassFQName();

    String getTestName();

}
