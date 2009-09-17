package eu.ebdit.eau.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public final class Classes {

    private Classes() {
	// prevents instance creation and subtyping
    }

    public static Class<?> classForName(final Object name) {
	if (name == null) {
	    return null;
	}
	try {
	    return Class.forName(name.toString(), false, Thread.currentThread()
		    .getContextClassLoader());
	} catch (ClassNotFoundException e) {
	    return null;
	}
    }

    public static Iterable<Class<?>> asClassIterable(final Object object) {
	if (object == null) {
	    return Collections.emptyList();
	}
	if (Class.class.isAssignableFrom(object.getClass())) {
	    return Arrays.asList(new Class<?>[]{ (Class<?>) object});
	}
	if (object.getClass().isArray()) {
	    if (Class.class.isAssignableFrom(object.getClass()
		    .getComponentType())) {
		return Arrays.asList((Class<?>[]) object);
	    }
	    List<Object> objList = Arrays.asList((Object[]) object);
	    List<Class<?>> ret = Lists.newArrayList();
	    for (Object obj : objList) {
		if (obj != null) {
		    if (Class.class.isAssignableFrom(obj.getClass())) {
			ret.add((Class<?>) obj);
		    }
		    final Class<?> classForName = Classes
		    .classForName(obj);
		    if (classForName != null) {
			ret.add(classForName);
		    }
		}
	    }
	    return ImmutableList.copyOf(ret);
	}
	final Class<?> classForName = Classes.classForName(object);
	if (classForName != null) {
	    return Arrays.asList(new Class<?>[]{classForName});
	}
	return Collections.emptyList();
    }

    public static Class<?>[] asClassArray(final Object input) {
	return ImmutableList.copyOf(asClassIterable(input)).toArray(
		new Class<?>[] {});
    }

}
