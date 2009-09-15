package eu.ebdit.eau.helper;

import java.util.Collection;
import java.util.concurrent.Callable;

import com.google.common.collect.Lists;

public final class ClassSuppressor {

    private final Collection<String> classes = Lists.newArrayList();
    private final Collection<String> packages = Lists.newArrayList();
    private final Collection<String> regexs = Lists.newArrayList();;

    public void suppressClass(String suppressedClass) {
	classes.add(suppressedClass);
    }

    public void suppressPackage(String suppressedPackage) {
	packages.add(suppressedPackage);
    }

    public void suppressByRegExp(String regExp) {
	regexs.add(regExp);
    }

    public <T> T runSuppressed(Callable<T> job) {
	ClassLoader original = supressLoader();
	try {
	    try {
		return job.call();
	    } finally {
		restoreLoader(original);
	    }
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}

    }

    private ClassLoader supressLoader() {
	final ClassLoader loader = Thread.currentThread()
		.getContextClassLoader();
	Thread.currentThread().setContextClassLoader(new FakeClassLoader());
	return loader;
    }

    private void restoreLoader(ClassLoader loader) {
	Thread.currentThread().setContextClassLoader(loader);
    }

    private class FakeClassLoader extends ClassLoader {

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
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
