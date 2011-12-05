/**
 * Copyright 2004-2011 The Kuali Foundation
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
package org.kuali.maven.plugins.jenkins;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

public class Env {
    public static final String FS = System.getProperty("file.separator");
    public static final String PS = System.getProperty("path.separator");

    public void write() {
        try {
            FileUtils.write(new File("/Users/jeffcaddel/env.htm"), getHtml());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHeader());
        sb.append("<table><tr valign=top><td>");
        sb.append(getSys());
        sb.append("</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>");
        // sb.append(getEnv());
        sb.append("</td></tr></table>");
        return sb.toString();
    }

    protected String getHeader() {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S z");
        StringBuilder sb = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        int processors = runtime.availableProcessors();
        double gigabyte = 1024 * 1024 * 1024;
        double freeMemory = runtime.freeMemory();
        double maxMemory = runtime.maxMemory();
        double allocatedMemory = runtime.totalMemory();
        double usedMemory = allocatedMemory - freeMemory;
        double totalFreeMemory = maxMemory - usedMemory;
        sb.append("<li>time: " + sdf.format(new Date()) + "</li>\n");
        sb.append("<li>processors: " + processors + "</li>\n");
        sb.append("<li>mem: [used=" + nf.format(usedMemory / gigabyte) + "g");
        sb.append(", free=" + nf.format(totalFreeMemory / gigabyte) + "g");
        sb.append(", allocated=" + nf.format(allocatedMemory / gigabyte) + "g");
        sb.append(", max=" + nf.format(maxMemory / gigabyte) + "g");
        sb.append("]</li>\n");
        return sb.toString();
    }

    protected String getSys() {
        Properties p = System.getProperties();
        Map<String, String> m = new TreeMap<String, String>();

        Set<String> names = p.stringPropertyNames();
        for (String name : names) {
            String value = p.getProperty(name);
            m.put(name, value);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=1>\n");
        sb.append("<th>System Property</th><th>Value</th>\n");
        for (String name : m.keySet()) {
            String value = m.get(name);
            if (value == null) {
                value = "";
            }
            if (name.equals("line.separator")) {
                value = value.replace("\n", "LF");
                value = value.replace("\r", "CR");
            }
            if (name.equals("common.loader") || name.contains("package.")
                    || name.equalsIgnoreCase("tomcat.util.scan.DefaultJarScanner.jarsToSkip")) {
                value = value.replace(",", "," + "<br>");
            }
            if (name.contains(".path") || name.contains(".dirs")) {
                value = value.replace(PS, PS + "<br>");
            }
            if (value == null || value.trim().equals("")) {
                value = "&nbsp;";
            }
            sb.append(" <tr>\n");
            sb.append("  <td>" + name + "</td><td>" + value + "</td>\n");
            sb.append(" </tr>\n");
        }
        sb.append("</table>\n");
        return sb.toString();
    }

    protected String getEnv() {
        Map<String, String> m = new TreeMap<String, String>(System.getenv());
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=1>\n");
        sb.append("<th>Environment Variable</th><th>Value</th>\n");
        for (String name : m.keySet()) {
            if (name.equals("LS_COLORS")) {
                continue;
            }
            String value = m.get(name);
            if (value == null) {
                value = "";
            }
            if (name.equals("CATALINA_OPTS")) {
                value = value.replace(" ", "<br>");
            }
            if (name.toLowerCase().contains("path")) {
                value = value.replace(PS, PS + "<br>");
            }
            if (value == null || value.trim().equals("")) {
                value = "&nbsp;";
            }
            sb.append(" <tr>\n");
            sb.append("  <td>" + name + "</td><td>" + value + "</td>\n");
            sb.append(" </tr>\n");
        }

        String name = "LS_COLORS";
        String value = m.get(name);
        if (value != null) {
            sb.append(" <tr>\n");
            value = value.replace(PS, PS + "<br>");
            sb.append("  <td>" + name + "</td><td>" + value + "</td>\n");
            sb.append(" </tr>\n");
        }
        sb.append("</table>\n");
        return sb.toString();
    }

}
