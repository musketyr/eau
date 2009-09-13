package eu.ebdit.eau.testing.annotations;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.processing.Processor;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import eu.ebdit.eau.testing.TestReporterTest;
import eu.ebdit.eau.testing.TestScore;

public class ScoreAnnotationProcessorTest extends TestReporterTest {

    @Override
    protected Iterable<TestScore> getScoreList() throws Exception {
	ScoreAnnotationProcessor processor = new ScoreAnnotationProcessor();

	// Get an instance of java compiler
	JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

	// Get a new instance of the standard file manager implementation
	StandardJavaFileManager fileManager = compiler.getStandardFileManager(
		null, null, null);

	// Get the list of java file objects, in this case we have only
	// one file, TestClass.java
	Iterable<? extends JavaFileObject> compilationUnits1 = fileManager.getJavaFileObjects(new File(getClass().getResource("/org/example/TestClass.java").toURI()));

	CompilationTask task = compiler.getTask(null, fileManager, null, null,
		null, compilationUnits1);

	// Create a list to hold annotation processors
	List<Processor> processors = new LinkedList<Processor>();

	// Add an annotation processor to the list
	processors.add(processor);

	// Set the annotation processor to the compiler task
	task.setProcessors(processors);

	// Perform the compilation task.
	boolean ret = task.call();
	assertTrue(ret);
	return processor.getScores();
    }

}
