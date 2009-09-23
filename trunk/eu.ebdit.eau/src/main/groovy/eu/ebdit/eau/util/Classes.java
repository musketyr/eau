package eu.ebdit.eau.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * Utility class for handling object which are supposed to be or contain
 * classes.
 * 
 * @author Vladimir Orany
 * 
 */
public final class Classes {

    private Classes() {
	// prevents instance creation and subtyping
    }

    /**
     * Converts input to class if exists. Returns <code>null</code> if input is
     * <code>null</code>. Returns input itself if input is already class object.
     * For non-string and non-class inputs {@link Object#toString()} method is
     * used to find out class name.
     * 
     * @param input
     *            object describing class
     * @return class object described by input if exist or <code>null</code>
     *         otherwise
     */
    @SuppressWarnings("unchecked")
    public static Class<?> existingClass(final Object input) {
	if (input == null) {
	    return null;
	}
	if (input instanceof Class) {
	    return (Class<?>) input;
	}
	try {
	    return Class.forName(input.toString(), false, Thread
		    .currentThread().getContextClassLoader());
	} catch (ClassNotFoundException e) {
	    return null;
	}
    }

    /**
     * Converts object to iterable of class objects. Returns empty iterable if
     * there is no way how to convert object to class iterable.
     * 
     * @param object
     *            object representing zero or more classes
     * @return iterable of classes described by object input or empty iterable
     *         if there is no way how to derive class objects from input object
     */
    @SuppressWarnings("unchecked")
    public static Iterable<Class<?>> asClassIterable(final Object object) {
	if (object == null) {
	    return Collections.emptyList();
	}
	if (object instanceof Class) {
	    return Arrays.asList(new Class<?>[] { (Class<?>) object });
	}
	final Class<?> classForName = Classes.existingClass(object);
	if (classForName != null) {
	    return Arrays.asList(new Class<?>[] { classForName });
	}

	Iterable<Object> objList = null;
	if (object.getClass().isArray()) {
	    objList = Arrays.asList((Object[]) object);
	}
	if (object instanceof Iterable) {
	    objList = (Iterable<Object>) object;
	}
	if (objList == null) {
	    return Collections.emptyList();
	}
	final List<Class<?>> ret = Lists.newArrayList();
	for (Object obj : objList) {
	    final Class<?> clazz = Classes.existingClass(obj);
	    if (clazz != null) {
		ret.add(clazz);
	    }
	}
	return ImmutableList.copyOf(ret);
    }

    /**
     * Converts object to array of class objects. Returns empty array if there
     * is no way how to convert the input.
     * 
     * @param input
     *            object representing zero or more class objects
     * @return array of class objects derived from input object or empty array
     */
    public static Class<?>[] asClassArray(final Object input) {
	return ImmutableList.copyOf(asClassIterable(input)).toArray(
		new Class<?>[] {});
    }

}
