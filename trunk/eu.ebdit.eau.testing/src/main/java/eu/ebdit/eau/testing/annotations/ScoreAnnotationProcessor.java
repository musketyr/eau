package eu.ebdit.eau.testing.annotations;

import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import eu.ebdit.eau.Score;
import eu.ebdit.eau.testing.ScoreCollector;
import eu.ebdit.eau.testing.beans.ScoreBean;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("eu.ebdit.eau.testing.annotations.*")
public class ScoreAnnotationProcessor extends AbstractProcessor implements
	ScoreCollector {

    private transient final List<Score> scores = Lists.newArrayList();

    @Override
    public final boolean process(final Set<? extends TypeElement> annotations,
	    final RoundEnvironment roundEnv) {
	for (Element e : roundEnv.getElementsAnnotatedWith(Points.class)) {
	    scores.add(getScoreFromElement(e));
	}
	return true;
    }

    private ScoreBean getScoreFromElement(final Element element) {
	final Points points = element.getAnnotation(Points.class);
	final Details details = element.getAnnotation(Details.class);
	final Bonus bonus = element.getAnnotation(Bonus.class);
	final Description desc = element.getAnnotation(Description.class);

	final ScoreBean score = new ScoreBean();
	score.setBonus(bonus != null);
	score.setPoints(points.value());
	score.setMessage(desc == null ? "" : desc.value());
	score.setSuiteName(getClassName(element));
	score.setDetails(details == null ? null : details.value());
	score.setCheckName(element.getSimpleName().toString());
	return score;
    }

    private String getClassName(final Element element) {
	return element.getEnclosingElement().toString();
    }

    public final Iterable<Score> getScores() {
	return ImmutableList.copyOf(scores);
    }

}
