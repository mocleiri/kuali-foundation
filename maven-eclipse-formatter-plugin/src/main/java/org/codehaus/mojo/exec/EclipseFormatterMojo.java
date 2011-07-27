package org.codehaus.mojo.exec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.tools.ant.DirectoryScanner;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * A plugin for executing the Eclipse java source code formatter
 * 
 * @author Jeff Caddel
 * @goal format
 * @aggregator
 */
public class EclipseFormatterMojo extends ExecMojo {
    private static final String FS = System.getProperty("file.separator");

    /**
     * Binaries representing a Java VM. Default values are "javaw.exe", "java.exe", and "java". It will search for
     * binaries in the order specified, stopping as soon as one is found.
     * 
     * @parameter expression="${eclipse.java.binaries}"
     */
    private String[] binaries = new String[] { "javaw.exe", "java.exe", "java" };

    /**
     * Full path to the Eclipse executable
     * 
     * @parameter expression="${eclipse.executable}"
     * @required
     */
    private String eclipseExecutable;

    /**
     * Full path to a Java VM. This gets filled in using the system property "java.home" unless a value is supplied
     * here.
     * 
     * @parameter expression="${eclipse.vm}"
     */
    private String vm;

    /**
     * 
     * @parameter expression="${eclipse.application}" default-value="org.eclipse.jdt.core.JavaCodeFormatter"
     * @required
     */
    private String application;

    /**
     * Pointer to an Eclipse "org.eclipse.jdt.core.prefs" file. Supports "classpath:" style notation
     * 
     * @parameter expression="${eclipse.formatterPreferences}" default-value="classpath:eclipse.prefs"
     * @required
     */
    private String formatterPreferences;

    /**
     * Any arguments specified here are passed to the Eclipse binary as additional command line arguments. Default
     * values are "-nosplash -verbose"
     */
    private String[] args = new String[] { "-nosplash", "-verbose" };

    /**
     * Regular expressions for directories containing Java source code. Default values are &#042;&#042;/src/main/java
     * and &#042;&#042;/src/test/java. The Eclipse formatter will recursively inspect any directories matching these
     * patterns for *.java files
     * 
     * @parameter
     */
    private String[] includes = new String[] { "**/src/main/java", "**/src/test/java" };

    /**
     * Regular expressions for directories to exclude from scanning for Java source code
     * 
     * @parameter
     */
    private String[] excludes = new String[] {};

    protected List<File> getSourceDirectories() {
        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(project.getBasedir());
        scanner.setIncludes(includes);
        scanner.setExcludes(excludes);
        scanner.scan();
        String[] includedDirs = scanner.getIncludedDirectories();
        List<File> dirs = new ArrayList<File>();
        for (String includedDir : includedDirs) {
            File file = new File(project.getBasedir().getAbsolutePath() + FS + includedDir);
            dirs.add(file);
        }
        return dirs;
    }

    @Override
    public void execute() throws MojoExecutionException {
        File file = new File(eclipseExecutable);
        if (!file.exists()) {
            throw new MojoExecutionException(eclipseExecutable + " does not exist");
        }
        getLog().info("Scanning for source directories underneath: " + project.getBasedir());
        List<File> dirs = getSourceDirectories();

        if (dirs.size() == 0) {
            getLog().info("No directories containing source code were located");
            return;
        } else {
            getLog().info("Located the following " + dirs.size() + " source directories:");
            for (File dir : dirs) {
                getLog().info(dir.getAbsolutePath());
            }
        }

        super.setExecutable(quote(eclipseExecutable));
        super.setArguments(getEclipseArguments(dirs));

        super.execute();
    }

    protected boolean sourceDirExists() {
        return new File(project.getBuild().getSourceDirectory()).exists();
    }

    protected boolean testSourceDirExists() {
        return new File(project.getBuild().getTestSourceDirectory()).exists();
    }

    protected String getJavaBinary() throws MojoExecutionException {
        if (!StringUtils.isEmpty(vm)) {
            return vm;
        }
        String javaHome = System.getProperty("java.home");
        String binaryHome = javaHome + FS + "bin";
        for (String binary : binaries) {
            File file = new File(binaryHome + FS + binary);
            if (file.exists()) {
                return file.getAbsolutePath();
            }
        }
        throw new MojoExecutionException(
                "No Java VM location was supplied, and we could not locate one using the System property 'java.home'");
    }

    protected List<String> getEclipseArguments(List<File> dirs) throws MojoExecutionException {
        List<String> eclipseArgs = new ArrayList<String>();
        eclipseArgs.add("-application");
        eclipseArgs.add(quote(application));
        eclipseArgs.add("-vm");
        String binary = getJavaBinary();
        getLog().info("Using Java VM: " + binary);
        eclipseArgs.add(quote(getJavaBinary()));
        eclipseArgs.add("-config");
        eclipseArgs.add(quote(getConfigAbsolutePath()));
        for (String arg : args) {
            addIfNotEmpty(eclipseArgs, arg);
        }
        for (File dir : dirs) {
            eclipseArgs.add(quote(dir.getAbsolutePath()));
        }
        return eclipseArgs;
    }

    protected String quote(String s) {
        return '"' + s + '"';
    }

    protected String getConfigAbsolutePath() throws MojoExecutionException {
        File file = new File(formatterPreferences);
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource(formatterPreferences);
        if (!resource.exists()) {
            throw new MojoExecutionException("Unable to locate " + formatterPreferences);
        }
        OutputStream out = null;
        try {
            File temp = File.createTempFile("eclipse.prefs.", null);
            out = new FileOutputStream(temp);
            IOUtils.copy(resource.getInputStream(), out);
            return temp.getAbsolutePath();
        } catch (IOException e) {
            throw new MojoExecutionException("Error copying resource " + formatterPreferences, e);
        } finally {
            IOUtils.closeQuietly(out);
        }

    }

    protected void addIfNotEmpty(List<String> list, String s) {
        if (StringUtils.isEmpty(s)) {
            return;
        }
        list.add(s);
    }

    public String getEclipseExecutable() {
        return eclipseExecutable;
    }

    public void setEclipseExecutable(String eclipseExecutable) {
        this.eclipseExecutable = eclipseExecutable;
    }

    public String getVm() {
        return vm;
    }

    public void setVm(String vm) {
        this.vm = vm;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String[] getIncludes() {
        return includes;
    }

    public void setIncludes(String[] includes) {
        this.includes = includes;
    }

    public String[] getExcludes() {
        return excludes;
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }

    public String[] getBinaries() {
        return binaries;
    }

    public void setBinaries(String[] binaries) {
        this.binaries = binaries;
    }

    public String getFormatterPreferences() {
        return formatterPreferences;
    }

    public void setFormatterPreferences(String formatterPreferences) {
        this.formatterPreferences = formatterPreferences;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

}
