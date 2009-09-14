package eu.ebdit.eau.testing.annotations;

import java.lang.reflect.Method;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import eu.ebdit.eau.Score;
import eu.ebdit.eau.testing.beans.ScoreBean;

public class ScoreAnnotationCollector {

    public final List<Score> check(final Class<?>... classes) {
	final List<Score> ret = Lists.newArrayList();
	for (Class<?> cl : classes) {
	    ret.addAll(checkClass(cl));
	}
	return ImmutableList.copyOf(ret);
    }

    private List<Score> checkClass(final Class<?> clazz) {
	final List<Score> scores = Lists.newArrayList();
	for (Method m : clazz.getMethods()) {
	    if (m.isAnnotationPresent(Points.class)) {
		scores.add(createScore(clazz, m));
	    }
	}
	return scores;
    }

    private ScoreBean createScore(final Class<?> clazz,
	    final Method method) {
	String desc = null;
	if (method.isAnnotationPresent(Description.class)) {
	    desc = method.getAnnotation(Description.class).value();
	}
	String details = null;
	if (method.isAnnotationPresent(Details.class)) {
	    details = method.getAnnotation(Details.class).value();
	}
	final ScoreBean bean = new ScoreBean();
	bean.setSuiteName(clazz.getName());
	bean.setCheckName(method.getName());
	bean.setPoints(method.getAnnotation(Points.class).value());
	bean.setMessage(desc);
	bean.setBonus(method.isAnnotationPresent(Bonus.class));
	bean.setDetails(details);
	return bean;
    }

}
