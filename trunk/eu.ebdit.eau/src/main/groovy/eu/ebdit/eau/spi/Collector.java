package eu.ebdit.eau.spi;

/**
 * Collects information of specified type <code>T</code> from object specified
 * by method parameters using the {@link #collectFrom(Object)} method. All
 * public implementations must have public constructors to allow initialization
 * over reflection
 * 
 * @author Vladimír Oraný
 * @param <T>
 *            what should be collected from input
 * @see #collectFrom(Object)
 */
public interface Collector<T> {

    /**
     * Collects information from object given as input parameter. Returns empty
     * {@link Iterable} if cannot get any information from specified input.
     * Should never return <code>null</code>!
     * 
     * @param input
     *            object to get information on
     * @return collected information or empty {@link Iterable} if no information
     *         can be collected by particular implementation
     */
    Iterable<T> collectFrom(Object input);
}
