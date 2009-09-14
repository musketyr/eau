package eu.ebdit.eau.testing;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import eu.ebdit.eau.Reporter;
import eu.ebdit.eau.Report;
import eu.ebdit.eau.reports.ReportContainer;
import eu.ebdit.eau.reports.SimpleReport;

public final class TestReporter implements Reporter {

    private transient final Map<String, Map<String, TestScore>> scoreMap;
    private transient final ImmutableList<TestResult> resultList;

    private TestReporter(final Map<String, Map<String, TestScore>> scoreMap,
	    final ImmutableList<TestResult> resultList) {
	this.scoreMap = scoreMap;
	this.resultList = resultList;
    }

    public Report report() {
	final List<Report> children = Lists.newArrayList();
	for (TestResult r : resultList) {
	    Map<String, TestScore> map = scoreMap.get(r.getClassName());
	    if (map == null) {
		map = Maps.newHashMap();
	    }
	    final TestScore score = map.get(r.getTestName());
	    if (score != null) {
		children.add(getEvaReport(score, r));
	    }

	}
	return ReportContainer.of("Test Reporter", children);
    }

    private Report getEvaReport(final TestScore testScore, final TestResult result) {
	final String message = testScore.getMessage() == null ? result.getMessage() : testScore
		.getMessage();
	final double points = result.getStatus().isOK() ? testScore.getPoints() : 0;
	final double max = testScore.isBonus() ? 0 : testScore.getPoints();
	final double maxWB = testScore.getPoints();
	return new SimpleReport(message, points, max, maxWB);
    }

    public static Reporter of(final Iterable<TestScore> scoreList, //NOPMD
	    final Iterable<TestResult> resultList) {
	final Map<String, Map<String, TestScore>> newMap = Maps.newHashMap();
	for (TestScore s : scoreList) {
	    Map<String, TestScore> map = newMap.get(s.getClassName());
	    if (map == null) {
		map = Maps.newHashMap();
		newMap.put(s.getClassName(), map);
	    }
	    map.put(s.getTestName(), s);
	}
	return new TestReporter(newMap, ImmutableList.copyOf(resultList));
    }

}
