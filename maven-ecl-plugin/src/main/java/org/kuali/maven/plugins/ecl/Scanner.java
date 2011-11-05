package org.kuali.maven.plugins.ecl;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kuali.maven.plugins.ecl.filter.IncludeExcludeFilter;

public class Scanner {
    public List<File> getProblemFiles(ProblemFileContext context) throws IOException {
        List<File> files = getFiles(context.getBaseDir(), context.getInclude(), context.getExclude());
        System.out.println("Located " + files.size() + " total files");
        List<File> problemFiles = getFiles(files, context.getProblem());
        return problemFiles;
    }

    public List<File> getFiles(List<File> files, FileFilter filter) throws IOException {
        List<File> newFileList = new ArrayList<File>();
        for (File file : files) {
            if (filter.accept(file)) {
                newFileList.add(file);
            }
        }
        return newFileList;
    }

    public List<File> getFiles(File dir, FileFilter include, FileFilter exclude) {
        FileFilter filter = new IncludeExcludeFilter(include, exclude);
        return getFiles(dir, filter);
    }

    public List<File> getFiles(File dir, FileFilter filter) {
        File[] contents = dir.listFiles(filter);
        List<File> files = new ArrayList<File>();
        for (File file : contents) {
            if (file.isDirectory()) {
                files.addAll(getFiles(file, filter));
            } else {
                files.add(file);
            }
        }
        return files;
    }
}
