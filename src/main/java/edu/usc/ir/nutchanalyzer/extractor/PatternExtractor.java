package edu.usc.ir.nutchanalyzer.extractor;

import java.util.List;
import java.util.regex.Matcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PatternExtractor {
	private static final Logger logger = LogManager.getLogger(PatternExtractor.class);

	public String extractAllPatterns(PatternNutch pattern, String logLines) {
		Matcher matcher = pattern.getPattern().matcher(logLines);
		String match = "";
		if (matcher.find()) {
			match = matcher.group(0);
			logger.debug("Found value: " + match);
			logger.debug(logLines);
		}
		return match;
	}
}
