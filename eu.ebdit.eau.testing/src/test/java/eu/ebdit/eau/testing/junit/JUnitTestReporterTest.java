package eu.ebdit.eau.testing.junit;

import eu.ebdit.eau.Reporter;
import eu.ebdit.eau.testing.AbstractTestReporterTest;


public class JUnitTestReporterTest extends AbstractTestReporterTest{

    @Override
    protected Reporter getReporter() throws ClassNotFoundException {
        return JUnitTestReporter.of(Class.forName("org.example.TestClass"));
    }
    
}
