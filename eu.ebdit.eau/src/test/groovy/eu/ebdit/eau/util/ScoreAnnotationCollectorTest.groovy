package eu.ebdit.eau.util;

import eu.ebdit.eau.Collector;
import eu.ebdit.eau.Score;


public class ScoreAnnotationCollectorTest extends AbstractScoreCollectorTest {

    @Override
    protected Object getInputForResults() {
	return "org.example.TestClass";
    }

    @Override
    protected Iterable<Object> getInputsToFail() {
        return ["no.such.Class", "no-such.file", "TestClass.xml"]
    }

    protected Iterable<Object> getInputsToSucceed() {
	return [Class.forName("org.example.TestClass"), "org.example.TestClass"]
    };

    @Override
    protected Collector<Score> newCollector() {
        return new ScoreAnnotationCollector();
    }

}
