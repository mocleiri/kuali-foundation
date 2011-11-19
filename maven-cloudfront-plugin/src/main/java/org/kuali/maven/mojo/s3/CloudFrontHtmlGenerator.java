package org.kuali.maven.mojo.s3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Generate directory listings in html format that is Amazon CloudFront friendly
 */
public class CloudFrontHtmlGenerator {
	HtmlUtils html = new HtmlUtils();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS z");

	S3BucketContext context;

	public CloudFrontHtmlGenerator() {
		this(null);
	}

	public CloudFrontHtmlGenerator(final S3BucketContext context) {
		super();
		this.context = context;
	}

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
	protected String getDirectory(final String prefix, final String delimiter) {
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
		return "<!-- Generated on " + sdf.format(new Date()) + " -->\n";
	}

	protected String getDocType() {
		return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n";
	}

	protected String getMeta() {
		return "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n";
	}

	protected String getGoogleAnalyticsJavascript() {
		return "<script type=\"text/javascript\">var _gaq = _gaq || []; _gaq.push(['_setAccount', 'UA-16781661-1']); _gaq.push(['_setDomainName', '.kuali.org']); _gaq.push(['_trackPageview']); (function() { var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true; ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js'; var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s); })();</script>\n";
	}

	/**
	 * Generate the full html page
	 */
	public String getHtml(final List<String[]> data, final String prefix, final String delimiter) {
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
		sb.append(this.html.openTag(html));
		sb.append(this.html.getIndentedContent(getHtmlComment()));
		sb.append(this.html.getTag(title, "Directory listing for " + directory));
		sb.append(this.html.openTag(head));
		sb.append(this.html.getIndentedContent("<link href=\"" + context.getCss()
				+ "\" rel=\"stylesheet\" type=\"text/css\"/>\n"));
		sb.append(this.html.getIndentedContent(getMeta()));
		sb.append(this.html.getIndentedContent(getGoogleAnalyticsJavascript()));
		sb.append(this.html.closeTag(head));
		sb.append(this.html.openTag(body));
		sb.append(this.html.openTag(div1));
		sb.append(this.html.getTag(span1, "Directory listing for " + directory));
		sb.append(this.html.closeTag(div1));
		sb.append(this.html.getIndentedContent("<hr>\n"));
		sb.append(this.html.openTag(div2));
		sb.append(getHtmlTable(data, getColumnDecorators()));
		sb.append(this.html.closeTag(div2));
		sb.append(this.html.getIndentedContent("<hr>\n"));
		sb.append(this.html.openTag(div3));
		sb.append(this.html.getTag(span2, context.getAbout()));
		sb.append(this.html.closeTag(div3));
		sb.append(this.html.closeTag(body));
		sb.append(this.html.closeTag(html));
		return sb.toString();
	}

	/**
	 * Generate html representing the contents of one table cell
	 */
	protected String getTableCell(final String content, final ColumnDecorator decorator) {
		Tag td = new Tag("td", decorator.getTableDataClass());
		return html.getTag(td, content);
	}

	/**
	 * Return true if the Collection is null or contains no entries, false otherwise
	 */
	protected boolean isEmpty(final Collection<?> c) {
		return c == null || c.size() == 0;
	}

	/**
	 * Alternate the styling of each row
	 */
	protected Tag getTableRowTag(final int row) {
		if ((row % 2) == 0) {
			return new Tag("tr", "table-tr-odd");
		} else {
			return new Tag("tr");
		}
	}

	/**
	 * Generate an html table row for the String[]
	 */
	protected String getTableRow(final int row, final String[] data, final List<ColumnDecorator> columnDecorators) {
		StringBuffer sb = new StringBuffer();
		Tag tr = getTableRowTag(row);
		sb.append(html.openTag(tr));
		for (int i = 0; i < data.length; i++) {
			sb.append(getTableCell(data[i], columnDecorators.get(i)));
		}
		sb.append(html.closeTag(tr));
		return sb.toString();
	}

	/**
	 * Generate a table row for each String[] in the list
	 */
	protected String getTableRows(final List<String[]> data, final List<ColumnDecorator> columnDecorators) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.size(); i++) {
			sb.append(getTableRow(i, data.get(i), columnDecorators));
		}
		return sb.toString();
	}

	/**
	 * Generate the html for the th tags from a list of ColumnDecorator objects
	 */
	protected String getTableHeaders(final List<ColumnDecorator> columnDecorators) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < columnDecorators.size(); i++) {
			ColumnDecorator decorator = columnDecorators.get(i);
			Tag th = new Tag("th", decorator.getTableDataClass());
			sb.append(html.openTag(th));
			sb.append(html.getTag(new Tag("span", decorator.getSpanClass()), decorator.getColumnTitle()));
			sb.append(html.closeTag(th));
		}
		return sb.toString();
	}

	/**
	 * Generate the table representing a directory listing
	 */
	protected String getHtmlTable(final List<String[]> data, final List<ColumnDecorator> columnDecorators) {
		if (isEmpty(data)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Tag table = new Tag("table", "mainTable");
		Tag thead = new Tag("thead");
		Tag tr = new Tag("tr");
		Tag tbody = new Tag("tbody");
		sb.append(html.openTag(table));
		sb.append(html.openTag(thead));
		sb.append(html.openTag(tr));
		sb.append(getTableHeaders(columnDecorators));
		sb.append(html.closeTag(tr));
		sb.append(html.closeTag(thead));
		sb.append(html.openTag(tbody));
		sb.append(getTableRows(data, columnDecorators));
		sb.append(html.closeTag(tbody));
		sb.append(html.closeTag(table));
		return sb.toString();
	}

	public S3BucketContext getContext() {
		return context;
	}

	public void setContext(final S3BucketContext context) {
		this.context = context;
	}

}
