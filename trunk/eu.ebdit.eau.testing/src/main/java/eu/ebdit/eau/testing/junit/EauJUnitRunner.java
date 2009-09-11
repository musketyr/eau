package eu.ebdit.eau.testing.junit;

import java.util.List;

import org.junit.runner.JUnitCore;

import com.google.common.collect.ImmutableList;

import eu.ebdit.eau.testing.TestResult;

public class EauJUnitRunner {

    public List<TestResult> collectFrom(Class<?> classes) {
	EauRunListener erl = new EauRunListener();
	JUnitCore core = new JUnitCore();
	core.addListener(erl);
	core.run(classes);
	return ImmutableList.copyOf(erl.getResults());
    }

}
