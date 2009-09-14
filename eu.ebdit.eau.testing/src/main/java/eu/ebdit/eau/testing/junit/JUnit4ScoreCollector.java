package eu.ebdit.eau.testing.junit;

import java.util.Collection;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import eu.ebdit.eau.Score;
import eu.ebdit.eau.testing.ScoreCollector;
import eu.ebdit.eau.testing.annotations.Bonus;
import eu.ebdit.eau.testing.annotations.Details;
import eu.ebdit.eau.testing.annotations.Points;
import eu.ebdit.eau.testing.beans.ResultBean;
import eu.ebdit.eau.testing.beans.ScoreBean;

public class JUnit4ScoreCollector extends RunListener implements
	ScoreCollector {

    private final Collection<Score> scores = Lists.newArrayList();//NOPMD
    
    @Override //NOPMD
    public final void testStarted(final Description description) {
        addScoreIfNeeded(description);
    }
    
    private void addScoreIfNeeded(final Description description) {
	final ResultBean helper = JUnitTestHelper.initNames(new ResultBean(), description.getDisplayName());
	final ScoreBean score = new ScoreBean();
	score.setSuiteName(helper.getSuiteName());
	score.setCheckName(helper.getCheckName());
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
    public final Iterable<Score> getScores() {
        return ImmutableList.copyOf(scores);
    }
    
}
