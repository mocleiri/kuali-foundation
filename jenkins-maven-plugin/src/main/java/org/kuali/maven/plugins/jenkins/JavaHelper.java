package org.kuali.maven.plugins.jenkins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaHelper {
    public static final String FS = System.getProperty("file.separator");
    public static final String JAVA_HOME_KEY = "java.home";
    public static final String EXECUTABLE = "java";
    public static final String[] EXECUTABLES = { EXECUTABLE, "javaw" };
    public static final String PATH = "bin";
    public static final String[] EXTENSIONS = { "", ".exe" };

    public String getExecutable() {
        return getExecutable(EXECUTABLES, EXTENSIONS);
    }

    public String getExecutable(String[] executables, String[] extensions) {
        List<String> paths = getPotentialPaths(executables, extensions);
        for (String path : paths) {
            if (isUsable(path)) {
                return path;
            }
        }
        return EXECUTABLE;
    }

    protected boolean isBlank(String[] array) {
        return array == null || array.length == 0;
    }

    protected boolean isUsable(String path) {
        File file = new File(path);
        return file.exists() && file.canExecute();
    }

    protected List<String> getPotentialPaths(String[] executables, String[] extensions) {
        String javaHome = System.getProperty(JAVA_HOME_KEY);
        if (!javaHome.endsWith(FS)) {
            javaHome += FS;
        }
        String dir = javaHome + PATH + FS;
        List<String> paths = new ArrayList<String>();
        for (String executable : executables) {
            for (String extension : extensions) {
                String path = dir + executable + extension;
                paths.add(path);
            }
        }
        return paths;
    }
}
