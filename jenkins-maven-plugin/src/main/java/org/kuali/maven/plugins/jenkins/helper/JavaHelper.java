package org.kuali.maven.plugins.jenkins.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.maven.plugins.jenkins.context.ProcessResult;

/**
 * Locate the full path to the java executable being used by the currently running JVM or just return "java" if unable
 * to locate it
 */
public class JavaHelper {
    ProcessHelper helper = new ProcessHelper();

    public static final String FS = System.getProperty("file.separator");
    public static final String JAVA_HOME_KEY = "java.home";
    public static final String JAVA = "java";
    public static final String[] EXECUTABLES = { JAVA, "javaw" };
    public static final String BIN = "bin";
    public static final String[] EXTENSIONS = { "", ".exe" };
    public static final String EXECUTE_JAR_ARG = "-jar";

    public ProcessResult executeJar(File jar, String input, String... args) {
        String executable = getExecutable();
        String[] jarArgs = getExecuteJarArgs(jar, args);
        return helper.execute(executable, jarArgs, input);
    }

    protected String[] getExecuteJarArgs(File jar, String... args) {
        List<String> list = new ArrayList<String>();
        list.add(EXECUTE_JAR_ARG);
        list.add(jar.getAbsolutePath());
        addArgs(list, args);
        return list.toArray(new String[list.size()]);
    }

    protected void addArgs(List<String> list, String... args) {
        if (args == null || args.length == 0) {
            return;
        }
        for (String arg : args) {
            list.add(arg);
        }
    }

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
        return JAVA;
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
        if (StringUtils.isBlank(javaHome)) {
            return new ArrayList<String>();
        }
        if (!javaHome.endsWith(FS)) {
            javaHome += FS;
        }
        String dir = javaHome + BIN + FS;
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
