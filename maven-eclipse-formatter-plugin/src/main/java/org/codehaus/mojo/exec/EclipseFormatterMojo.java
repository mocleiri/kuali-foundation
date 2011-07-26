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
     * Full path to the Eclipse executable
     * 
     * @parameter expression="${eclipse.executable}"
     * @required
     */
    private String eclipseExecutable;

    /**
     * Full path to a Java vm
     * 
     * @parameter expression="${eclipse.vm}"
     * @required
     */
    private String vm;

    /**
     * 
     * @parameter expression="${eclipse.application}" default-value="org.eclipse.jdt.core.JavaCodeFormatter"
     * @required
     */
    private String application;

    /**
     * Path to the Eclipse "org.eclipse.jdt.core.prefs" file. Supports "classpath:" style notation
     * 
     * @parameter expression="${eclipse.configPath}" default-value="classpath:eclipse.prefs"
     * @required
     */
    private String configPath;

    /**
     * @parameter expression="${eclipse.nosplash}" default-value="-nosplash"
     */
    private String nosplash;

    /**
     * @parameter expression="${eclipse.verbose}" default-value="-verbose"
     */
    private String verbose;

    /**
     * @parameter
     */
    private String[] includes = new String[] { "**/src/main/java", "**/src/test/java" };

    /**
     * @parameter
     */
    private String[] excludes = new String[] {};

    protected List<File> getSourceDirectories() {
        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(project.getBasedir());
        scanner.setIncludes(includes);
        scanner.setExcludes(excludes);
        getLog().info("Scanning for source directories: " + project.getBasedir());
        scanner.scan();
        String[] includedDirs = scanner.getIncludedDirectories();
        List<File> dirs = new ArrayList<File>();
        for (String includedDir : includedDirs) {
            File file = new File(project.getBasedir().getAbsolutePath() + FS + includedDir);
            dirs.add(file);
        }
        getLog().info("Located " + dirs.size() + " source directories");
        return dirs;
    }

    @Override
    public void execute() throws MojoExecutionException {
        List<File> dirs = getSourceDirectories();

        if (dirs.size() == 0) {
            getLog().info("No directories containing source code were located");
            return;
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

    protected List<String> getEclipseArguments(List<File> dirs) throws MojoExecutionException {
        List<String> args = new ArrayList<String>();
        args.add("-application");
        args.add(quote(application));
        args.add("-vm");
        args.add(quote(vm));
        args.add("-config");
        args.add(quote(getConfigAbsolutePath()));
        addIfNotEmpty(args, nosplash);
        addIfNotEmpty(args, verbose);
        for (File dir : dirs) {
            args.add(quote(dir.getAbsolutePath()));
        }
        return args;
    }

    protected String quote(String s) {
        return '"' + s + '"';
    }

    protected String getConfigAbsolutePath() throws MojoExecutionException {
        File file = new File(configPath);
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource(configPath);
        if (!resource.exists()) {
            throw new MojoExecutionException("Unable to locate " + configPath);
        }
        OutputStream out = null;
        try {
            File temp = File.createTempFile("eclipse.prefs.", null);
            out = new FileOutputStream(temp);
            IOUtils.copy(resource.getInputStream(), out);
            return temp.getAbsolutePath();
        } catch (IOException e) {
            throw new MojoExecutionException("Error copying resource " + configPath, e);
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

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public String getNosplash() {
        return nosplash;
    }

    public void setNosplash(String nosplash) {
        this.nosplash = nosplash;
    }

    public String getVerbose() {
        return verbose;
    }

    public void setVerbose(String verbose) {
        this.verbose = verbose;
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

}
