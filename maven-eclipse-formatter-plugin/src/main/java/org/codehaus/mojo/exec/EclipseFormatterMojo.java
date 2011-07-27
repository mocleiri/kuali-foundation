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
     * Binaries representing a Java VM. Default values are "javaw.exe", "java.exe", and "java". This list is searched in
     * order, stopping as soon as one is found.
     * 
     * @parameter
     */
    private String[] javaBinaries = new String[] { "javaw.exe", "java.exe", "java" };

    /**
     * Full path to the Eclipse executable binary
     * 
     * @parameter expression="${eclipse.binary}"
     * @required
     */
    private String eclipseBinary;

    /**
     * Full path to a Java VM. This gets filled in using the system property "java.home" unless a value is supplied
     * here.
     * 
     * @parameter expression="${eclipse.vm}"
     */
    private String vm;

    /**
     * This is the name of the Eclipse application that performs the formatting
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
     * 
     * @parameter
     */
    private String[] eclipseArgs = new String[] { "-nosplash", "-verbose" };

    /**
     * Regular expressions for directories that contain Java source code to format. Default values are
     * &#042;&#042;/src/main/java and &#042;&#042;/src/test/java. The Eclipse formatter will recursively inspect any
     * directories matching these patterns for *.java files
     * 
     * @parameter
     */
    private String[] includes = new String[] { "**/src/main/java", "**/src/test/java" };

    /**
     * Regular expressions for directories to exclude from the formatting process.
     * 
     * @parameter
     */
    private String[] excludes = new String[] { "**/.settings", "**/.svn" };

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
        File file = new File(eclipseBinary);
        if (!file.exists()) {
            throw new MojoExecutionException(eclipseBinary + " does not exist");
        }
        getLog().info("Eclipse Location: " + file.getAbsolutePath());
        getLog().info("Java VM: " + getJavaBinary());
        getLog().info("Scanning directory: " + project.getBasedir());
        StringBuilder sb = new StringBuilder();
        for (String include : includes) {
            sb.append(include + " ");
        }
        getLog().info("Includes: " + sb.toString());
        sb = new StringBuilder();
        for (String exclude : excludes) {
            sb.append(exclude + " ");
        }
        getLog().info("Excludes: " + sb.toString());
        List<File> dirs = getSourceDirectories();

        if (dirs.size() == 0) {
            getLog().info("No directories containing source code were located");
            return;
        } else {
            getLog().info("Located " + dirs.size() + " source directories:");
            for (File dir : dirs) {
                getLog().info(dir.getAbsolutePath());
            }
        }

        super.setExecutable(quote(eclipseBinary));
        super.setArguments(getEclipseArguments(dirs));

        super.execute();
    }

    protected String getJavaBinary() throws MojoExecutionException {
        if (!StringUtils.isEmpty(vm)) {
            return vm;
        }
        String javaHome = System.getProperty("java.home");
        String binaryHome = javaHome + FS + "bin";
        for (String binary : javaBinaries) {
            File file = new File(binaryHome + FS + binary);
            if (file.exists()) {
                return file.getAbsolutePath();
            }
        }
        throw new MojoExecutionException(
                "No Java VM location was supplied, and we could not locate one using the System property 'java.home'");
    }

    protected List<String> getEclipseArguments(List<File> dirs) throws MojoExecutionException {
        List<String> args = new ArrayList<String>();
        args.add("-application");
        args.add(quote(application));
        args.add("-vm");
        args.add(quote(getJavaBinary()));
        args.add("-config");
        args.add(quote(getConfigAbsolutePath()));
        for (String arg : eclipseArgs) {
            addIfNotEmpty(args, arg);
        }
        for (File dir : dirs) {
            args.add(quote(dir.getAbsolutePath()));
        }
        return args;
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

    public String getEclipseBinary() {
        return eclipseBinary;
    }

    public void setEclipseBinary(String eclipseExecutable) {
        this.eclipseBinary = eclipseExecutable;
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

    public String[] getJavaBinaries() {
        return javaBinaries;
    }

    public void setJavaBinaries(String[] binaries) {
        this.javaBinaries = binaries;
    }

    public String getFormatterPreferences() {
        return formatterPreferences;
    }

    public void setFormatterPreferences(String formatterPreferences) {
        this.formatterPreferences = formatterPreferences;
    }

    public String[] getEclipseArgs() {
        return eclipseArgs;
    }

    public void setEclipseArgs(String[] args) {
        this.eclipseArgs = args;
    }

}
