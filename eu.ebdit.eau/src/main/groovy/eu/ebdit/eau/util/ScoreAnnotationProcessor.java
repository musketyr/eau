package eu.ebdit.eau.util;

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

import eu.ebdit.eau.Bonus;
import eu.ebdit.eau.Description;
import eu.ebdit.eau.Details;
import eu.ebdit.eau.Points;
import eu.ebdit.eau.Score;
import eu.ebdit.eau.beans.ScoreBean;

/**
 * This class is Java 6 annotation processor which can be run during source file
 * compilation.
 * 
 * @author Vladimir Orany
 * 
 */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("eu.ebdit.eau.*")
public class ScoreAnnotationProcessor extends AbstractProcessor {

    private final transient List<Score> scores = Lists.newArrayList();

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
	score.setDescription(desc == null ? null : desc.value());
	score.setSuiteName(getClassName(element));
	score.setDetails(details == null ? null : details.value());
	score.setCheckName(element.getSimpleName().toString());
	return score;
    }

    private String getClassName(final Element element) {
	return element.getEnclosingElement().toString();
    }

    /**
     * @return collected scores
     */
    public final Iterable<Score> getScores() {
	return ImmutableList.copyOf(scores);
    }

}
