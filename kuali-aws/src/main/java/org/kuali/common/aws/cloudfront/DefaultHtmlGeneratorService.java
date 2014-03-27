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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.kuali.common.util.Counter;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.html.HtmlUtils;
import org.kuali.common.util.html.Tag;

import com.amazonaws.services.s3.model.ObjectListing;

/**
 * Generate directory listings in html format that is Amazon CloudFront friendly
 */
public class DefaultHtmlGeneratorService implements HtmlGeneratorService {

	/**
	 * Decorators for the columns in the table
	 */
	protected List<ColumnDecorator> getColumnDecorators() {
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
	protected String getDirectory(String prefix, String delimiter) {
		if (prefix == null) {
			return delimiter;
		}
		if (prefix.endsWith(delimiter)) {
			return delimiter + prefix.substring(0, prefix.length() - delimiter.length());
		} else {
			return delimiter + prefix;
		}
	}

	protected String getHtmlComment() {
		return "<!-- Generated on " + FormatUtils.getDate(System.currentTimeMillis()) + " -->\n";
	}

	protected String getDocType() {
		return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n";
	}

	protected String getMeta(HtmlGeneratorContext context) {
		return "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + context.getEncoding() + "\"/>\n";
	}

	protected String getGoogleAnalyticsJavascript(HtmlGeneratorContext context) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script type=\"text/javascript\">var _gaq = _gaq || []; _gaq.push(['_setAccount', '");
		sb.append(context.getGoogleAnalyticsAccount());
		sb.append("']); _gaq.push(['_setDomainName', '");
		sb.append(context.getGoogleAnalyticsDomainName());
		sb.append("']); _gaq.push(['_trackPageview']); (function() { var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true; ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js'; var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s); })();</script>\n");
		return sb.toString();
	}

	/**
	 * Generate the full html page
	 */
	@Override
	public String getDirectoryListing(DirectoryListingContext context) {

		HtmlGeneratorContext hgc = context.getGeneratorContext();
		ObjectListing listing = context.getIndexContext().getListing();

		SimpleDateFormat formatter = CloudFrontUtils.getSimpleDateFormat(hgc.getDateDisplayFormat(), hgc.getDateDisplayTimeZone());
		String now = formatter.format(new Date());

		Counter indent = new Counter();
		String directory = getDirectory(listing.getPrefix(), context.getBucketContext().getDelimiter());

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
		sb.append(HtmlUtils.openTag(html, indent));
		sb.append(HtmlUtils.getIndentedContent(getHtmlComment(), indent));
		sb.append(HtmlUtils.getTag(title, "Directory listing for " + directory, indent));
		sb.append(HtmlUtils.openTag(head, indent));
		sb.append(HtmlUtils.getIndentedContent("<link href=\"" + hgc.getCss() + "\" rel=\"stylesheet\" type=\"text/css\"/>\n", indent));
		sb.append(HtmlUtils.getIndentedContent(getMeta(hgc), indent));
		sb.append(HtmlUtils.getIndentedContent(getGoogleAnalyticsJavascript(hgc), indent));
		sb.append(HtmlUtils.closeTag(head, indent));
		sb.append(HtmlUtils.openTag(body, indent));
		sb.append(HtmlUtils.openTag(div1, indent));
		sb.append(HtmlUtils.getTag(span1, "Directory listing for " + directory, indent));
		sb.append(HtmlUtils.closeTag(div1, indent));
		sb.append(HtmlUtils.getIndentedContent("<hr>\n", indent));
		sb.append(HtmlUtils.openTag(div2, indent));
		sb.append(getHtmlTable(context.getIndexContext().getIndexData(), getColumnDecorators(), indent));
		sb.append(HtmlUtils.closeTag(div2, indent));
		sb.append(HtmlUtils.getIndentedContent("<hr>\n", indent));
		sb.append(HtmlUtils.openTag(div3, indent));
		sb.append(HtmlUtils.getTag(span2, context.getAbout() + " " + now, indent));
		sb.append(HtmlUtils.closeTag(div3, indent));
		sb.append(HtmlUtils.closeTag(body, indent));
		sb.append(HtmlUtils.closeTag(html, indent));
		return sb.toString();
	}

	/**
	 * Generate html representing the contents of one table cell
	 */
	protected String getTableCell(String content, ColumnDecorator decorator, Counter indent) {
		Tag td = new Tag("td", decorator.getTableDataClass());
		return HtmlUtils.getTag(td, content, indent);
	}

	/**
	 * Return true if the Collection is null or contains no entries, false otherwise
	 */
	protected boolean isEmpty(Collection<?> c) {
		return c == null || c.size() == 0;
	}

	/**
	 * Alternate the styling of each row
	 */
	protected Tag getTableRowTag(int row) {
		if ((row % 2) == 0) {
			return new Tag("tr", "table-tr-odd");
		} else {
			return new Tag("tr");
		}
	}

	/**
	 * Generate an html table row for the String[]
	 */
	protected String getTableRow(int row, String[] data, List<ColumnDecorator> columnDecorators, Counter indent) {
		StringBuffer sb = new StringBuffer();
		Tag tr = getTableRowTag(row);
		sb.append(HtmlUtils.openTag(tr, indent));
		for (int i = 0; i < data.length; i++) {
			sb.append(getTableCell(data[i], columnDecorators.get(i), indent));
		}
		sb.append(HtmlUtils.closeTag(tr, indent));
		return sb.toString();
	}

	/**
	 * Generate a table row for each String[] in the list
	 */
	protected String getTableRows(List<String[]> data, List<ColumnDecorator> columnDecorators, Counter indent) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.size(); i++) {
			sb.append(getTableRow(i, data.get(i), columnDecorators, indent));
		}
		return sb.toString();
	}

	/**
	 * Generate the html for the th tags from a list of ColumnDecorator objects
	 */
	protected String getTableHeaders(List<ColumnDecorator> columnDecorators, Counter indent) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < columnDecorators.size(); i++) {
			ColumnDecorator decorator = columnDecorators.get(i);
			Tag th = new Tag("th", decorator.getTableDataClass());
			sb.append(HtmlUtils.openTag(th, indent));
			sb.append(HtmlUtils.getTag(new Tag("span", decorator.getSpanClass()), decorator.getColumnTitle(), indent));
			sb.append(HtmlUtils.closeTag(th, indent));
		}
		return sb.toString();
	}

	/**
	 * Generate the table representing a directory listing
	 */
	protected String getHtmlTable(List<String[]> data, List<ColumnDecorator> columnDecorators, Counter indent) {
		if (isEmpty(data)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Tag table = new Tag("table", "mainTable");
		Tag thead = new Tag("thead");
		Tag tr = new Tag("tr");
		Tag tbody = new Tag("tbody");
		sb.append(HtmlUtils.openTag(table, indent));
		sb.append(HtmlUtils.openTag(thead, indent));
		sb.append(HtmlUtils.openTag(tr, indent));
		sb.append(getTableHeaders(columnDecorators, indent));
		sb.append(HtmlUtils.closeTag(tr, indent));
		sb.append(HtmlUtils.closeTag(thead, indent));
		sb.append(HtmlUtils.openTag(tbody, indent));
		sb.append(getTableRows(data, columnDecorators, indent));
		sb.append(HtmlUtils.closeTag(tbody, indent));
		sb.append(HtmlUtils.closeTag(table, indent));
		return sb.toString();
	}

}
