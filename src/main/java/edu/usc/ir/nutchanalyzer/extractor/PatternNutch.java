package edu.usc.ir.nutchanalyzer.extractor;

import java.util.regex.Pattern;

/**
 * This Enum stores all the patterns to be extracted from Nutch's hadoop.log. 
 */
public enum PatternNutch {
	/**
	 * I/P - Any String matches a http/s URL <br/>
	 * Example match -> "http://www.hostname.com/"
	 */
	URL("(http|https)://[a-zA-Z0-9./?=_-]*"),
	/**
	 * Input any URL matches a hostname. <br/>
	 * I/P - http://www.hostname.com/contact.html <br/>
	 * Match - http://www.hostname.com/
	 */
	URL_HOSTNAME("^(?:\\w+://)?[^:?#/\\s]*?([^.\\s]+\\.(?:[a-z]{2,}|co\\.uk|org\\.uk|ac\\.uk|org\\.au|com\\.au|___etc___))(?:[:?#/]|$)");

	
	private Pattern pattern;

	private PatternNutch(String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		this.pattern = pattern;
	}

	public Pattern getPattern() {
		return pattern;
	}
}
