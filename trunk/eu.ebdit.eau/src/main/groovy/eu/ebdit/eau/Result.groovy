package eu.ebdit.eau;

import groovy.lang.Immutable;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Result of the check. Check can be test run or style check or something other.
 * 
 * @author Vladimír Oraný
 * 
 */
@Immutable public class Result{
	
	private static final Pattern PATTERN = Pattern.compile("(.*?)\\((.*?)\\)");
	
	/**
	 * The name of the suite which is resulted check part of.
	 */
	String suiteName
	
	/**
	 * The message from checking or <code>null</code> if there is no
	 * message from the check (usually on successful check).
	 */
	String message
	
	/**
	 * Whether the check passed. That means whether the test was
	 * successful and did ended by error of failure or whether the check didn't
	 * find any problems.
	 */
	boolean success
	
	/**
	 * The name of the resulted check 
	 */
	String checkName
	
	/**
	 * Creates new result for given parameters
	 * @param suiteName the name of the suite
	 * @param checkName the name of the check
	 * @param success whether was check successful, default is true
	 * @param message message from the check, default is <code>null</code>
	 * @return returns new immutable result of given parameters
	 */
	static Result ofNames(String suiteName, String checkName, boolean success = true, 
	String message = null){
		
		return new Result(suiteName: suiteName, checkName: checkName, 
		success: success, message: message)
	}
	
	/**
	 * Creates new result for given parameters
	 * @param fullName full name of the check in format "checkName(suiteName)"
	 * @param success whether was check successful, default is true
	 * @param message message from the check, default is <code>null</code>
	 * @return returns new immutable result of given parameters
	 */
	static Result ofFullName(String fullName, boolean success = true, 
	String message = null){
		def names = parseFullName(fullName)
		return new Result(suiteName: names.suiteName, checkName: names.checkName, 
		success: success, message: message)
	}
	
	private static Map<String, String> parseFullName(fullName){
		final Matcher matcher = PATTERN.matcher(fullName);
		if (matcher.matches()) {
			return [suiteName: matcher.group(2), checkName: matcher.group(1)]
		} else {
			throw new AssertionError("Matcher must match!");
		}
	}
	
}
