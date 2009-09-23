package eu.ebdit.eau.util;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.ServiceLoader;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;

import eu.ebdit.eau.Result;
import eu.ebdit.eau.Score;
import eu.ebdit.eau.spi.Collector;

/**
 * Utility class for collectors service discovery.
 * 
 * @author Vladimir Orany
 * 
 */
@SuppressWarnings("unchecked")
public final class Collectors {

    public static final Collector<Result> RESULT_COLLECTOR_FOOTPRINT  // NOPMD
    	= new Collector<Result>() {

	    @Override
	    public Iterable<Result> collectFrom(final Object input) {
		return Collections.emptyList();
	    }
	
    	};
    	
    public static final Collector<Score> SCORE_COLLECTOR_FOOTPRINT // NOPMD 
    	= new Collector<Score>() {

	    @Override
	    public Iterable<Score> collectFrom(final Object input) {
		return Collections.emptyList();
	    }
	
    	};
    
    private static final class IsOfSpecificType implements
	    Predicate<Collector> {

	private final transient Class<?> specifiedType;

	public IsOfSpecificType(final Class<?> specifiedType) {
	    checkNotNull(specifiedType);
	    this.specifiedType = specifiedType;
	}

	@Override
	public boolean apply(final Collector collector) {
	    checkNotNull(collector);
	    for (Type iface : collector.getClass().getGenericInterfaces()) {
		return isCollectorOfSpecifiedType(iface);
	    }
	    return false;
	}

	private boolean isCollectorOfSpecifiedType(final Type iface) {
	    if (iface instanceof ParameterizedType) {
		final ParameterizedType paramType = (ParameterizedType) iface;
		if (Collector.class.equals(paramType.getRawType())
			&& specifiedType.equals(paramType
				.getActualTypeArguments()[0])

		) {
		    return true;
		}
	    }
	    return false;
	}

    }
    private static final Predicate<Collector> RESULT_FILTER 
    	= new IsOfSpecificType(Result.class);

    private static final Predicate<Collector> SCORE_FILTER 
    	= new IsOfSpecificType(Score.class);

    /**
     * @return all result collectors registered as service
     */
    public static Iterable<Collector<Result>> getResultCollectors() {
	return getFilteredCollectors(RESULT_FILTER);
    }

    /**
     * @return all score collectors registred as service
     */
    public static Iterable<Collector<Score>> getScoreCollectors() {
	return getFilteredCollectors(SCORE_FILTER);
    }

    private static Iterable<Collector> getAllCollectors() {
	return ServiceLoader.load(Collector.class);
    }

    private static <T> Iterable<Collector<T>> getFilteredCollectors(
	    final Predicate<Collector> filter) {
	final Iterable<Collector> loader = getAllCollectors();
	final ImmutableList.Builder<Collector<T>> ret = 
	    ImmutableList.builder();
	for (Collector collector : loader) {
	    if (filter.apply(collector)) {
		ret.add(collector);
	    }
	}
	return ret.build();
    }

    private Collectors() {
	// prevents instance creation and subtyping
    }

}
