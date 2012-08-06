package org.codehaus.mojo.properties;

import java.io.File;
import java.util.List;
import java.util.Map;

public class DD {
    File dir;
    List<File> files;
    Map<String, File> fileMap;

    public File getDir() {
        return dir;
    }

    public void setDir(File dir) {
        this.dir = dir;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Map<String, File> getFileMap() {
        return fileMap;
    }

    public void setFileMap(Map<String, File> fileMap) {
        this.fileMap = fileMap;
    }

}
