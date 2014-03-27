/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.common.aws.cloudfront;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.Counter;
import org.kuali.common.util.Str;
import org.kuali.common.util.html.HtmlUtils;

import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * Convert information from an S3 bucket into pojo's
 */
public class DefaultListingConverterService implements ListingConverterService {

	/**
	 * Convert "foo/bar/css/" into "foo/bar/css"<br>
	 * Convert "foo/bar/css" into "foo/bar"<br>
	 */
	protected String getTrimmedPrefix(String prefix, String delimiter) {
		return Str.removeSuffix(prefix, delimiter);
	}

	/**
	 * Convert each DisplayRow object in the list to a String[] and add the String[] to the list of data
	 */
	protected void addDisplayRows(List<DisplayRow> displayRows, List<String[]> data) {
		for (DisplayRow displayRow : displayRows) {
			addDisplayRow(displayRow, data);
		}
	}

	/**
	 * Convert a DisplayRow object to a String[]
	 */
	protected void addDisplayRow(DisplayRow displayRow, List<String[]> data) {
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
	protected String getShow(String key, String prefix) {
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
	protected DisplayRow getDisplayRow(String commonPrefix, IndexDataContext context, Counter indent) {
		/**
		 * // Create some UI friendly strings String image = HtmlUtils.getImage(context.getConverterContext().getDirImage(), indent); String show = getShow(commonPrefix,
		 * context.getListing().getPrefix()); String dest = context.getBucketContext().getDelimiter() + commonPrefix; String ahref = HtmlUtils.getHref(dest, show, indent); String
		 * date = "-"; String size = "-";
		 * 
		 * // Store them in an object DisplayRow displayRow = new DisplayRow(); displayRow.setImage(image); displayRow.setAhref(ahref); displayRow.setLastModified(date);
		 * displayRow.setSize(size); displayRow.setShow(show); return displayRow;
		 **/
		return null;
	}

	protected List<DisplayRow> getDirectoryDisplayRows(IndexDataContext context, Counter indent) {
		/**
		 * List<DisplayRow> displayRows = new ArrayList<DisplayRow>(); for (String commonPrefix : context.getListing().getCommonPrefixes()) { DisplayRow displayRow =
		 * getDisplayRow(commonPrefix, context, indent); if (displayRow == null) { continue; } displayRows.add(displayRow); } return displayRows;
		 **/
		return null;
	}

	@Override
	public List<String[]> getIndexData(IndexDataContext context) {
		/**
		 * ListingConverterContext lcc = context.getConverterContext(); ObjectListing listing = context.getListing();
		 * 
		 * SimpleDateFormat formatter = CloudFrontUtils.getSimpleDateFormat(lcc.getDateDisplayFormat(), lcc.getDateDisplayTimeZone()); Counter indent = new Counter(); DisplayRow
		 * upOneDirectory = getUpOneDirectoryDisplayRow(context, listing.getPrefix(), indent); List<DisplayRow> objectDisplayRows = getObjectDisplayRows(context, indent,
		 * formatter); List<DisplayRow> directoryDisplayRows = getDirectoryDisplayRows(context, indent); Comparator<DisplayRow> comparator = new DisplayRowComparator();
		 * Collections.sort(directoryDisplayRows, comparator); List<String[]> indexData = new ArrayList<String[]>(); addDisplayRow(upOneDirectory, indexData);
		 * addDisplayRows(directoryDisplayRows, indexData); addDisplayRows(objectDisplayRows, indexData); return indexData;
		 **/
		return null;
	}

	protected boolean isDirectory(S3ObjectSummary summary, List<String> commonPrefixes, String prefix, String delimiter) {
		String key = summary.getKey();
		if (StringUtils.equals(key, prefix)) {
			return true;
		}
		for (String commonPrefix : commonPrefixes) {
			if (StringUtils.equals(key, commonPrefix)) {
				return true;
			}
			String trimmedPrefix = Str.removeSuffix(commonPrefix, delimiter);
			if (StringUtils.equals(key, trimmedPrefix)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Convert an S3ObjectSummary into a DisplayRow object for the UI
	 */
	protected DisplayRow getDisplayRow(IndexDataContext context, S3ObjectSummary summary, Counter indent, SimpleDateFormat formatter) {
		/**

		String delimiter = context.getBucketContext().getDelimiter();

		String key = summary.getKey();

		// Create some UI friendly strings
		String image = HtmlUtils.getImage(context.getConverterContext().getFileImage(), indent);
		String show = getShow(key, context.getListing().getPrefix());
		String dest = delimiter + key;
		String ahref = HtmlUtils.getHref(dest, show, indent);
		String date = formatter.format(summary.getLastModified());
		String size = FormatUtils.getSize(summary.getSize());

		// Store them in an object
		DisplayRow displayRow = new DisplayRow();
		displayRow.setShow(show);
		displayRow.setImage(image);
		displayRow.setAhref(ahref);
		displayRow.setLastModified(date);
		displayRow.setSize(size);
		return displayRow;
		**/
		return null;
	}

	protected List<DisplayRow> getObjectDisplayRows(IndexDataContext context, Counter indent, SimpleDateFormat formatter) {
		/**
		String delimiter = context.getBucketContext().getDelimiter();
		ObjectListing listing = context.getListing();
		List<DisplayRow> displayRows = new ArrayList<DisplayRow>();
		for (S3ObjectSummary summary : listing.getObjectSummaries()) {
			if (isDirectory(summary, listing.getCommonPrefixes(), listing.getPrefix(), delimiter)) {
				continue;
			}
			DisplayRow displayRow = getDisplayRow(context, summary, indent, formatter);
			if (displayRow == null) {
				continue;
			}
			displayRows.add(displayRow);
		}
		return displayRows;
		**/
		return null;
	}

	/**
	 * Convert a commonPrefix into a DisplayRow object for the UI
	 */
	protected DisplayRow getUpOneDirectoryDisplayRow(IndexDataContext context, String prefix, Counter indent) {

		String delimiter = context.getBucketContext().getDelimiter();
		String browseKey = context.getConverterContext().getBrowseKey();

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
