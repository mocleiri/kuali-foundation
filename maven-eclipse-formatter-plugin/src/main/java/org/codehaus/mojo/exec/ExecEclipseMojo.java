package org.codehaus.mojo.exec;

public class ExecEclipseMojo extends ExecMojo {

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
     * @parameter expression="${eclipse.application}"
     *            default-value="-application org.eclipse.jdt.core.JavaCodeFormatter"
     * @required
     */
    private String application;

    /**
     * Full path to the Eclipse "org.eclipse.jdt.core.prefs" file
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

}
