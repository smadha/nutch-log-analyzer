package edu.usc.ir.test.analyzer;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import edu.usc.ir.nutchanalyzer.NutchURLAnalyzer;

public class AnalyzerTest {

	private String logFilePath;

	public AnalyzerTest() {
		logFilePath = this.getClass().getResource("/hadoop.log").getPath();
		System.out.println(logFilePath);

	}

	@Test
	public void testComputeURLStats() {

		Map<String, Set<String>> result = null;
		try {
			result = new NutchURLAnalyzer().computeURLStats(logFilePath);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

		Assert.assertNotNull(result);
		Assert.assertNotNull(result.get("http://www.jasonsguns.com/"));
		Assert.assertEquals(27, result.get("http://www.jasonsguns.com/").size());

	}

}
