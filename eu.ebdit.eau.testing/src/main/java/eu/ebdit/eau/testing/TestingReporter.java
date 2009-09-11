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

public class TestingReporter implements Reporter {

    private final Map<String, Map<String, TestScore>> scoreMap;
    private final ImmutableList<TestResult> resultList;

    private TestingReporter(Map<String, Map<String, TestScore>> scoreMap,
	    ImmutableList<TestResult> resultList) {
	this.scoreMap = scoreMap;
	this.resultList = resultList;
    }

    public Report report() {
	List<Report> children = Lists.newArrayList();
	for (TestResult r : resultList) {
	    Map<String, TestScore> m = scoreMap.get(r.getClassFQName());
	    if (m == null) {
		// TODO: what to do?
	    } else {
		TestScore ts = m.get(r.getTestName());
		if (ts == null) {
		    // TODO: what to do?
		} else {
		    children.add(getEvaReport(ts, r));
		}
	    }
	}
	return ReportContainer.of("TestingEva Report", children);
    }

    private Report getEvaReport(TestScore ts, TestResult r) {
	String message = ts.getMessage() == null ? r.getMessage() : ts
		.getMessage();
	double points = r.getStatus().isOK() ? ts.getPoints() : 0;
	double max = ts.isBonus() ? 0 : ts.getPoints();
	double maxWB = ts.getPoints();
	return new SimpleReport(message, points, max, maxWB);
    }

    public static Reporter of(Iterable<TestScore> scoreList,
	    Iterable<TestResult> resultList) {
	Map<String, Map<String, TestScore>> newMap = Maps.newHashMap();
	for (TestScore s : scoreList) {
	    Map<String, TestScore> map = newMap.get(s.getClassFQName());
	    if (map == null) {
		map = Maps.newHashMap();
		newMap.put(s.getClassFQName(), map);
	    }
	    map.put(s.getTestName(), s);
	}
	return new TestingReporter(newMap, ImmutableList.copyOf(resultList));
    }

}
