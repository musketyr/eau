package eu.ebdit.eau.beans;

import static org.junit.Assert.*;

import org.junit.Test;


public class ResultBeanTest {

    @Test
    public void testGetFullName() throws Exception {
	ResultBean bean = new ResultBean();
	assertEquals("", bean.getFullName());
	bean.setCheckName("check");
	bean.setSuiteName("Suite");
	assertEquals("check(Suite)", bean.getFullName().toString());
    }
    
    @Test
    public void testSetFullName() throws Exception {
	ResultBean bean = new ResultBean();
	bean.setFullName("check(Suite)");
	assertEquals("check", bean.getCheckName());
	assertEquals("Suite", bean.getSuiteName());
    }
    
    @Test
    public void testInitPassed() throws Exception {
	ResultBean bean = new ResultBean();
	assertFalse(bean.passed());
	assertNull(bean.getMessage());
	bean.init();
	assertTrue(bean.passed());
	assertEquals("", bean.getMessage());
    }
    
    @Test
    public void testPassed() throws Exception {
	ResultBean bean = new ResultBean();
	bean.setPassed(true);
	assertTrue(bean.getPassed());
	assertTrue(bean.passed());
	bean.setPassed(false);
	assertFalse(bean.getPassed());
	assertFalse(bean.passed());
    }
    
}
