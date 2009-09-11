package eu.ebdit.eau.testing.junit;

import eu.ebdit.eau.Reporter;
import eu.ebdit.eau.testing.BaseTestReporterTest;


public class JUnitTestReporterTest extends BaseTestReporterTest{

    @Override
    protected Reporter getReporter() throws Exception {
        return JUnitTestReporter.of(Class.forName("org.example.TestClass"));
    }
    
}
