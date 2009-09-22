package eu.ebdit.eau.helper;

import java.util.Collection;
import java.util.concurrent.Callable;

import com.google.common.collect.Lists;

import eu.ebdit.eau.util.Classes;

/**
 * This class helps to test classes which depends on some class loading issues.
 * Works the best with {@link Classes#existingClass(Object)}.
 * 
 * @author Vladimir Orany
 * 
 */
public final class ClassSuppressor {

    private final transient Collection<String> classes = Lists.newArrayList();
    private final transient Collection<String> packages = Lists.newArrayList();
    private final transient Collection<String> regexs = Lists.newArrayList();;

    /**
     * Suppress class from being loaded by thread context class loader.
     * 
     * @param suppressedClass
     *            name of class to be suppressed
     * @return modified instance of class supressor
     */
    public ClassSuppressor suppressClass(final String suppressedClass) {
	classes.add(suppressedClass);
	return this;
    }

    /**
     * Suppress package and all its subpackages from being loaded by thread
     * context class loader.
     * 
     * @param suppressedPackage
     *            name of the root package to be suppressed
     * @return modified instance of class supressor
     */
    public ClassSuppressor suppressPackage(final String suppressedPackage) {
	packages.add(suppressedPackage);
	return this;
    }

    /**
     * Suppress all classes matching particular regular expression from being
     * loaded by thread context class loader.
     * 
     * @param regExp
     *            regular expression describing classes to be suppressed
     * @return modified instance of class suppressor
     */
    public ClassSuppressor suppressByRegExp(final String regExp) {
	regexs.add(regExp);
	return this;
    }

    /**
     * Runs piece of code with some classes suppressed.
     * 
     * @param <T> type of result returned by job
     * @param job
     *            piece of code where must be some classes suppressed
     * @return result of job call
     */
    public <T> T runSuppressed(final Callable<T> job) {
	final ClassLoader original = supressLoader();
	try {
	    try {
		return job.call();
	    } finally {
		restoreLoader(original);
	    }
	} catch (Exception e) {
	    throw new RuntimeException(e); // NOPMD
	}

    }

    private ClassLoader supressLoader() {
	final ClassLoader loader = Thread.currentThread()
		.getContextClassLoader();
	Thread.currentThread().setContextClassLoader(new FakeClassLoader());
	return loader;
    }

    private void restoreLoader(final ClassLoader loader) {
	Thread.currentThread().setContextClassLoader(loader);
    }

    private class FakeClassLoader extends ClassLoader {

	@Override
	public Class<?> loadClass(final String name)
		throws ClassNotFoundException {
	    if (classes.contains(name)) {
		throw new ClassNotFoundException();
	    }
	    for (String pkg : packages) {
		if (name.startsWith(pkg)) {
		    throw new ClassNotFoundException();
		}
	    }
	    for (String regex : regexs) {
		if (name.matches(regex)) {
		    throw new ClassNotFoundException();
		}
	    }
	    return super.loadClass(name);
	}
    }

}
