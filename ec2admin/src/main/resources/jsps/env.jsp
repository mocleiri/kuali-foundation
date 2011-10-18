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
            double freeMemory = runtime.freeMemory();
            double maxMemory = runtime.maxMemory();
            double allocatedMemory = runtime.totalMemory();
            double usedMemory = allocatedMemory - freeMemory;
            double totalFreeMemory = maxMemory - usedMemory;
            sb.append("<li>time: " + sdf.format(new Date()) + "</li>\n");
            sb.append("<li>processors: " + processors + "</li>\n");
            sb.append("<li>mem: [max=" + nf.format(maxMemory / gigabyte) + "g");
            sb.append(", allocated=" + nf.format(allocatedMemory / gigabyte) + "g");
            sb.append(", used=" + nf.format(usedMemory / gigabyte) + "g");
            sb.append(", free=" + nf.format(totalFreeMemory / gigabyte) + "g");
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
                if (name.equals("common.loader") || name.contains("package.")) {
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
                if (name.equals("CATALINA_OPTS")) {
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
