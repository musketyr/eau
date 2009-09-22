package eu.ebdit.eau.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import eu.ebdit.eau.Result;
import eu.ebdit.eau.Score;
import eu.ebdit.eau.junit.JUnitResultCollector;
import eu.ebdit.eau.spi.Collector;

/**
 * Test for {@link Collectors}.
 * 
 * @author Vladimir Orany
 * 
 */
public final class CollectorsTest {

    private static final class ContainsInstance<T> implements
	    Predicate<Collector<T>> {

	private final transient Class<?> desiredClass;

	public ContainsInstance(final Class<?> desiredClass) {
	    Preconditions.checkNotNull(desiredClass);
	    this.desiredClass = desiredClass;
	}

	@Override
	public boolean apply(final Collector<T> input) {
	    return desiredClass.equals(input.getClass());
	}
    }

    /**
     * Test for {@link Collectors#getResultCollectors()}.
     */
    @Test
    public void testResultCollectors() {
	final Iterable<Collector<Result>> resultCollectors = Collectors
		.getResultCollectors();
	assertRegistered(false, resultCollectors, ScoreAnnotationCollector.class);
	assertRegistered(false, resultCollectors, XmlScoreParser.class);
	assertRegistered(true, resultCollectors, JUnitResultCollector.class);
	assertRegistered(true, resultCollectors, XmlResultParser.class);
    }

    /**
     * Test for {@link Collectors#getScoreCollectors()}.
     */
    @Test
    public void testScoreCollectors() {
	final Iterable<Collector<Score>> scoreCollectors = Collectors
		.getScoreCollectors();
	assertRegistered(true, scoreCollectors, ScoreAnnotationCollector.class);
	assertRegistered(true, scoreCollectors, XmlScoreParser.class);
	assertRegistered(false, scoreCollectors, JUnitResultCollector.class);
	assertRegistered(false, scoreCollectors, XmlResultParser.class);
    }

    private <T> void assertRegistered(final boolean registered,
	    final Iterable<Collector<T>> collectors,
	    final Class<?> toBeRegistered) {
	assertEquals(toBeRegistered.getName() + " must "
		+ (registered ? "" : "not ") + "be registered!", registered,
		Iterables.any(collectors, new ContainsInstance<T>(
			toBeRegistered)));
    }

}
