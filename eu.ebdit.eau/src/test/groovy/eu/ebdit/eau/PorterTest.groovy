package eu.ebdit.eau;

import static org.junit.Assert.*;

class PorterTest {
	
	@org.junit.Test
	public void testMap() throws Exception {
		String key = "key"
		String text = "text"
		Porter.leasePorter("porter").giveBurden(key, text);
		assert text == Porter.leasePorter("porter").inspectBurden(key)
	}

}
