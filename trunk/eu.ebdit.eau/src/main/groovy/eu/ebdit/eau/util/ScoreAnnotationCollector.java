package eu.ebdit.eau.util;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import eu.ebdit.eau.Bonus;
import eu.ebdit.eau.Collector;
import eu.ebdit.eau.Description;
import eu.ebdit.eau.Details;
import eu.ebdit.eau.Points;
import eu.ebdit.eau.Score;
import eu.ebdit.eau.beans.ScoreBean;

public class ScoreAnnotationCollector implements Collector<Score>{
   
    @Override
    public boolean canCollectFrom(final Object input) {
	return !Iterables.isEmpty(Classes.asClassIterable(input));
    }

    public final List<Score> collectFrom(final Object input) {
	if (!canCollectFrom(input)) {
	    return Collections.emptyList();
	}
	final List<Score> ret = Lists.newArrayList();
	for (Class<?> cl : Classes.asClassIterable(input)) {
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
