<%--

    Copyright 2004-2014 The Kuali Foundation

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
<%@ page import="java.io.*" %><%

response.setContentType("text/plain");

int display = 10 * 1024;
if (request.getParameter("k") != null) {
  display = new Integer(request.getParameter("k")) * 1024;
}
String catalinaBase = System.getProperty("catalina.base");
String filename = catalinaBase + "/logs/catalina.out";
File file = new File(filename);
long length = file.length();
InputStream in = new FileInputStream(file);
long skip = length - display;
if (skip < 0) {
  skip = 0;
}
in.skip(skip);
byte[] buffer = new byte[4096];
int readLength = 0;
while ((readLength = in.read(buffer, 0, buffer.length)) != -1) {
        out.write(new String(buffer, 0, readLength));
}
in.close();
%>
