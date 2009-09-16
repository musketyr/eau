package eu.ebdit.eau;

public interface Collector<T> {
    boolean canCollectFrom(Object input);
    Iterable<T> collectFrom(Object input);
}
