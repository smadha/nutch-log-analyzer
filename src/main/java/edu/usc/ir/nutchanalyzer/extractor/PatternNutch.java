/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	URL("(http|https)://[a-zA-Z0-9./?=_-]*"), /**
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
