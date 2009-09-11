package eu.ebdit.eau.testing.junit;

import eu.ebdit.eau.testing.TestResult;
import eu.ebdit.eau.testing.TestScore;

interface TestCollector {

    public abstract Iterable<TestResult> getResults();

    public abstract Iterable<TestScore> getScores();

}