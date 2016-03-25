package edu.usc.ir.nutchanalyzer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.usc.ir.nutchanalyzer.extractor.PatternExtractor;
import edu.usc.ir.nutchanalyzer.extractor.PatternNutch;

/**
 * Analytical functions over hadoop.log 
 */
public class NutchURLAnalyzer {
	private static final Logger LOGGER = LogManager.getLogger(NutchURLAnalyzer.class);

	/**
	 * Tries to capture crawl dimension with respect to URLs and host names crawled
	 * @param logFilePath Path of hadoop.log
	 * @return A map of hostName to set of URLs present in log file
	 * @throws IOException If file is not found at logFilePath
	 */
	public Map<String, Set<String>> computeURLStats(String logFilePath) throws IOException {

		List<PatternNutch> pipe = new ArrayList<>();
		pipe.add(PatternNutch.URL);
		pipe.add(PatternNutch.URL_HOSTNAME);

		Map<String, Set<String>> hostToURL = new HashMap<>();

		Path path = Paths.get(logFilePath);
		List<String> logFile = Files.readAllLines(path, Charset.defaultCharset());

		for (String line : logFile) {
			List<String> listMatch = new PatternExtractor().extractAllPatternPipe(pipe, line);
			if (listMatch.isEmpty()) {
				continue;
			}
			String hostName = listMatch.get(1);
			String URL = listMatch.get(0);

			if (hostToURL.get(hostName) == null) {
				hostToURL.put(hostName, new HashSet<String>());
			}

			hostToURL.get(hostName).add(URL);

		}

		LOGGER.debug(hostToURL);

		return hostToURL;
	}
}
