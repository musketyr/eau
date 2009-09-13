package eu.ebdit.eau.testing.annotations;

import java.lang.reflect.Method;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import eu.ebdit.eau.testing.TestScore;
import eu.ebdit.eau.testing.beans.TestScoreBean;

public class ScoreAnnotationFinder {

    public List<TestScore> check(final Class<?>... classes){
	final List<TestScore> ret = Lists.newArrayList();
	for (Class<?> cl : classes) {
	    ret.addAll(checkClass(cl));
	}
	return ImmutableList.copyOf(ret);
    }
    
    private List<TestScore> checkClass(final Class<?> clazz) {
	final List<TestScore> scores = Lists.newArrayList();
	for (Method m : clazz.getMethods()) {
	if (m.isAnnotationPresent(Points.class)) {
	    if (!m.isAccessible()) {
		m.setAccessible(true);
	    }
	    scores.add(createTestScore(clazz, m));
	}
	}
	return scores;
    }

    private TestScoreBean createTestScore(final Class<?> clazz, final Method method) {
	String desc = null;
	if (method.isAnnotationPresent(Description.class)) {
	desc = method.getAnnotation(Description.class).value();
	}
	String details = null;
	if (method.isAnnotationPresent(Details.class)) {
	details = method.getAnnotation(Details.class).value();
	}
	final TestScoreBean bean = new TestScoreBean();
	bean.setClassFQName(clazz.getName());
	bean.setTestName(method.getName());
	bean.setPoints(method.getAnnotation(Points.class).value());
	bean.setMessage(desc);
	bean.setBonus(method.isAnnotationPresent(Bonus.class));
	bean.setDetails(details);
	return bean;
    }

}
