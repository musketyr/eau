package eu.ebdit.eau.beans

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.ebdit.eau.Result

public class ResultBean implements Result{
	
	private static final Pattern PATTERN = Pattern.compile("(.*?)\\((.*?)\\)");

	String suiteName
	String message
	boolean passed
	String checkName
	
	boolean passed(){ return passed
	}
	
	def getFullName(){
	    if (!suiteName && !checkName) {
		return ''
	    }
		return "$checkName($suiteName)"
	}
	
	def setFullName(fullName){
		final Matcher matcher = PATTERN.matcher(fullName);
		if (matcher.matches()) {
			setSuiteName(matcher.group(2));
			setCheckName(matcher.group(1));
		} else {
			throw new AssertionError("Matcher must match!");
		}
	}
}
