package eu.ebdit.eau;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test for {@link Result}.
 * 
 * @author Vladimir Orany
 * 
 */
public final class ResultTest {

    private static final String SUITE_AS_DECLARED
    	= "Suite name must be same as declared";
    private static final String CHECK_AS_DECLARED
    	= "Check name must be same as declared";
    private static final String DEFAULT_SUCCESS
    	= "Default success must be true!";
    private static final String CHECK_NAME = "check";
    private static final String SUITE_NAME = "Suite";
    private static final String MESSAGE = "Message";
    private static final String MESSAGE_AS_DECLARED // NOPMD
    	= "Message must be same as declared";
    private static final String SUCCESS_AS_DECLARED // NOPMD 
    	= "Success must be same as declared!";
    private static final String DEFAULT_MESSAGE 
    	= "Default message must be null!";

    /**
     * Test for {@link Result#ofFullName(String)}.
     */
    @Test
    public void testOfFullName1() {
	final Result bean = Result.ofFullName("check(Suite)");
	assertCheckSuite(bean);
	assertTrue(DEFAULT_SUCCESS, bean.isSuccess());
	assertNull(DEFAULT_MESSAGE, bean.getMessage());
    }

    /**
     * Test for {@link Result#ofFullName(String, boolean)}.
     */
    @Test
    public void testOfFullName2() {
	final Result bean = Result.ofFullName("check(Suite)", false);
	assertCheckSuite(bean);
	assertFalse(SUCCESS_AS_DECLARED, bean.isSuccess());
	assertNull(DEFAULT_MESSAGE, bean.getMessage());
    }

    /**
     * Test for {@link Result#ofFullName(String, boolean, String)}.
     */
    @Test
    public void testOfFullName3() {
	final Result bean = Result.ofFullName("check(Suite)", false, MESSAGE);
	assertCheckSuite(bean);
	assertFalse(SUCCESS_AS_DECLARED, bean.isSuccess());
	assertEquals(MESSAGE_AS_DECLARED, MESSAGE, bean.getMessage());
    }

    /**
     * Test for {@link Result#ofNames(String, String)}.
     */
    @Test
    public void testOf1() {
	final Result bean = Result.ofNames(SUITE_NAME, CHECK_NAME);
	assertCheckSuite(bean);
	assertTrue(DEFAULT_SUCCESS, bean.isSuccess());
	assertNull(DEFAULT_MESSAGE, bean.getMessage());
    }

    /**
     * Test for {@link Result#ofNames(String, String, boolean)}.
     */
    @Test
    public void testOf2() {
	final Result bean = Result.ofNames(SUITE_NAME, CHECK_NAME, false);
	assertCheckSuite(bean);
	assertFalse(SUCCESS_AS_DECLARED, bean.isSuccess());
	assertNull(DEFAULT_MESSAGE, bean.getMessage());
    }

    /**
     * Test for {@link Result#ofNames(String, String, boolean, String)}.
     */
    @Test
    public void testOf3() {
	final Result bean = Result.ofNames(SUITE_NAME, CHECK_NAME, false, MESSAGE);
	assertCheckSuite(bean);
	assertFalse(SUCCESS_AS_DECLARED, bean.isSuccess());
	assertEquals(MESSAGE_AS_DECLARED, MESSAGE, bean.getMessage());
    }

    private void assertCheckSuite(final Result bean) {
	assertEquals(CHECK_AS_DECLARED, CHECK_NAME, bean.getCheckName());
	assertEquals(SUITE_AS_DECLARED, SUITE_NAME, bean.getSuiteName());
    }

}
