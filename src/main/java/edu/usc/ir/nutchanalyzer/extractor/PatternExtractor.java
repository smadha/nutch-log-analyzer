package edu.usc.ir.nutchanalyzer.extractor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Pattern Extracting Strategies given regex from log file
 */
public class PatternExtractor {
	private static final Logger LOGGER = LogManager.getLogger(PatternExtractor.class);

	/**
	 * Extracts regex pattern as provided in patternNutch from logLine<br/>
	 * 
	 * @param patternNutch
	 * @param logLine
	 * @return pattern if present in logLine, else a empty String
	 */
	public String extractPattern(PatternNutch patternNutch, final String logLine) {
		Matcher matcher = patternNutch.getPattern().matcher(logLine);
		String match = "";
		if (matcher.find()) {
			match = matcher.group(0);
			LOGGER.debug("{} found : {} ", patternNutch, match);
			LOGGER.debug(logLine);
		}
		return match;
	}

	/**
	 * Applies multiple regex one after other to a logLine. <br/>
	 * Second regex is applied to results of first and so on.. <br/>
	 * 
	 * @param patternPipeline
	 * @param logLine
	 * @return final pattern if found , else an empty String if match is empty for any Pattern
	 */
	public String extractPatternPipe(List<PatternNutch> patternPipeline, final String logLine) {
		String pipedInput = logLine;

		for (PatternNutch patternNutch : patternPipeline) {
			Matcher matcher = patternNutch.getPattern().matcher(pipedInput);
			if (matcher.find()) {
				pipedInput = matcher.group(0);

				LOGGER.debug("{} found : {} ", patternNutch, pipedInput);
				LOGGER.debug(logLine);
			} else {
				pipedInput = "";
				break;
			}
		}
		return pipedInput;
	}

	/**
	 * Applies multiple regex one after other to a logLine. <br/>
	 * Second regex is applied to results of first and so on.. <br/>
	 * 
	 * @param patternPipeline
	 * @param logLine
	 * @return All pattern found in sequence of regex provided, else an empty List
	 */
	public List<String> extractAllPatternPipe(List<PatternNutch> patternPipeline, final String logLine) {
		String pipedInput = logLine;
		List<String> resultPipe = new ArrayList<>();

		for (PatternNutch patternNutch : patternPipeline) {
			Matcher matcher = patternNutch.getPattern().matcher(pipedInput);
			if (matcher.find()) {
				pipedInput = matcher.group(0);
				resultPipe.add(pipedInput);

				LOGGER.debug("{} found : {} ", patternNutch, pipedInput);
				LOGGER.debug(logLine);
			} else {
				resultPipe.clear();
				break;
			}
		}
		return resultPipe;
	}
}
