package org.kuali.cm.checkstyle;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class Checkstyle {

    public static void main(String[] args) {
        new Checkstyle().execute();
    }

    protected void execute() {
        try {
            List<Error> errors = getErrorObjects(getErrors());
            Map<String, String> issues = new TreeMap<String, String>();
            Map<String, SourceFile> files = new TreeMap<String, SourceFile>();
            Properties props = getCheckStyleProps();
            for (Error e : errors) {
                String msg = translate(e.getMsg());
                issues.put(msg, msg);
                SourceFile sf = files.get(e.getSrc());
                if (sf == null) {
                    sf = new SourceFile();
                    sf.setName(e.getSrc());
                    List<String> violations = new ArrayList<String>();
                    sf.setViolations(violations);
                    files.put(e.getSrc(), sf);
                }
                sf.getViolations().addAll(getCheckStyleNames(msg, props));
            }
            for (String key : issues.keySet()) {
                System.out.println(key);
            }
            System.out.println();
            System.out.println("Files: " + files.size() + " Issues: " + issues.size());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    protected List<String> getCheckStyleNames(String msg, Properties props) {
        List<String> names = new ArrayList<String>();
        for (String key : props.stringPropertyNames()) {
            String value = props.getProperty(key);
            if (value.equals(msg)) {
                names.add(key);
            }
        }
        return names;
    }

    protected Properties getCheckStyleProps() throws IOException {
        String location = "classpath:checkstyle.properties";
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource(location);
        Properties props = new Properties();
        props.load(resource.getInputStream());
        return props;
    }

    protected String translate(String msg) {
        if (msg.contains("must be private and have accessor methods.")) {
            return "must be private and have accessor methods.";
        }
        if (msg.contains("must match pattern '^[a-z][a-zA-Z0-9]*$'.")) {
            return "must match pattern '^[a-z][a-zA-Z0-9]*$'.";
        }
        if (msg.contains("must match pattern '^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$'.")) {
            return "must match pattern '^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$'.";
        }
        if (msg.contains("must match pattern '^[a-z_][a-zA-Z0-9]*$'.")) {
            return "must match pattern '^[a-z_][a-zA-Z0-9]*$'.";
        }
        if (msg.contains("must match pattern '^[A-Z][a-zA-Z0-9]*$'.")) {
            return "must match pattern '^[A-Z][a-zA-Z0-9]*$'.";
        }
        if (msg.contains("Method length is")) {
            return "Method length is";
        }
        if (msg.contains("File length is")) {
            return "File length is";
        }
        if (msg.contains("construct must use '{}'s.")) {
            return "construct must use '{}'s.";
        }
        if (msg.contains("is not preceded with whitespace.")) {
            return "is not preceded with whitespace.";
        }
        if (msg.contains("is not followed by whitespace.")) {
            return "is not followed by whitespace.";
        }
        if (msg.contains("should be on a new line.")) {
            return "should be on a new line.";
        }
        if (msg.contains("should be on the same line.")) {
            return "should be on the same line.";
        }
        if (msg.contains("Got an exception -")) {
            return "Got an exception -";
        }
        return msg;
    }

    protected List<Error> getErrorObjects(List<String> errors) {
        List<Error> list = new ArrayList<Error>();
        for (String error : errors) {
            list.add(getError(error));
        }
        return list;
    }

    protected Error getError(String s) {
        Error error = new Error();
        error.setSrc(getSrc(s));
        error.setMsg(getMsg(s));
        return error;
    }

    protected String getSrc(String s) {
        String src = StringUtils.substringBetween(s, "[ERROR]", "[").trim();
        return src;
    }

    protected String getMsg(String s) {
        int pos = s.indexOf(".java");
        String msg = s.substring(pos);
        pos = msg.indexOf("]") + 1;
        return msg.substring(pos).trim();
    }

    protected List<String> getErrors() throws IOException {
        InputStream in = null;
        try {
            String location = "classpath:checkstyle.txt";
            ResourceLoader loader = new DefaultResourceLoader();
            Resource resource = loader.getResource(location);
            in = resource.getInputStream();
            String s = IOUtils.toString(in);
            String[] tokens = StringUtils.splitByWholeSeparator(s, "\n");
            List<String> errors = new ArrayList<String>();
            for (int i = 0; i < tokens.length; i++) {
                int pos = tokens[i].indexOf("ERROR");
                if (pos != -1) {
                    errors.add(tokens[i].trim());
                }
            }
            return errors;
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}
