package eu.ebdit.eau.testing.junit;

import java.util.Collection;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import eu.ebdit.eau.testing.TestScore;
import eu.ebdit.eau.testing.TestScoreCollector;
import eu.ebdit.eau.testing.annotations.Bonus;
import eu.ebdit.eau.testing.annotations.Details;
import eu.ebdit.eau.testing.annotations.Points;
import eu.ebdit.eau.testing.beans.TestResultBean;
import eu.ebdit.eau.testing.beans.TestScoreBean;

public class JUnit4TestScoreCollector extends RunListener implements
	TestScoreCollector {

    private final Collection<TestScore> scores = Lists.newArrayList();//NOPMD
    
    @Override //NOPMD
    public void testStarted(final Description description) {
        addScoreIfNeeded(description);
    }
    
    private void addScoreIfNeeded(final Description description) {
	final TestResultBean helper = JUnitTestHelper.initNames(new TestResultBean(), description.getDisplayName());
	final TestScoreBean score = new TestScoreBean();
	score.setClassName(helper.getClassName());
	score.setTestName(helper.getTestName());
	final Points points = description.getAnnotation(Points.class);
	if (points != null) {
	    score.setPoints(points.value());

	    final eu.ebdit.eau.testing.annotations.Description desc = description
		    .getAnnotation(eu.ebdit.eau.testing.annotations.Description.class);
	    if (desc != null) {
		score.setMessage(desc.value());
	    }
	    final Details details = description.getAnnotation(Details.class);
	    if (details != null) {
		score.setDetails(details.value());
	    }
	    score.setBonus(description.getAnnotation(Bonus.class) != null);
	    scores .add(score);
	}
    }

    @Override
    public Iterable<TestScore> getScores() {
        return ImmutableList.copyOf(scores);
    }
    
}
