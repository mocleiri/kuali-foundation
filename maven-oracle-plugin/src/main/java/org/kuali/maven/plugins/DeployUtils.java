package org.kuali.maven.plugins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.handler.ArtifactHandler;
import org.apache.maven.artifact.handler.DefaultArtifactHandler;

public class DeployUtils {
    public static void main(String[] args) {
        try {
            String basedir = System.getProperty("user.home") + "/.oracle";
            File directory = new File(basedir);
            DeployUtils du = new DeployUtils();
            List<Artifact> artifacts = du.getArtifacts(directory, "com.oracle");
            for (Artifact artifact : artifacts) {
                System.out.println(artifact);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public String getGAVString(Artifact artifact) {
        StringBuilder sb = new StringBuilder();
        sb.append(artifact.getGroupId());
        sb.append(":");
        sb.append(artifact.getArtifactId());
        sb.append(":");
        sb.append(artifact.getVersion());
        return sb.toString();
    }

    public List<Artifact> getArtifacts(File directory, String groupId) {
        List<File> files = getFiles(directory);
        List<Artifact> artifacts = new ArrayList<Artifact>();
        for (File file : files) {
            Artifact artifact = getArtifact(directory, file, groupId);
            artifacts.add(artifact);
        }
        return artifacts;
    }

    protected Artifact getArtifact(File baseDirectory, File file, String groupId) {
        ArtifactHandler handler = new DefaultArtifactHandler("jar");
        String artifactId = getArtifactId(file);
        String version = getVersion(baseDirectory, file);
        Artifact artifact = new DefaultArtifact(groupId, artifactId, version, null, "jar", null, handler);
        artifact.setFile(file);
        return artifact;
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
