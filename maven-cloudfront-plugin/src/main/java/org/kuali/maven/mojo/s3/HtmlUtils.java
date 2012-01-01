/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.maven.mojo.s3;

import org.apache.commons.lang.StringUtils;

/**
 * Utility methods for generating html
 */
public class HtmlUtils {

    int indent = 0;

    public String getIndentedContent(final String content) {
        return getIndent() + content;
    }

    public String getIndent() {
        return StringUtils.repeat(" ", indent);
    }

    /**
     * Return an HTML ahref tag
     */
    public String getHref(final String dest, final String show) {
        return getIndent() + "<a href=\"" + dest + "\">" + show + "</a>";
    }

    /**
     * Return an HTML img tag
     */
    public String getImage(final String image) {
        return getIndent() + "<img src=\"" + image + "\">";
    }

    public String openTag(final Tag tag) {
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

    public String closeTag(final Tag tag) {
        indent--;
        return getIndent() + "</" + tag.getName() + ">\n";
    }

    public String getTag(final Tag tag, final String content) {
        StringBuffer sb = new StringBuffer();
        sb.append(openTag(tag));
        sb.append(getIndent());
        sb.append(content);
        sb.append("\n");
        sb.append(closeTag(tag));
        return sb.toString();
    }
}
