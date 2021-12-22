package nl.vkb.customer;

import java.util.regex.Pattern;

public final class Utils {

	private Utils(){}

	// Regex pattern matcher
	public static boolean patternMatches(String emailAddress, String regexPattern) {
		return Pattern.compile(regexPattern)
				.matcher(emailAddress)
				.matches();
	}
}
