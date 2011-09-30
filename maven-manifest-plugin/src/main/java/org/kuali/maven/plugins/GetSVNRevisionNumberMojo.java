package org.kuali.maven.plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Examine a MANIFEST.MF file for the presence of an SVN revision number. If found, set the system property
 * "manifest.SVN-Revision" to the value that was located
 *
 * @author Jeff Caddel
 * @goal svnrevision
 */
public class GetSVNRevisionNumberMojo extends AbstractMojo {

    /**
     * @parameter expression="${manifest.filename}"
     * @required
     */
    private String filename;

    /**
     * @parameter expression="${manifest.attribute}" default-value="SVN-Revision";
     * @required
     */
    private String attribute;

    /**
     * @parameter expression="${manifest.prefix}" default-value="manifest";
     */
    private String prefix;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            Manifest manifest = getManifest(filename);
            Attributes attributes = manifest.getMainAttributes();
            String revisionNumber = attributes.getValue(attribute);
            validate(revisionNumber);
            String key = "prefix" + "." + attribute;
            getLog().info(key + "=" + revisionNumber);
            System.setProperty(key, revisionNumber);
        } catch (Exception e) {
            throw new MojoExecutionException("Error handling " + filename, e);
        }
    }

    protected void validate(String revisionNumber) throws MojoExecutionException {
        if (revisionNumber == null || revisionNumber.trim().equals("")) {
            throw new MojoExecutionException("Could not locate a value for " + attribute + " in " + filename);
        }
        Integer number = Integer.parseInt(revisionNumber);
        if (number < 0) {
            throw new MojoExecutionException("Negative revision number");
        }
    }

    protected Manifest getManifest(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filename);
        }
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            return new Manifest(in);
        } finally {
            close(in);
        }
    }

    protected void close(InputStream in) {
        if (in == null) {
            return;
        }
        try {
            in.close();
        } catch (IOException e) {
            ; // ignore
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
