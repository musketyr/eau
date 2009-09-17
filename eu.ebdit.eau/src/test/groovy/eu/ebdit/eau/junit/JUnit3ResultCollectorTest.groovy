package eu.ebdit.eau.junit;

import org.junit.Ignore;
import org.junit.Test;

import eu.ebdit.eau.Collector;
import eu.ebdit.eau.Result;
import eu.ebdit.eau.junit.JUnit3ResultCollector;
import eu.ebdit.eau.util.AbstractResultCollectorTest;

public class JUnit3ResultCollectorTest extends AbstractJUnitResultCollectorTest {

    @Override
    protected Collector<Result> newCollector() {
	new JUnit3ResultCollector()
    }
    
}