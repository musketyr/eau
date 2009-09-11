package eu.ebdit.eau.testing.annotations;

import java.lang.reflect.Method;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import eu.ebdit.eau.testing.TestScore;
import eu.ebdit.eau.testing.beans.TestScoreBean;

public class ScoreAnnotationFinder {

    public List<TestScore> check(Class<?>... classes){
	List<TestScore> ret = Lists.newArrayList();
	for (Class<?> cl : classes) {
	    ret.addAll(checkClass(cl));
	}
	return ImmutableList.copyOf(ret);
    }
    
    private List<TestScore> checkClass(Class<?> clazz) {
	List<TestScore> scores = Lists.newArrayList();
	for (Method m : clazz.getMethods()) {
	if (m.isAnnotationPresent(Points.class)) {
	    if (!m.isAccessible()) {
		m.setAccessible(true);
	    }
	    double points = m.getAnnotation(Points.class).value();
	    String desc = null;
	    if (m.isAnnotationPresent(Description.class)) {
		desc = m.getAnnotation(Description.class).value();
	    }
	    String details = null;
	    if (m.isAnnotationPresent(Details.class)) {
		details = m.getAnnotation(Details.class).value();
	    }
	    boolean bonus = m.isAnnotationPresent(Bonus.class);
	    scores.add(TestScoreBean.of(clazz.getName(), m.getName(), points,
		    desc, bonus, details));
	}
	}
	return scores;
    }

}
