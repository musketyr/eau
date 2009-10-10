package acme;

public class VeteranCar implements Vehicle{
    
    private Direction direction = Direction.NORTH;
    
    @Override public Direction getDirection() { return direction; }
    
    @Override public boolean isLeftBlinkerOn() { return false; }
    
    @Override public boolean isRightBlinkerOn() { return false; }
    
    @Override public void prepareTurnLeft() {}
    
    @Override public void prepareTurnRight() {}

    @Override public void turnLeft() { direction = direction.left(); }

    @Override public void turnRight() { direction = direction.right(); }
    
}
