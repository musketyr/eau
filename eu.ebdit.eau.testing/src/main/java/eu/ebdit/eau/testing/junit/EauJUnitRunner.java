package eu.ebdit.eau.testing.junit;

import org.junit.runner.JUnitCore;

public class EauJUnitRunner {

    public EauRunListener collectResults(Class<?>... classes) {
	EauRunListener erl = new EauRunListener();
	JUnitCore core = new JUnitCore();
	core.addListener(erl);
	core.run(classes);
	return erl;
    }

}
