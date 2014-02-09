<%@ page import="java.io.*" %><%response.setContentType("text/plain");

int display = 10 * 1024;
if (request.getParameter("k") != null) {
  display = new Integer(request.getParameter("k")) * 1024;
}
String systemProperty = "catalina.base";
if (request.getParameter("systemProperty") != null) {
	systemProperty = request.getParameter("systemProperty");
}
String path = "logs/catalina.out";
if (request.getParameter("path") != null) {
	path = request.getParameter("path");
}
String basedir = System.getProperty(systemProperty);
String filename = basedir + File.separator + path;
if (request.getParameter("file") != null) {
	filename = request.getParameter("file");
}
File file = new File(filename);
long length = file.length();
InputStream in = new FileInputStream(file);
long skip = length <= display ? 0 : length - display;
in.skip(skip);
String encoding = "UTF-8";
byte[] buffer = new byte[4096];
int amount = 0;
while ((amount = in.read(buffer, 0, buffer.length)) != -1) {
  out.write(new String(buffer, 0, amount, encoding));
}
in.close();
%>
