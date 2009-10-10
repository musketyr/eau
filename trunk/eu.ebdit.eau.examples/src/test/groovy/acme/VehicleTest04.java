package acme;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.ebdit.eau.Bonus;
import eu.ebdit.eau.Description;
import eu.ebdit.eau.Details;
import eu.ebdit.eau.Points;

@Bonus @Points(1)
@Description("Vehicle functionality should be complete")
public class VehicleTest04 {

    protected Vehicle fixture;
    
    // setup and other tests
    
    @Points(2)
    @Description("Vehicle changes direction to proper one after turning right")
    @Test public void testTurnRight(){
	Direction original = fixture.getDirection();
	fixture.turnRight();
	assertEquals(original.right(), fixture.getDirection());
    }
    
    @Bonus @Points(0.5)
    @Description("Vehicle blinks right when preparing to turn right")
    @Details("Vehicle must keep original direction when preparing to turn")
    @Test public void testPrepareRight(){
	Direction original = fixture.getDirection();
	fixture.prepareTurnRight();
	assertEquals(original, fixture.getDirection());
	assertTrue(fixture.isRightBlinkerOn());
	assertFalse(fixture.isLeftBlinkerOn());
    }
    
    
    
}
