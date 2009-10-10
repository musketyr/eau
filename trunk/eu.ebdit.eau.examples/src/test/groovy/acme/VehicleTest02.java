package acme;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.ebdit.eau.Points;

public class VehicleTest02 {

    protected Vehicle fixture;
    
    // setup and other tests
    
    @Points(2)
    @Test public void testTurnRight(){
	Direction original = fixture.getDirection();
	fixture.turnRight();
	assertEquals(original.right(), fixture.getDirection());
    }
    
    @Points(0.5)
    @Test public void testPrepareRight(){
	Direction original = fixture.getDirection();
	fixture.prepareTurnRight();
	assertEquals(original, fixture.getDirection());
	assertTrue(fixture.isRightBlinkerOn());
	assertFalse(fixture.isLeftBlinkerOn());
    }
    
    
    
}
