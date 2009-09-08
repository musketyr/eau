package eu.ebdit.eau.testing;

public interface Result {

	String getClassFQName();

	String getTestName();
	
	Status getStatus();

	String getMessage();

}
