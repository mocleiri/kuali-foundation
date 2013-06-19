package org.kuali.maven.plugins;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.handler.ArtifactHandler;
import org.apache.maven.artifact.handler.DefaultArtifactHandler;

public class DeployUtils {
    public static void main(String[] args) {
        try {
            String basedir = System.getProperty("user.home") + "/.oracle";
            DeployUtils du = new DeployUtils();
            List<Artifact> artifacts = du.getArtifacts(new File(basedir), "com.oracle", "11.2.0.2, 11.2.0.1");
            String s = du.getShellScript(artifacts);
            System.out.println(s);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void write(String filename, String content) throws IOException {
        OutputStream out = null;
        try {
            out = new FileOutputStream(filename);
            IOUtils.write(content, out);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    public String getShellScript(List<Artifact> artifacts) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("#!/bin/sh\n");
        for (Artifact artifact : artifacts) {
            sb.append(getCommandLine(artifact) + "\n");
        }
        return sb.toString();
    }

    protected String getCommandLine(Artifact artifact) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("mvn process-resources -Pprivate");
        sb.append(" -Ddp.groupId=" + artifact.getGroupId());
        sb.append(" -Ddp.version=" + artifact.getVersion());
        sb.append(" -Ddp.artifactId=" + artifact.getArtifactId());
        sb.append(" -Ddp.file=" + artifact.getFile().getCanonicalPath());
        return sb.toString();
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

    public List<Artifact> getArtifacts(File directory, String groupId, String versions) {
        List<File> files = getFiles(directory);
        List<Artifact> artifacts = new ArrayList<Artifact>();
        for (File file : files) {
            Artifact artifact = getArtifact(directory, file, groupId);
            artifacts.add(artifact);
        }
        trimArtifacts(artifacts, versions);
        Collections.sort(artifacts, new ArtifactComparator());
        return artifacts;
    }

    protected boolean isMatch(String s, String[] tokens) {
        for (String token : tokens) {
            if (s.trim().equals(token.trim())) {
                return true;
            }
        }
        return false;
    }

    protected void trimArtifacts(List<Artifact> artifacts, String includeVersions) {
        if (StringUtils.isEmpty(includeVersions)) {
            return;
        }
        String[] versions = includeVersions.split(",");
        Iterator<Artifact> itr = artifacts.iterator();
        while (itr.hasNext()) {
            Artifact artifact = itr.next();
            if (!isMatch(artifact.getVersion(), versions)) {
                itr.remove();
            }
        }
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
