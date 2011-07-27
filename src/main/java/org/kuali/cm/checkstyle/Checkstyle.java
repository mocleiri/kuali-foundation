package org.kuali.cm.checkstyle;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class Checkstyle {

    public static void main(String[] args) {
        try {
            Checkstyle cs = new Checkstyle();
            List<Error> errors = cs.getErrorObjects(cs.getErrors());
            System.out.println(errors.size());
            for (Error e : errors) {
                System.out.println(e.getSrc() + "=" + e.getMsg());
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
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
