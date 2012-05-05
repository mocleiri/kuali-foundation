package org.codehaus.mojo.exec;

import static org.codehaus.plexus.util.StringUtils.isEmpty;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
     * Location on the file system that contains java source code.
     * 
     * @parameter expression="${eclipse.baseDirectory}" default-value="${project.basedir}"
     * @required
     */
    private String baseDirectory;

    /**
     * Regular expressions for directories that contain Java source code to format. Default value is
     * &#042;&#042;/src/main/java. The Eclipse formatter will recursively inspect any directories matching these
     * patterns for *.java files
     * 
     * @parameter
     */
    private String[] includes = new String[] { "**/src/main/java" };

    /**
     * Regular expressions for directories to exclude from the process that scans for *.java files
     * 
     * @parameter
     */
    private String[] excludes = new String[] { "**/.settings", "**/.svn" };

    protected List<File> getSourceDirectories() {
        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(baseDirectory);
        scanner.setIncludes(includes);
        scanner.setExcludes(excludes);
        scanner.scan();
        String[] includedDirs = scanner.getIncludedDirectories();
        List<File> dirs = new ArrayList<File>();
        for (String includedDir : includedDirs) {
            File file = new File(scanner.getBasedir().getAbsolutePath() + FS + includedDir);
            dirs.add(file);
        }
        return dirs;
    }

    protected void showDirs(List<File> srcDirs) {
        getLog().info("Located " + srcDirs.size() + " source directories:");
        for (File dir : srcDirs) {
            getLog().info(dir.getAbsolutePath());
        }
    }

    protected void showConfig(File eclipseBinary, File javaBinary) {
        getLog().info("Eclipse Location: " + eclipseBinary.getAbsolutePath());
        getLog().info("Java VM: " + javaBinary.getAbsolutePath());
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
    }

    @Override
    public void execute() throws MojoExecutionException {
        if (!fileExists(eclipseBinary)) {
            throw new MojoExecutionException(eclipseBinary + " does not exist");
        }
        File eclipseBinaryFile = new File(eclipseBinary);
        File javaBinary = getJavaBinary();
        showConfig(eclipseBinaryFile, javaBinary);

        List<File> dirs = getSourceDirectories();
        if (dirs.size() == 0) {
            getLog().info("No directories containing source code were located");
            return;
        }
        showDirs(dirs);

        super.setExecutable(eclipseBinaryFile.getAbsolutePath());
        super.setArguments(getEclipseArguments(javaBinary, dirs));

        super.execute();
    }

    protected boolean fileExists(String filename) {
        return new File(filename).exists();
    }

    protected File getJavaBinary() throws MojoExecutionException {
        // They provided us with a VM, and we found a file in the location they specified
        if (!isEmpty(vm) && fileExists(vm)) {
            return new File(vm);
        }

        // They asked us to use a specific VM, but it doesn't exist
        if (!isEmpty(vm) && !fileExists(vm)) {
            throw new MojoExecutionException(vm + " does not exist");
        }

        // They did not specify a VM, attempt to locate one
        String javaHome = System.getProperty("java.home");
        String binaryHome = javaHome + FS + "bin";
        for (String binary : javaBinaries) {
            File file = new File(binaryHome + FS + binary);
            if (file.exists()) {
                return file;
            }
        }
        throw new MojoExecutionException(
                "No Java VM location was supplied, and we could not locate one using the System property 'java.home'");
    }

    protected List<String> getEclipseArguments(File javaBinary, List<File> dirs) throws MojoExecutionException {
        List<String> args = new ArrayList<String>();
        args.add("-application");
        args.add(application);
        args.add("-vm");
        args.add(javaBinary.getAbsolutePath());
        args.add("-config");
        args.add(getConfigAbsolutePath());
        for (String arg : eclipseArgs) {
            addIfNotEmpty(args, arg);
        }
        for (File dir : dirs) {
            args.add(dir.getAbsolutePath());
        }
        return args;
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
        InputStream in = null;
        try {
            File temp = File.createTempFile("eclipse.prefs.", null);
            temp.deleteOnExit();
            out = new FileOutputStream(temp);
            in = resource.getInputStream();
            IOUtils.copy(in, out);
            return temp.getAbsolutePath();
        } catch (IOException e) {
            throw new MojoExecutionException("Error copying resource " + formatterPreferences, e);
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(in);
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

    public String getBaseDirectory() {
        return baseDirectory;
    }

    public void setBaseDirectory(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

}
