package org.springframework.beans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class CopyrightHandler {

    public static void main(String[] args) {
        new CopyrightHandler().exec(args);
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

            ContentRemover remover = new ContentRemover();
            for (File file : files) {
                String name = file.getName();
                if (name.equals("EDLSpringBeans.xml")) {
                    System.out.println("foo");
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
