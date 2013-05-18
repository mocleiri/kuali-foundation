/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.common.aws.s3.cloudfront;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.Counter;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.html.HtmlUtils;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * Convert information from an S3 bucket into pojo's
 */
public class ObjectListingConverter {

	/**
	 * Convert "foo/bar/css/" into "foo/bar/css"<br>
	 * Convert "foo/bar/css" into "foo/bar"<br>
	 */
	protected String getTrimmedPrefix(final String prefix, final String delimiter) {
		int pos = prefix.lastIndexOf(delimiter);
		if (pos == -1) {
			return prefix;
		}
		return prefix.substring(0, pos);
	}

	/**
	 * Convert each DisplayRow object in the list to a String[] and add the String[] to the list of data
	 */
	protected void addDisplayRows(final List<DisplayRow> displayRows, final List<String[]> data) {
		for (DisplayRow displayRow : displayRows) {
			addDisplayRow(displayRow, data);
		}
	}

	/**
	 * Convert a DisplayRow object to a String[]
	 */
	protected void addDisplayRow(final DisplayRow displayRow, final List<String[]> data) {
		if (displayRow == null) {
			return;
		}
		String[] row = new String[4];
		row[0] = displayRow.getImage();
		row[1] = displayRow.getAhref();
		row[2] = displayRow.getLastModified();
		row[3] = displayRow.getSize();
		data.add(row);
	}

	/**
	 * Trim the prefix off of the text we display for this object.<br>
	 * Display "style.css" instead of "css/style.css"
	 */
	protected String getShow(final String key, final String prefix) {
		if (prefix == null) {
			return key;
		}
		int index = prefix.length();
		String s = key.substring(index);
		return s;
	}

	/**
	 * Convert a commonPrefix into a DisplayRow object for the UI
	 */
	protected DisplayRow getDisplayRow(String commonPrefix, String prefix, String delimiter, String directoryImage, Counter indent) {

		// Create some UI friendly strings
		String image = HtmlUtils.getImage(directoryImage, indent);
		String show = getShow(commonPrefix, prefix);
		String dest = delimiter + commonPrefix;
		String ahref = HtmlUtils.getHref(dest, show, indent);
		String date = "-";
		String size = "-";

		// Store them in an object
		DisplayRow displayRow = new DisplayRow();
		displayRow.setImage(image);
		displayRow.setAhref(ahref);
		displayRow.setLastModified(date);
		displayRow.setSize(size);
		displayRow.setShow(show);
		return displayRow;
	}

	protected List<DisplayRow> getDirectoryDisplayRows(ObjectListing objectListing, String prefix, String delimiter, String directoryImage, Counter indent) {
		List<DisplayRow> displayRows = new ArrayList<DisplayRow>();
		for (String commonPrefix : objectListing.getCommonPrefixes()) {
			DisplayRow displayRow = getDisplayRow(commonPrefix, prefix, delimiter, directoryImage, indent);
			if (displayRow == null) {
				continue;
			}
			displayRows.add(displayRow);
		}
		return displayRows;
	}

	/**
	 * Convert the ObjectListing into List<String[]>. Each list entry represents one row in the html table we will be generating
	 */
	public List<String[]> convert(ObjectListing listing, String prefix, String delimiter, String dirImage, String fileImage, String browseKey) {
		Counter indent = new Counter();
		DisplayRow upOneDirectory = getUpOneDirectoryDisplayRow(prefix, delimiter, browseKey, indent);
		List<DisplayRow> objectDisplayRows = getObjectDisplayRows(listing, prefix, delimiter, fileImage, indent);
		List<DisplayRow> directoryDisplayRows = getDirectoryDisplayRows(listing, prefix, delimiter, dirImage, indent);
		Comparator<DisplayRow> comparator = new DisplayRowComparator();
		Collections.sort(directoryDisplayRows, comparator);
		List<String[]> data = new ArrayList<String[]>();
		addDisplayRow(upOneDirectory, data);
		addDisplayRows(directoryDisplayRows, data);
		addDisplayRows(objectDisplayRows, data);
		return data;
	}

	protected boolean isDirectory(S3ObjectSummary summary, List<String> commonPrefixes, String prefix, String delimiter) {
		String key = summary.getKey();
		if (key.equals(prefix)) {
			return true;
		}
		for (String commonPrefix : commonPrefixes) {
			if (key.equals(commonPrefix)) {
				return true;
			}
			String trimmedPrefix = getTrimmedPrefix(commonPrefix, delimiter);
			if (key.equals(trimmedPrefix)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Convert an S3ObjectSummary into a DisplayRow object for the UI
	 */
	protected DisplayRow getDisplayRow(S3ObjectSummary summary, String prefix, String delimiter, String fileImage, Counter indent) {
		String key = summary.getKey();

		// Create some UI friendly strings
		String image = HtmlUtils.getImage(fileImage, indent);
		String show = getShow(key, prefix);
		String dest = delimiter + key;
		String ahref = HtmlUtils.getHref(dest, show, indent);
		String date = FormatUtils.getDate(summary.getLastModified());
		String size = FormatUtils.getSize(summary.getSize());

		// Store them in an object
		DisplayRow displayRow = new DisplayRow();
		displayRow.setShow(show);
		displayRow.setImage(image);
		displayRow.setAhref(ahref);
		displayRow.setLastModified(date);
		displayRow.setSize(size);
		return displayRow;
	}

	protected List<DisplayRow> getObjectDisplayRows(ObjectListing objectListing, String prefix, String delimiter, String fileImage, Counter indent) {
		List<DisplayRow> displayRows = new ArrayList<DisplayRow>();
		for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
			if (isDirectory(summary, objectListing.getCommonPrefixes(), prefix, delimiter)) {
				continue;
			}
			DisplayRow displayRow = getDisplayRow(summary, prefix, delimiter, fileImage, indent);
			if (displayRow == null) {
				continue;
			}
			displayRows.add(displayRow);
		}
		return displayRows;
	}

	/**
	 * Convert a commonPrefix into a DisplayRow object for the UI
	 */
	protected DisplayRow getUpOneDirectoryDisplayRow(String prefix, String delimiter, String browseKey, Counter indent) {
		if (StringUtils.isEmpty(prefix)) {
			return null;
		}

		// Create some UI friendly strings
		String image = "";
		String show = ".." + delimiter;
		String dest = getUpOneDirectoryDest(prefix, delimiter, browseKey);
		String ahref = HtmlUtils.getHref(dest, show, indent);
		String date = "";
		String size = "";

		// Store them in an object
		DisplayRow displayRow = new DisplayRow();
		displayRow.setImage(image);
		displayRow.setAhref(ahref);
		displayRow.setLastModified(date);
		displayRow.setSize(size);
		return displayRow;
	}

	/**
	 * If prefix is "foo/" and delimiter is "/" return "/"<br>
	 * If prefix is "foo/bar/" and delimiter is "/" return "foo/"
	 */
	protected String getUpOneDirectoryDest(String prefix, String delimiter, String browseKey) {
		if (prefix.endsWith(delimiter)) {
			prefix = prefix.substring(0, prefix.length() - 1);
		}
		int pos = prefix.lastIndexOf(delimiter);
		if (pos == -1) {
			return delimiter + browseKey;
		} else {
			return delimiter + prefix.substring(0, pos + 1);
		}
	}

}
