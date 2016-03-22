package edu.usc.ir.nutchanalyzer.extractor;

import java.util.regex.Pattern;

public enum PatternNutch {
	URL("(http|https)://[a-zA-Z0-9./?=_-]*");

	private Pattern pattern;

	private PatternNutch(String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		this.pattern = pattern;
	}

	public Pattern getPattern() {
		return pattern;
	}
}
