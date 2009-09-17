package eu.ebdit.eau;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.ebdit.eau.Result;


public class ResultTest {
    
    
    @Test
    public void testOfFullName1() throws Exception {
	Result bean = Result.ofFullName("check(Suite)");
	assertCheckSuite(bean);
	assertTrue(bean.isSuccess());
	assertNull(bean.getMessage());
    }
    
    @Test
    public void testOfFullName2() throws Exception {
	Result bean = Result.ofFullName("check(Suite)", false);
	assertCheckSuite(bean);
	assertFalse(bean.isSuccess());
	assertNull(bean.getMessage());
    }
    
    @Test
    public void testOfFullName3() throws Exception {
	Result bean = Result.ofFullName("check(Suite)", false, "Message");
	assertCheckSuite(bean);
	assertFalse(bean.isSuccess());
	assertEquals("Message", bean.getMessage());
    }
    
    @Test
    public void testOf1() throws Exception {
	Result bean = Result.ofNames("Suite", "check");
	assertCheckSuite(bean);
	assertTrue(bean.isSuccess());
	assertNull(bean.getMessage());
    }
    
    @Test
    public void testOf2() throws Exception {
	Result bean = Result.ofNames("Suite", "check", false);
	assertCheckSuite(bean);
	assertFalse(bean.isSuccess());
	assertNull(bean.getMessage());
    }
    
    @Test
    public void testOf3() throws Exception {
	Result bean = Result.ofNames("Suite", "check", false, "Message");
	assertCheckSuite(bean);
	assertFalse(bean.isSuccess());
	assertEquals("Message", bean.getMessage());
    }


    private void assertCheckSuite(final Result bean) {
	assertEquals("check", bean.getCheckName());
	assertEquals("Suite", bean.getSuiteName());
    }
    
}
