/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.html;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility methods for generating html
 */
public class HtmlUtils {

	// TODO Holy crap this is awful
	private static int indent = 0;

	public static String getIndentedContent(String content) {
		return getIndent() + content;
	}

	public static String getIndent() {
		return StringUtils.repeat(" ", indent);
	}

	/**
	 * Return an HTML ahref tag
	 */
	public static String getHref(String dest, String show) {
		return getIndent() + "<a href=\"" + dest + "\">" + show + "</a>";
	}

	/**
	 * Return an HTML img tag
	 */
	public static String getImage(String image) {
		return getIndent() + "<img src=\"" + image + "\">";
	}

	public static String openTag(Tag tag) {
		StringBuffer sb = new StringBuffer();
		sb.append(getIndent());
		indent++;
		sb.append("<" + tag.getName());
		if (tag.getId() != null) {
			sb.append(" id=\"" + tag.getId() + '"');
		}
		if (tag.getClazz() != null) {
			sb.append(" class=\"" + tag.getClazz() + '"');
		}
		sb.append(">\n");
		return sb.toString();
	}

	public static String closeTag(Tag tag) {
		indent--;
		return getIndent() + "</" + tag.getName() + ">\n";
	}

	public static String getTag(Tag tag, String content) {
		StringBuffer sb = new StringBuffer();
		sb.append(openTag(tag));
		sb.append(getIndent());
		sb.append(content);
		sb.append("\n");
		sb.append(closeTag(tag));
		return sb.toString();
	}
}
