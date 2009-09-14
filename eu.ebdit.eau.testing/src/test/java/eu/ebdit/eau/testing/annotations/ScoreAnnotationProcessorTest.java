package eu.ebdit.eau.testing.annotations;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.processing.Processor;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

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

	final List<File> newCpath = Lists
		.newArrayList(((Iterable<? extends File>) fileManager
			.getLocation(StandardLocation.CLASS_PATH)).iterator());
	newCpath.add(new File(Joiner.on(File.separator).join(
		System.getenv("M2_REPO"), "junit", "junit", "4.5",
		"junit-4.5.jar")));
	newCpath.add(new File(ScoreAnnotationProcessorTest.class.getResource(
		"/").toURI()));
	newCpath.add(new File(new File(ScoreAnnotationProcessorTest.class
		.getResource("/").toURI()).getParentFile().getAbsolutePath()
		+ File.separator + "classes" + File.separator));
	fileManager.setLocation(StandardLocation.CLASS_PATH, newCpath);

	// Get the list of java file objects, in this case we have only
	// one file, TestClass.java
	final Iterable<? extends JavaFileObject> compilationUnits1 = fileManager
		.getJavaFileObjects(new File(ScoreAnnotationProcessorTest.class
			.getResource("/org/example/TestClass.java").toURI()));

	final CompilationTask task = compiler.getTask(null, fileManager, null,
		null, null, compilationUnits1);

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

}
