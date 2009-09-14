package eu.ebdit.eau.testing;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import eu.ebdit.eau.Reporter;
import eu.ebdit.eau.Report;
import eu.ebdit.eau.Result;
import eu.ebdit.eau.Score;
import eu.ebdit.eau.reports.ReportContainer;
import eu.ebdit.eau.reports.SimpleReport;

public final class TestReporter implements Reporter {

    private final transient Map<String, Map<String, Score>> scoreMap;
    private final transient ImmutableList<Result> resultList;

    private TestReporter(final Map<String, Map<String, Score>> scoreMap,
	    final ImmutableList<Result> resultList) {
	this.scoreMap = scoreMap;
	this.resultList = resultList;
    }

    public Report report() {
	final List<Report> children = Lists.newArrayList();
	for (Result r : resultList) {
	    Map<String, Score> map = scoreMap.get(r.getSuiteName());
	    if (map == null) {
		map = Maps.newHashMap();
	    }
	    final Score score = map.get(r.getCheckName());
	    if (score != null) {
		children.add(getEvaReport(score, r));
	    }

	}
	return ReportContainer.of("Test Reporter", children);
    }

    private Report getEvaReport(final Score Score,
	    final Result result) {
	final String message = Score.getMessage() == null ? result
		.getMessage() : Score.getMessage();
	final double points = result.getStatus().isOK() ? Score.getPoints()
		: 0;
	final double max = Score.isBonus() ? 0 : Score.getPoints();
	final double maxWB = Score.getPoints();
	return new SimpleReport(message, points, max, maxWB);
    }

    public static Reporter of(final Iterable<Score> scoreList, // NOPMD
	    final Iterable<Result> resultList) {
	final Map<String, Map<String, Score>> newMap = Maps.newHashMap();
	for (Score s : scoreList) {
	    Map<String, Score> map = newMap.get(s.getSuiteName());
	    if (map == null) {
		map = Maps.newHashMap();
		newMap.put(s.getSuiteName(), map);
	    }
	    map.put(s.getCheckName(), s);
	}
	return new TestReporter(newMap, ImmutableList.copyOf(resultList));
    }

}
