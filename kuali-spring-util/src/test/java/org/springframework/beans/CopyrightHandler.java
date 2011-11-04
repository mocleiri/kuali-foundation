package org.springframework.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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

    protected void write(File file, String content) {
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

    protected String getExistingContent(File file) {
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
        String content = getExistingContent(file);
        String flat = flatten(content);
        String filename = file.getName();
        File newFile1 = new File("C:/temp/ecl/" + filename);
        File newFile2 = new File("C:/temp/ecl/" + filename + ".flat");
        write(newFile1, content);
        write(newFile2, flat);
    }

    protected void exec(String[] args) {
        try {

            ProblemFileContext context = new MultipleCopyRightContext("c:/eclipse/3.6.2/r11/eclipse/ws/rice-2.0-trunk");
            context.setInclude(new FilenameContainsDotXFilter());

            ProblemFileDetector detector = new ProblemFileDetector();
            List<File> files = detector.getProblemFiles(context);
            System.out.println(files.size());
            for (File file : files) {
                System.out.println(file.getAbsolutePath());
            }
            Properties invalidEcl = getXMLProperties("invalid-ecl-headers.xml");
            List<String> contentsToRemove = getValues(invalidEcl);
            File foo = new File("c:/temp/ecl/invalid.txt");
            StringBuilder sb = new StringBuilder();
            for (String s : contentsToRemove) {
                String flat = flatten(s);
                sb.append(flat + "\n\n");
            }
            write(foo, sb.toString());

            ContentRemover remover = new ContentRemover();
            for (File file : files) {
                String name = file.getName();
                if (name.equals("EDLSpringBeans.xml")) {
                    copy(file);
                }
                remover.removeContent(file, contentsToRemove);
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    protected List<String> getValues(Properties properties) {
        List<String> values = new ArrayList<String>();
        Set<String> keys = properties.stringPropertyNames();
        for (String key : keys) {
            String value = properties.getProperty(key);

            value = value.replace("${ecl.cr}", "\r");
            value = value.replace("${ecl.lf}", "\n");

            // If it has cr+linefeed change it to just linefeed
            String lf = value.replace("\r\n", "\n");

            // Expand linefeed into cr+linefeed
            String crlf = lf.replace("\n", "\r\n");

            // Add both values, if we have an exact match where the only
            // difference is cr+linefeed vs linefeed it needs to be replaced
            values.add(lf);
            values.add(crlf);
        }
        return values;
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
