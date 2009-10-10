package acme;

public interface Vehicle {
    Direction getDirection();
    void turnLeft();
    void turnRight();
    void prepareTurnLeft();
    void prepareTurnRight();
    boolean isLeftBlinkerOn();
    boolean isRightBlinkerOn();
}
