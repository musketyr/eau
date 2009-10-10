package acme;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest01 {

    protected Vehicle fixture;
    
    // setup and other tests
    
    @Test public void testTurnRight(){
	Direction original = fixture.getDirection();
	fixture.turnRight();
	assertEquals(original.right(), fixture.getDirection());
    }
    
    @Test public void testPrepareRight(){
	Direction original = fixture.getDirection();
	fixture.prepareTurnRight();
	assertEquals(original, fixture.getDirection());
	assertTrue(fixture.isRightBlinkerOn());
	assertFalse(fixture.isLeftBlinkerOn());
    }
    
    
    
}
