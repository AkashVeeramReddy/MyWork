package utils;

import java.util.regex.Pattern;

public class PatternUtils {

	public static final String WHITESPACE_REGEX = "\\\\s";
	public static final Pattern WHITESPACE_PATTERN = Pattern.compile(WHITESPACE_REGEX);
	
	public static final String NEWLINE_REGEX = MyConstants.NEW_LINE;
	public static final Pattern NEWLINE_PATTERN = Pattern.compile(NEWLINE_REGEX);
}
