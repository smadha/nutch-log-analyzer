package edu.usc.ir.test.extractor;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

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
			String str = new PatternExtractor().extractAllPatterns(PatternNutch.URL, line);
			if (str != null && !str.isEmpty()){
				count ++;
				matchSet.add(str);
			}
		}
		System.out.println(matchSet);
		assertEquals(45, count);
		assertEquals(27, matchSet.size());
		
	}

}
