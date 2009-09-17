package eu.ebdit.eau;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import eu.ebdit.eau.reports.ReportContainer;
import eu.ebdit.eau.reports.SimpleReport;

public final class Reporter {

    private final Iterable<Collector<Result>> resultCollectors;
    private final Iterable<Collector<Score>> scoreCollectors;

    private Reporter(Iterable<Collector<Result>> resultCollectors,
	    Iterable<Collector<Score>> scoreCollectors) {
	this.resultCollectors = ImmutableList.copyOf(resultCollectors);
	this.scoreCollectors = ImmutableList.copyOf(scoreCollectors);
    }

    public Iterable<Collector<Result>> getResultCollectors() {
	return resultCollectors;
    }

    public Iterable<Collector<Score>> getScoreCollectors() {
	return scoreCollectors;
    }

    public Report report(Object input) {
	final Map<String, Map<String, Score>> scoreMap = collectScoresToMap(input);
	final List<Report> children = Lists.newArrayList();
	for (Result r : collectResults(input)) {
	    Map<String, Score> map = scoreMap.get(r.getSuiteName());
	    if (map == null) {
		map = Maps.newHashMap();
	    }
	    final Score score = map.get(r.getCheckName());
	    if (score != null) {
		children.add(getReport(score, r));
	    }

	}
	return ReportContainer.of("Test Reporter", children);
    }

    private Map<String, Map<String, Score>> collectScoresToMap(Object input) {
	return scoresToMap(collectScores(input));
    }

    private Iterable<Score> collectScores(Object input) {
	return Iterables.concat(Iterables.transform(scoreCollectors,
		new CollectByCollector<Score>(input)));
    }

    private Iterable<Result> collectResults(Object input) {
	return Iterables.concat(Iterables.transform(resultCollectors,
		new CollectByCollector<Result>(input)));
    }

    private Report getReport(final Score Score, final Result result) {
	final String message = Score.getDescription() == null ? result
		.getMessage() : Score.getDescription();
	final double points = result.isSuccess() ? Score.getPoints() : 0;
	final double max = Score.isBonus() ? 0 : Score.getPoints();
	final double maxWB = Score.getPoints();
	return new SimpleReport(message, points, max, maxWB);
    }

    private static Map<String, Map<String, Score>> scoresToMap(
	    final Iterable<Score> scores) {
	final Map<String, Map<String, Score>> newMap = Maps.newHashMap();
	for (Score s : scores) {
	    Map<String, Score> map = newMap.get(s.getSuiteName());
	    if (map == null) {
		map = Maps.newHashMap();
		newMap.put(s.getSuiteName(), map);
	    }
	    map.put(s.getCheckName(), s);
	}
	return newMap;
    }

    public static Builder withResultCollectors(Collector<Result> first,
	    Collector<Result>... rest) {
	return new Builder().withResultCollectors(first, rest);
    }

    public static Builder withScoreCollectors(Collector<Score> first,
	    Collector<Score>... rest) {
	return new Builder().withScoreCollectors(first, rest);
    }

    public static Builder withResultCollectors(Iterable<Collector<Result>> collectors) {
	return new Builder().withResultCollectors(collectors);
    }

    public static Builder withScoreCollectors(Iterable<Collector<Score>> collectors) {
	return new Builder().withScoreCollectors(collectors);
    }

    public static final class Builder {
	private final Collection<Collector<Result>> resultCollectors = Lists
		.newArrayList();
	private final Collection<Collector<Score>> scoreCollectors = Lists
		.newArrayList();

	public Builder withResultCollectors(Collector<Result> first,
		Collector<Result>... rest) {
	    resultCollectors.addAll(Lists.asList(first, rest));
	    return this;
	}

	public Builder withScoreCollectors(Collector<Score> first,
		Collector<Score>... rest) {
	    scoreCollectors.addAll(Lists.asList(first, rest));
	    return this;
	}

	public Builder withResultCollectors(
		Iterable<Collector<Result>> collectors) {
	    resultCollectors.addAll(Lists.newArrayList(collectors));
	    return this;
	}

	public Builder withScoreCollectors(Iterable<Collector<Score>> collectors) {
	    scoreCollectors.addAll(Lists.newArrayList(collectors));
	    return this;
	}

	public Reporter build() {
	    Preconditions.checkState(!resultCollectors.isEmpty(),
		    "There must be at least one result collector");
	    Preconditions.checkState(!scoreCollectors.isEmpty(),
		    "There must be at least one score collector");
	    return new Reporter(resultCollectors, scoreCollectors);
	}

    }

    private static final class CollectByCollector<T> implements
	    Function<Collector<T>, Iterable<T>> {

	private final Object input;

	public CollectByCollector(Object input) {
	    this.input = input;
	}

	@Override
	public Iterable<T> apply(Collector<T> from) {
	    return from.collectFrom(input);
	}
    }

}
