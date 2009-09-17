package eu.ebdit.eau.util;

import static com.google.common.collect.Iterables.isEmpty;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import eu.ebdit.eau.Collector;

/**
 * Abstract test for {@link Collector collectors}.
 * 
 * @author Vladimir Orany
 * 
 * @param <T>
 *            type of collected items
 */
public abstract class AbstractCollectorTest<T> {

    private transient Collector<T> fixure;

    /**
     * Sets up right collector to be test.
     */
    @Before
    public final void setUp() {
	fixure = newCollector();
    }

    /**
     * Tests whether items collected from {@link #collect()} are equals to
     * expected values.
     */
    @Test
    public final void testCollect() {
	assertCollectedItems(collect());
    }

    /**
     * Tests whether all iterables collected from {@link #getInputsToSucceed()}
     * are not empty and all iterables collected from {@link #getInputsToFail()}
     * are not empty.
     */
    public final void testInputs() {
	for (Object toSucceed : getInputsToSucceed()) {
	    assertFalse("Items collected from " + toSucceed + " was empty!",
		    isEmpty(getFixure().collectFrom(toSucceed)));
	}
	for (Object toFail : getInputsToFail()) {
	    assertTrue("Items collected from " + toFail + " was not empty!",
		    isEmpty(getFixure().collectFrom(toFail)));
	}
    }

    /**
     * @return tested collector
     */
    protected final Collector<T> getFixure() {
	return fixure;
    }

    /**
     * @return items collected from {@link #getInputForResults()} object
     */
    protected final Iterable<T> collect() {
	return getFixure().collectFrom(getInputForResults());
    }

    /**
     * @return new instance of the collector
     */
    protected abstract Collector<T> newCollector();

    /**
     * @return input to be used for collecting assertion items
     */
    protected abstract Object getInputForResults();

    /**
     * @return return inputs for which must collector return non-empty iterable
     */
    protected abstract Iterable<Object> getInputsToSucceed();

    /**
     * @return return inputs for which must collector return empty iterable
     */
    protected abstract Iterable<Object> getInputsToFail();

    /**
     * @param collected
     *            items collected from {@link #getInputForResults()} to be
     *            asserted as valid
     */
    protected abstract void assertCollectedItems(Iterable<T> collected);

}
