package eu.ebdit.eau.testing;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import eu.ebdit.eau.Eau;
import eu.ebdit.eau.EauReport;
import eu.ebdit.eau.reports.ReportContainer;
import eu.ebdit.eau.reports.SimpleEauReport;


public class TestingEau implements Eau {

	private final Map<String, Map<String, TestScore>> scoreMap;
	private final ImmutableList<Result> resultList;
	
	
	
	private TestingEau(Map<String, Map<String, TestScore>> scoreMap,
			ImmutableList<Result> resultList) {
		this.scoreMap = scoreMap;
		this.resultList = resultList;
	}

	public EauReport report() {
		List<EauReport> children = Lists.newArrayList();
		for (Result r : resultList) {
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

	private EauReport getEvaReport(TestScore ts, Result r) {
		String message = ts.getMessage() == null ? r.getMessage() : ts.getMessage();
		double points = r.getStatus().isOK() ? ts.getPoints() : 0;
		double max = ts.isBonus() ? 0 : ts.getPoints();
		double maxWB = ts.getPoints();
		return new SimpleEauReport(message, points, max, maxWB);
	}

	public static Eau of(Iterable<TestScore> scoreList, Iterable<Result> resultList) {
		Map<String,Map<String, TestScore>> newMap = Maps.newHashMap();
		for (TestScore s : scoreList) {
			Map<String, TestScore> map = newMap.get(s.getClassFQName());
			if (map == null) {
				map = Maps.newHashMap();
				newMap.put(s.getClassFQName(), map);
			}
			map.put(s.getTestName(), s);
		}
		return new TestingEau(newMap, ImmutableList.copyOf(resultList));
	}

}
