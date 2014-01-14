<%--

    Copyright 2004-2013 The Kuali Foundation

    Licensed under the Educational Community License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.opensource.org/licenses/ecl2.php

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>
<%@ page import="java.util.*,java.text.*"%>
<%

            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S z");
            StringBuilder sb = new StringBuilder();
            Runtime runtime = Runtime.getRuntime();
            int processors = runtime.availableProcessors();
            double gigabyte = 1024 * 1024 * 1024;

            // The total amount of memory the JVM is allowed to allocate 
            double maxMemory = runtime.maxMemory();

            // The total amount of memory currently allocated by the JVM 
            double allocatedMemory = runtime.totalMemory();

            // The amount of memory still available in the currently allocated heap 
            double freeMemory = runtime.freeMemory();

            // The JDK methods supplied with the Runtime object do not directly provide
            // a number for used memory.  Those methods tell us what is currently allocated
            // and how much of that allocation is *not* being used.  The amount of memory 
            // currently being used by the JVM is the difference between the two. 
            double usedMemory = allocatedMemory - freeMemory;
            
            // The JDK method Runtime.freeMemory() reports the amount of memory available
            // in the currently allocated heap.  We are interested in the total amount of 
            // memory still available to the JVM irrespective of the size of the currently 
            // allocated heap.
            double totalFreeMemory = maxMemory - usedMemory;
            
            sb.append("<li>time: " + sdf.format(new Date()) + "</li>\n");
            sb.append("<li>processors: " + processors + "</li>\n");
            sb.append("<li>mem: [used=" + nf.format(usedMemory / gigabyte) + "g");
            sb.append(", free=" + nf.format(totalFreeMemory / gigabyte) + "g");
            sb.append(", allocated=" + nf.format(allocatedMemory / gigabyte) + "g");
            sb.append(", max=" + nf.format(maxMemory / gigabyte) + "g");
            sb.append("]</li>\n");
            String pathSeparatorKey = "path.separator";
            String lineSeparatorKey = "line.separator";
            String pathSeparator = System.getProperty(pathSeparatorKey);
            Properties p = System.getProperties();
            Map<String, String> m = new TreeMap<String, String>();

            Set<String> names = p.stringPropertyNames();
            for (String name : names) {
                String value = p.getProperty(name);
                m.put(name, value);
            }
            sb.append("<table><tr valign=top><td>");
            sb.append("<table border=1>\n");
            sb.append("<th>System Property</th><th>Value</th>\n");
            for (String name : m.keySet()) {
                String value = m.get(name);
                if (value == null) {
                  value = "";
                }
                if (name.equals(lineSeparatorKey)) {
                    value = value.replace("\n", "LF");
                    value = value.replace("\r", "CR");
                }
                if (name.equals("common.loader") || name.contains("package.") || name.equalsIgnoreCase("tomcat.util.scan.DefaultJarScanner.jarsToSkip")) {
                    value = value.replace(",", "," + "<br>");
                }
                if (name.contains(".path") || name.contains(".dirs")) {
                    value = value.replace(pathSeparator, pathSeparator + "<br>");
                }
                if (value == null || value.trim().equals("")) {
                    value = "&nbsp;";
                }
                sb.append(" <tr>\n");
                sb.append("  <td>" + name + "</td><td>" + value + "</td>\n");
                sb.append(" </tr>\n");
            }
            sb.append("</table>\n");
            sb.append("</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td>");
            m = new TreeMap<String, String>(System.getenv());
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
                if (name.equals("CATALINA_OPTS") || name.equals("JAVA_OPTS")) {
                  value = value.replace(" ", "<br>");
                }
                if (name.toLowerCase().contains("path")) {
                  value = value.replace(pathSeparator, pathSeparator + "<br>");
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
              value = value.replace(pathSeparator, pathSeparator + "<br>");
              sb.append("  <td>" + name + "</td><td>" + value + "</td>\n");
              sb.append(" </tr>\n");
            }
            sb.append("</table>\n");
            sb.append("</td></tr></table>");
            out.println(sb);

%>
