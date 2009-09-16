package eu.ebdit.eau;

public interface Result {

    String getMessage();

    boolean passed();
    
    String getSuiteName();

    String getCheckName();

}
