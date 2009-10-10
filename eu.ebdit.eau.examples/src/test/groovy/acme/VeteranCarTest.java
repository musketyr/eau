package acme;

import org.junit.Before;

public class VeteranCarTest extends VehicleTest03 {
    @Before public void setUp(){
	fixture = new VeteranCar();
    }
}
