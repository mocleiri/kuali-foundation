package org.codehaus.mojo.exec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ExecEclipseMojo extends ExecMojo {
    private static final String FS = System.getProperty("file.separator");

    // C:/eclipse/3.6.2/r11/eclipse/eclipse.exe
    // -nosplash
    // -vm "C:/Program Files/Java/jdk1.6.0_21/bin/java.exe"
    // -application org.eclipse.jdt.core.JavaCodeFormatter
    // -verbose
    // -config C:/eclipse/3.6.2/r11/eclipse/ws/maven-scm-plugin/.settings/org.eclipse.jdt.core.prefs
    // C:/eclipse/3.6.2/r11/eclipse/ws/maven-eclipse-formatter-plugin/src/main/java/org/codehaus/mojo/exec

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
     * @parameter expression="${eclipse.configPath}"
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
     * @parameter expression="${eclipse.formatSource}" default-value="true"
     */
    private boolean formatSource;

    /**
     * @parameter expression="${eclipse.formatTestSource}" default-value="true"
     */
    private boolean formatTestSource;

    @Override
    public void execute() throws MojoExecutionException {
        super.setExecutable(eclipseExecutable);
        super.setArguments(getEclipseArguments());

        super.execute();
    }

    protected String getAbsolutePath(String relativeDir) {
        File basedir = project.getBasedir();
        String path = basedir.getAbsolutePath();
        if ((!path.endsWith(FS)) && (!relativeDir.startsWith(FS))) {
            path += FS;
        }
        return path + relativeDir;
    }

    protected String getAbsolutePathSourceDirectory() {
        return getAbsolutePath(project.getBuild().getSourceDirectory());
    }

    protected String getAbsolutePathTestSourceDirectory() {
        return getAbsolutePath(project.getBuild().getTestSourceDirectory());
    }

    protected List<String> getEclipseArguments() throws MojoExecutionException {
        List<String> args = new ArrayList<String>();
        args.add("-application");
        args.add(application);
        args.add("-vm");
        args.add(vm);
        args.add("-config");
        args.add(getConfigAbsolutePath());
        addIfNotEmpty(args, nosplash);
        addIfNotEmpty(args, verbose);
        if (formatSource) {
            args.add(getAbsolutePathSourceDirectory());
        }
        if (formatTestSource) {
            args.add(getAbsolutePathTestSourceDirectory());
        }
        return args;
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
            File temp = File.createTempFile("eclipse", "prefs");
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

    public boolean isFormatSource() {
        return formatSource;
    }

    public void setFormatSource(boolean formatSource) {
        this.formatSource = formatSource;
    }

    public boolean isFormatTestSource() {
        return formatTestSource;
    }

    public void setFormatTestSource(boolean formatTestSource) {
        this.formatTestSource = formatTestSource;
    }

}
