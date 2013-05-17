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
import java.util.Collection;
import java.util.List;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.html.HtmlUtils;
import org.kuali.common.util.html.Tag;

/**
 * Generate directory listings in html format that is Amazon CloudFront friendly
 */
public class CloudFrontHtml {

	/**
	 * Decorators for the columns in the table
	 */
	protected static List<ColumnDecorator> getColumnDecorators() {
		List<ColumnDecorator> columnDecorators = new ArrayList<ColumnDecorator>();
		columnDecorators.add(new ColumnDecorator("image-column", "sort-header", ""));
		columnDecorators.add(new ColumnDecorator("name-column", "sort-header", "Name"));
		columnDecorators.add(new ColumnDecorator("last-modified-column", "sort-header", "Last Modified"));
		columnDecorators.add(new ColumnDecorator("size-column", "sort-header", "Size"));
		return columnDecorators;
	}

	/**
	 * If prefix is null, return the delimiter.<br>
	 * If delimiter is "/" and prefix is "foo/bar" return "/foo/bar"<br>
	 * If delimiter is "/" and prefix is "foo/bar/" return "/foo/bar"
	 */
	protected static String getDirectory(String prefix, String delimiter) {
		if (prefix == null) {
			return delimiter;
		}
		if (prefix.endsWith(delimiter)) {
			return delimiter + prefix.substring(0, prefix.length() - delimiter.length());
		} else {
			return delimiter + prefix;
		}
	}

	protected static String getHtmlComment() {
		return "<!-- Generated on " + FormatUtils.getDate(System.currentTimeMillis()) + " -->\n";
	}

	protected static String getDocType() {
		return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n";
	}

	protected static String getMeta() {
		return "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n";
	}

	protected static String getGoogleAnalyticsJavascript() {
		return "<script type=\"text/javascript\">var _gaq = _gaq || []; _gaq.push(['_setAccount', 'UA-16781661-1']); _gaq.push(['_setDomainName', '.kuali.org']); _gaq.push(['_trackPageview']); (function() { var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true; ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js'; var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s); })();</script>\n";
	}

	/**
	 * Generate the full html page
	 */
	public static String getDirectoryListingHtml(List<String[]> data, String prefix, String delimiter, String css, String about) {
		String directory = getDirectory(prefix, delimiter);

		Tag html = new Tag("html");
		Tag title = new Tag("title");
		Tag head = new Tag("head");
		Tag body = new Tag("body");
		Tag div1 = new Tag("div", "title");
		Tag span1 = new Tag("span", null, "title");
		Tag div2 = new Tag("div", "data");
		Tag div3 = new Tag("div", "footer", "footer-left");
		Tag span2 = new Tag("span", null, "footer-text");

		StringBuffer sb = new StringBuffer();
		sb.append(getDocType());
		sb.append(HtmlUtils.openTag(html));
		sb.append(HtmlUtils.getIndentedContent(getHtmlComment()));
		sb.append(HtmlUtils.getTag(title, "Directory listing for " + directory));
		sb.append(HtmlUtils.openTag(head));
		sb.append(HtmlUtils.getIndentedContent("<link href=\"" + css + "\" rel=\"stylesheet\" type=\"text/css\"/>\n"));
		sb.append(HtmlUtils.getIndentedContent(getMeta()));
		sb.append(HtmlUtils.getIndentedContent(getGoogleAnalyticsJavascript()));
		sb.append(HtmlUtils.closeTag(head));
		sb.append(HtmlUtils.openTag(body));
		sb.append(HtmlUtils.openTag(div1));
		sb.append(HtmlUtils.getTag(span1, "Directory listing for " + directory));
		sb.append(HtmlUtils.closeTag(div1));
		sb.append(HtmlUtils.getIndentedContent("<hr>\n"));
		sb.append(HtmlUtils.openTag(div2));
		sb.append(getHtmlTable(data, getColumnDecorators()));
		sb.append(HtmlUtils.closeTag(div2));
		sb.append(HtmlUtils.getIndentedContent("<hr>\n"));
		sb.append(HtmlUtils.openTag(div3));
		sb.append(HtmlUtils.getTag(span2, about));
		sb.append(HtmlUtils.closeTag(div3));
		sb.append(HtmlUtils.closeTag(body));
		sb.append(HtmlUtils.closeTag(html));
		return sb.toString();
	}

	/**
	 * Generate html representing the contents of one table cell
	 */
	protected static String getTableCell(String content, ColumnDecorator decorator) {
		Tag td = new Tag("td", decorator.getTableDataClass());
		return HtmlUtils.getTag(td, content);
	}

	/**
	 * Return true if the Collection is null or contains no entries, false otherwise
	 */
	protected static boolean isEmpty(Collection<?> c) {
		return c == null || c.size() == 0;
	}

	/**
	 * Alternate the styling of each row
	 */
	protected static Tag getTableRowTag(int row) {
		if ((row % 2) == 0) {
			return new Tag("tr", "table-tr-odd");
		} else {
			return new Tag("tr");
		}
	}

	/**
	 * Generate an html table row for the String[]
	 */
	protected static String getTableRow(int row, String[] data, List<ColumnDecorator> columnDecorators) {
		StringBuffer sb = new StringBuffer();
		Tag tr = getTableRowTag(row);
		sb.append(HtmlUtils.openTag(tr));
		for (int i = 0; i < data.length; i++) {
			sb.append(getTableCell(data[i], columnDecorators.get(i)));
		}
		sb.append(HtmlUtils.closeTag(tr));
		return sb.toString();
	}

	/**
	 * Generate a table row for each String[] in the list
	 */
	protected static String getTableRows(List<String[]> data, List<ColumnDecorator> columnDecorators) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.size(); i++) {
			sb.append(getTableRow(i, data.get(i), columnDecorators));
		}
		return sb.toString();
	}

	/**
	 * Generate the html for the th tags from a list of ColumnDecorator objects
	 */
	protected static String getTableHeaders(List<ColumnDecorator> columnDecorators) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < columnDecorators.size(); i++) {
			ColumnDecorator decorator = columnDecorators.get(i);
			Tag th = new Tag("th", decorator.getTableDataClass());
			sb.append(HtmlUtils.openTag(th));
			sb.append(HtmlUtils.getTag(new Tag("span", decorator.getSpanClass()), decorator.getColumnTitle()));
			sb.append(HtmlUtils.closeTag(th));
		}
		return sb.toString();
	}

	/**
	 * Generate the table representing a directory listing
	 */
	protected static String getHtmlTable(List<String[]> data, List<ColumnDecorator> columnDecorators) {
		if (isEmpty(data)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Tag table = new Tag("table", "mainTable");
		Tag thead = new Tag("thead");
		Tag tr = new Tag("tr");
		Tag tbody = new Tag("tbody");
		sb.append(HtmlUtils.openTag(table));
		sb.append(HtmlUtils.openTag(thead));
		sb.append(HtmlUtils.openTag(tr));
		sb.append(getTableHeaders(columnDecorators));
		sb.append(HtmlUtils.closeTag(tr));
		sb.append(HtmlUtils.closeTag(thead));
		sb.append(HtmlUtils.openTag(tbody));
		sb.append(getTableRows(data, columnDecorators));
		sb.append(HtmlUtils.closeTag(tbody));
		sb.append(HtmlUtils.closeTag(table));
		return sb.toString();
	}

}
