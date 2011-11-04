package org.springframework.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class CopyrightHandler {

    public static final String ECL_CR = "${ecl.cr}";
    public static final String ECL_LF = "${ecl.lf}";
    public static final String CR = "\r";
    public static final String LF = "\n";

    public static void main(String[] args) {
        new CopyrightHandler().exec(args);
    }

    public static final void write(File file, String content) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            IOUtils.write(content.getBytes(), out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    public static final String read(File file) {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            return IOUtils.toString(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public static final String expand(String s) {
        s = s.replace(ECL_CR, CR);
        s = s.replace(ECL_LF, LF);
        return s;
    }

    public static final String flatten(String s) {
        s = s.replace(CR, ECL_CR);
        s = s.replace(LF, ECL_LF);
        return s;
    }

    protected void copy(File file) {
        String content = read(file);
        String flat = flatten(content);
        String filename = file.getName();
        File flatFile = new File("C:/temp/ecl/" + filename);
        write(flatFile, flat);
    }

    protected void exec(String[] args) {
        try {

            ProblemFileContext context = new MultipleCopyrightContext("c:/eclipse/3.6.2/r11/eclipse/ws/rice-2.0-trunk");
            context.setInclude(new FilenameContainsDotXFilter());

            ProblemFileDetector detector = new ProblemFileDetector();
            List<File> files = detector.getProblemFiles(context);
            System.out.println(files.size());
            Properties invalidEcl = getProperties("invalid-ecl.properties");
            Set<String> contentsToRemove = getValues(invalidEcl);
            ContentRemover remover = new ContentRemover();
            Iterator<File> itr = files.iterator();
            while (itr.hasNext()) {
                File file = itr.next();
                boolean updated = remover.removeContent(file, contentsToRemove);
                if (!updated) {
                    copy(file);
                }
            }
            for (File file : files) {
                System.out.println("Multi-copyright files: " + file.getAbsolutePath());
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    protected Set<String> getValues(Properties properties) {
        Set<String> values = new HashSet<String>();
        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            String value = properties.getProperty(key);
            if (values.contains(value)) {
                throw new RuntimeException("key " + key + " is a duplicate");
            }
            values.add(value);
        }
        return values;
    }

    protected Properties getProperties(String location) {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource(location);
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = resource.getInputStream();
            properties.load(in);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    protected Properties getXMLProperties(String location) {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource(location);
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = resource.getInputStream();
            properties.loadFromXML(in);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

}
