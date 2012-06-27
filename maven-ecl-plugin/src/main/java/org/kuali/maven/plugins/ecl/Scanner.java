package org.kuali.maven.plugins.ecl;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kuali.maven.plugins.ecl.filter.IncludeExcludeFilter;

public class Scanner {
    public List<File> getProblemFiles(ProblemFileContext context) throws IOException {
        List<File> files = getRecursiveFileList(context.getBaseDir(), context.getInclude(), context.getExclude());
        System.out.println("Located " + files.size() + " total files");
        List<File> problemFiles = getFiles(files, context.getProblem());
        return problemFiles;
    }

    public List<File> getFiles(List<File> files, FileFilter filter) throws IOException {
        List<File> acceptedFiles = new ArrayList<File>();
        for (File file : files) {
            if (filter.accept(file)) {
                acceptedFiles.add(file);
            }
        }
        return acceptedFiles;
    }

    public List<File> getRecursiveFileList(File dir, FileFilter include, FileFilter exclude) {
        FileFilter filter = new IncludeExcludeFilter(include, exclude);
        return getRecusiveFileList(dir, filter);
    }

    public List<File> getRecusiveFileList(File dir, FileFilter filter) {
        File[] contents = dir.listFiles();
        List<File> files = new ArrayList<File>();
        for (File file : contents) {
            if (file.isDirectory()) {
                files.addAll(getRecusiveFileList(file, filter));
            } else {
                if (filter.accept(file)) {
                    files.add(file);
                }
            }
        }
        return files;
    }
}
