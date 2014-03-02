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
import org.kuali.common.util.Counter;

/**
 * Utility methods for generating html
 */
public class HtmlUtils {

	public static String getIndentedContent(String content, Counter indent) {
		return getIndent(indent) + content;
	}

	public static String getIndent(Counter indent) {
		return StringUtils.repeat(" ", indent.getValue());
	}

	/**
	 * Return an HTML ahref tag
	 */
	public static String getHref(String dest, String show, Counter indent) {
		return getIndent(indent) + "<a href=\"" + dest + "\">" + show + "</a>";
	}

	/**
	 * Return an HTML img tag
	 */
	public static String getImage(String image, Counter indent) {
		return getIndent(indent) + "<img src=\"" + image + "\">";
	}

	public static String openTag(Tag tag, Counter indent) {
		StringBuffer sb = new StringBuffer();
		sb.append(getIndent(indent));
		indent.increment();
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

	public static String closeTag(Tag tag, Counter indent) {
		indent.decrement();
		return getIndent(indent) + "</" + tag.getName() + ">\n";
	}

	public static String getTag(Tag tag, String content, Counter indent) {
		StringBuffer sb = new StringBuffer();
		sb.append(openTag(tag, indent));
		sb.append(getIndent(indent));
		sb.append(content);
		sb.append("\n");
		sb.append(closeTag(tag, indent));
		return sb.toString();
	}
}
