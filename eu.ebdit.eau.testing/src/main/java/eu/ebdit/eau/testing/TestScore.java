package eu.ebdit.eau.testing;

public interface TestScore {

    String getClassFQName();

    String getTestName();

    double getPoints();

    String getMessage();

    boolean isBonus();

}
