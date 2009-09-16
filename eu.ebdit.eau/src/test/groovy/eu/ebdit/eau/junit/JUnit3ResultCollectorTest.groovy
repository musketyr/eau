package eu.ebdit.eau.junit;

import org.junit.Ignore;
import org.junit.Test;

import eu.ebdit.eau.Result;
import eu.ebdit.eau.junit.JUnit3ResultCollector;
import eu.ebdit.eau.reports.TestReporterTest;
import eu.ebdit.eau.util.AbstractResultCollectorTest;

public class JUnit3ResultCollectorTest extends AbstractResultCollectorTest {

    @Override
    protected Iterable<Result> getResults() {
	return new JUnit3ResultCollector().collectFrom(Class.forName("org.example.TestClass"));
    }
    
}
