package org.kuali.maven.plugins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DeployableUtils {
    public static void main(String[] args) {
        try {
            String basedir = System.getProperty("user.home") + "/.oracle";
            File directory = new File(basedir);
            DeployableUtils du = new DeployableUtils();
            List<Deployable> deployables = du.getDeployables(directory, "com.oracle");
            for (Deployable deployable : deployables) {
                System.out.println(du.getGAVString(deployable.getGav()));
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public String getGAVString(GAV gav) {
        StringBuilder sb = new StringBuilder();
        sb.append(gav.getGroupId());
        sb.append(":");
        sb.append(gav.getArtifactId());
        sb.append(":");
        sb.append(gav.getVersion());
        return sb.toString();
    }

    public List<Deployable> getDeployables(File directory, String groupId) {
        List<File> files = getFiles(directory);
        List<Deployable> deployables = new ArrayList<Deployable>();
        for (File file : files) {
            Deployable deployable = new Deployable();
            GAV gav = getGAV(directory, file, groupId);
            deployable.setFile(file);
            deployable.setGav(gav);
            deployables.add(deployable);
        }
        return deployables;
    }

    protected GAV getGAV(File baseDirectory, File file, String groupId) {
        String artifactId = getArtifactId(file);
        String version = getVersion(baseDirectory, file);
        GAV gav = new GAV();
        gav.setGroupId(groupId);
        gav.setArtifactId(artifactId);
        gav.setVersion(version);
        return gav;
    }

    protected String getVersion(File baseDirectory, File file) {
        String path1 = baseDirectory.getAbsolutePath();
        String path2 = file.getAbsolutePath();
        int pos = path2.indexOf(path1) + path1.length();
        String version = path2.substring(pos);
        String filename = file.getName();
        pos = version.indexOf(filename);
        version = version.substring(0, pos);
        String fileSeparator = System.getProperty("file.separator");
        version = version.replace(fileSeparator, "");
        return version;
    }

    protected String getArtifactId(File file) {
        String filename = file.getName();
        int pos = filename.indexOf(".");
        return filename.substring(0, pos);
    }

    public List<File> getFiles(File directory) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory + " is not a directory");
        }
        File[] fileList = directory.listFiles();
        List<File> files = new ArrayList<File>();
        for (File file : fileList) {
            if (file.isDirectory()) {
                files.addAll(getFiles(file));
            } else {
                files.add(file);
            }
        }
        return files;
    }

}
