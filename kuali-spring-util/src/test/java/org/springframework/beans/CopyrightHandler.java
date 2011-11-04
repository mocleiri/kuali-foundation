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
            Properties invalidEcl = getXMLProperties("invalid-ecl-headers.xml");
            List<String> contentsToRemove = getValues(invalidEcl);

            ContentRemover remover = new ContentRemover();
            for (File file : files) {
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
            String value1 = properties.getProperty(key);
            String value2 = value1.replace("\n", "\r\n");
            values.add(value1);
            values.add(value2);
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
