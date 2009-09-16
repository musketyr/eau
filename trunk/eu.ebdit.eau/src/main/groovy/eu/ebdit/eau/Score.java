package eu.ebdit.eau;

public interface Score {

    String getMessage();
    
    String getDetails();
    
    double getPoints();

    boolean isBonus();
    
    String getSuiteName();

    String getCheckName();

}
