package eu.ebdit.eau.testing.annotations;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.processing.Processor;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import com.google.common.collect.ImmutableList;

import eu.ebdit.eau.testing.TestReporterTest;
import eu.ebdit.eau.testing.TestScore;

public class ScoreAnnotationProcessorTest extends TestReporterTest {// NOPMD

    @Override
    protected Iterable<TestScore> getScoreList() throws URISyntaxException,
	    IOException {// NOPMD
	final ScoreAnnotationProcessor processor = new ScoreAnnotationProcessor();

	// Get an instance of java compiler
	final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

	// Get a new instance of the standard file manager implementation
	final StandardJavaFileManager fileManager = compiler
		.getStandardFileManager(null, null, null);

	System.out.println(fileManager.getLocation(StandardLocation.CLASS_PATH));
	
	// Get the list of java file objects, in this case we have only
	// one file, TestClass.java
	final Iterable<? extends JavaFileObject> compilationUnits1 = fileManager
		.getJavaFileObjects(new File(ScoreAnnotationProcessorTest.class
			.getResource("/org/example/TestClass.java").toURI()));
	
	//final String $ = File.pathSeparator;
	Iterable<String> options = ImmutableList.of();//ImmutableList.of("-cp \"" + cpath + ";%M2_REPO%" + $ + "junit" + $ + "junit" + $ + "4.5" + $ + "junit-4.5.jar\"");
	
	final CompilationTask task = compiler.getTask(null, fileManager, null,
		options, null, compilationUnits1);
	
	
	// Create a list to hold annotation processors
	final List<Processor> processors = new LinkedList<Processor>();

	// Add an annotation processor to the list
	processors.add(processor);

	// Set the annotation processor to the compiler task
	task.setProcessors(processors);

	// Perform the compilation task.
	final boolean ret = task.call();
	assertTrue(ret);// NOPMD
	return processor.getScores();
    }

//    public static void addFile(String s) throws IOException {
//	File f = new File(s);
//	addFile(f);
//    }// end method
//
//    @SuppressWarnings("deprecation")
//    public static void addFile(File f) throws IOException {
//	addURL(f.toURL());
//    }// end method
//
//    public static void addURL(URL u) throws IOException {
//
//	URLClassLoader sysloader = (URLClassLoader) ClassLoader
//		.getSystemClassLoader();
//	Class<?> sysclass = URLClassLoader.class;
//
//	try {
//	    Method method = sysclass.getDeclaredMethod("addURL", new Class[]{URL.class});
//	    method.setAccessible(true);
//	    method.invoke(sysloader, new Object[] { u });
//	} catch (Throwable t) {
//	    t.printStackTrace();
//	    throw new IOException(
//		    "Error, could not add URL to system classloader");
//	}// end try catch
//
//    }// end method

}
