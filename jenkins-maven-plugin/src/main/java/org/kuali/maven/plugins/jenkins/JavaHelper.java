package org.kuali.maven.plugins.jenkins;

public class JavaHelper {
    public static final String FS = System.getProperty("file.separator");
    public static final String JAVA_HOME_KEY = "java.home";
    public static final String DEFAULT_EXECUTABLE_NAME = "java";
    public static final String DEFAULT_PATH = "bin" + FS + DEFAULT_EXECUTABLE_NAME;

    public String getExecutable() {
        String javaHome = System.getProperty(JAVA_HOME_KEY);
        if (!javaHome.endsWith(FS)) {
            javaHome += FS;
        }
        String path = javaHome + DEFAULT_PATH;
        return null;
    }
}
