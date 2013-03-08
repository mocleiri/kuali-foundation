/**
 * Copyright 2010-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util;

import java.util.Date;

public class FormatUtils {

	protected static SimpleFormatter SF = new SimpleFormatter();

	/**
	 * 
	 */
	public static String getThroughputInSeconds(long millis, long count, String label) {
		return SF.getThroughputInSeconds(millis, count, label);
	}

	/**
	 * Parse a date from the string. The string must be in the same format returned by the getDate() methods
	 */
	public static Date parseDate(String date) {
		return SF.parseDate(date);
	}

	/**
	 * Return a formatted date
	 */
	public static String getDate(long millis) {
		return SF.getDate(millis);
	}

	/**
	 * Return a formatted date
	 */
	public static String getDate(Date date) {
		return SF.getDate(date);
	}

	/**
	 * Given a number of bytes and the number of milliseconds it took to transfer that number of bytes, return bytes/s, KB/s, MB/s, GB/s, TB/s, PB/s, or EB/s as appropriate
	 */
	public static String getRate(long millis, long bytes) {
		return SF.getRate(millis, bytes);
	}

	/**
	 * Return a formatted <code>count</code>
	 */
	public static String getCount(long count) {
		return SF.getCount(count);
	}

	/**
	 * Given milliseconds, return milliseconds, seconds, minutes, hours, days, years, decades, or centuries as appropriate. Note that years, decades, and centuries are
	 * approximations since the logic always assumes there are exactly 365 days per year.
	 */
	public static String getTime(long millis) {
		return SF.getTime(millis);
	}

	/**
	 * Given a number of bytes return bytes, kilobytes, megabytes, gigabytes, terabytes, petabytes, or exabytes as appropriate.
	 */
	public static String getSize(long bytes) {
		return SF.getSize(bytes);
	}

	/**
	 * Given a number of bytes return a string formatted into the unit of measure indicated
	 */
	public static String getSize(long bytes, Size unitOfMeasure) {
		return SF.getSize(bytes, unitOfMeasure);
	}

	public static String getFormattedSize(long bytes, Size size) {
		return SF.getFormattedSize(bytes, size);
	}

	public static Size getSizeEnum(double bytes) {
		return SF.getSizeEnum(bytes);
	}

}
