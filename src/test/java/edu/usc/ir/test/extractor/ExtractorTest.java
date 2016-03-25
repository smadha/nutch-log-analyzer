package edu.usc.ir.test.extractor;

import org.junit.Assert;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import edu.usc.ir.nutchanalyzer.extractor.PatternExtractor;
import edu.usc.ir.nutchanalyzer.extractor.PatternNutch;

public class ExtractorTest {

	private List<String> logFile;

	public ExtractorTest() {
		try {
			Path path = Paths.get(this.getClass().getResource("/hadoop.log").toURI());
			logFile = Files.readAllLines(path, Charset.defaultCharset());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testExtractAllPatterns() {
		Set<String> matchSet = new HashSet<>();
		int count = 0;

		for (String line : logFile) {
			String str = new PatternExtractor().extractPattern(PatternNutch.URL, line);
			if (str != null && !str.isEmpty()) {
				count++;
				matchSet.add(str);
			}
		}
		System.out.println(matchSet);
		Assert.assertTrue(matchSet.contains("http://www.jasonsguns.com/"));
		Assert.assertTrue(matchSet.contains("http://www.jasonsguns.com/PrivacyStatement.aspx"));
		Assert.assertTrue(matchSet.contains("http://www.jasonsguns.com/Search.aspx?code=Hunting"));
		Assert.assertEquals(45, count);
		Assert.assertEquals(27, matchSet.size());

	}

	@Test
	public void testExtractPatternPipe() {
		List<PatternNutch> pipe = new ArrayList<>();
		pipe.add(PatternNutch.URL);
		pipe.add(PatternNutch.URL_HOSTNAME);

		Set<String> matchSet = new HashSet<>();
		int count = 0;

		for (String line : logFile) {
			String str = new PatternExtractor().extractPatternPipe(pipe, line);
			if (str != null && !str.isEmpty()) {
				count++;
				matchSet.add(str);
			}
		}
		System.out.println(matchSet);
		
		Assert.assertTrue(matchSet.contains("http://www.jasonsguns.com/"));
		Assert.assertEquals(45, count);
		Assert.assertEquals(1, matchSet.size());
		
		
	}

}
