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

import eu.ebdit.eau.testing.TestScore;
import eu.ebdit.eau.testing.beans.TestScoreBean;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("eu.ebdit.eau.testing.annotations.*")
public class ScoreAnnotationProcessor extends AbstractProcessor {

    private final List<TestScore> scores = Lists.newArrayList();
    
    
    @Override
    public boolean process(Set<? extends TypeElement> annotations,
	    RoundEnvironment roundEnv) {
	System.out.println(annotations);
	for (Element e : roundEnv.getElementsAnnotatedWith(Points.class)) {
	    scores.add(getScoreFromElement(e));
	}
	return false;
    }

    private TestScoreBean getScoreFromElement(Element e) {
	Points points = e.getAnnotation(Points.class);
	Details details = e.getAnnotation(Details.class);
	Bonus bonus = e.getAnnotation(Bonus.class);
	Description desc = e.getAnnotation(Description.class);
	TestScoreBean score = new TestScoreBean();
	score.setBonus(bonus != null);
	score.setPoints(points.value());
	score.setMessage(desc == null ? "" : desc.value());
	score.setClassFQName(getClassFQName(e));
	score.setDetails(details == null ? null : details.value());
	score.setTestName(e.getSimpleName().toString());
	return score;
    }

    private String getClassFQName(Element e) {
	return e.getEnclosingElement().toString();
    }

    public Iterable<TestScore> getScores() {
	return ImmutableList.copyOf(scores);
    }

}
