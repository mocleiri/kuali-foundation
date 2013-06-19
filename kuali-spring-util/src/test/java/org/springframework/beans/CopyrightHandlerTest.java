package org.springframework.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class CopyrightHandlerTest {
    String tmpdir = System.getProperty("java.io.tmpdir");

    public static final String ECL_CR = "${ecl.cr}";
    public static final String ECL_LF = "${ecl.lf}";
    public static final String CR = "\r";
    public static final String LF = "\n";

    public static void main(String[] args) {
        new CopyrightHandlerTest().exec(args);
    }

    protected String getBaseDir() {
        String basedir = System.getProperty("ecl.basedir");
        if (basedir == null) {
            throw new RuntimeException("Set the system property 'ecl.basedir'");
        } else {
            return basedir;
        }

    }

    @Test
    public void testDuplicateCopyright() {
        try {
            String basedir = getBaseDir();
            System.out.println("Examining " + basedir);
            System.out.println("Tmp Dir: " + tmpdir);
            ProblemFileContext context = new MultipleCopyrightContext(basedir);
            ProblemFileDetector detector = new ProblemFileDetector();
            List<File> files = detector.getProblemFiles(context);
            System.out.println("Located " + files.size() + " unknown files with multiple 'Copyright' lines");
            Properties invalidEcl = getProperties("invalid-ecl.properties");
            Set<String> contentsToRemove = getValues(invalidEcl);
            ContentRemover remover = new ContentRemover();
            Iterator<File> itr = files.iterator();
            List<File> updatedFiles = new ArrayList<File>();
            List<File> nonUpdatedFiles = new ArrayList<File>();
            while (itr.hasNext()) {
                File file = itr.next();
                boolean updated = remover.removeContent(file, contentsToRemove);
                if (!updated) {
                    copy(file);
                    nonUpdatedFiles.add(file);
                } else {
                    updatedFiles.add(file);
                }
            }
            System.out.println("Updated files");
            System.out.println("---------------------");
            for (File file : updatedFiles) {
                System.out.println(file.getAbsolutePath());
            }
            System.out.println();
            System.out.println("Multi-copyright files");
            System.out.println("---------------------");
            for (File file : nonUpdatedFiles) {
                System.out.println(file.getAbsolutePath());
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    protected void exec(String[] args) {
        testDuplicateCopyright();
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

    public static final void write(File file, String content) {
        OutputStream out = null;
        try {
            out = FileUtils.openOutputStream(file);
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

    public static final String unflatten(String s) {
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
        File flatFile = new File(tmpdir + "/" + filename);
        write(flatFile, flat);
    }

}
