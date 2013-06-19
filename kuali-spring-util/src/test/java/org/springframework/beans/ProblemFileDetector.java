package org.springframework.beans;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProblemFileDetector {
    public List<File> getProblemFiles(ProblemFileContext context) throws IOException {
        File baseDir = context.getBaseDir();
        FileFilter exclude = context.getExclude();
        FileFilter include = context.getInclude();
        FileFilter problem = context.getProblem();
        List<File> files = getFiles(baseDir, exclude, include);
        System.out.println("Located " + files.size() + " total files");
        List<File> problemFiles = getProblemFiles(files, problem);
        return problemFiles;
    }

    protected List<File> getProblemFiles(List<File> files, FileFilter problem) throws IOException {
        List<File> problemFiles = new ArrayList<File>();
        for (File file : files) {
            if (problem.accept(file)) {
                problemFiles.add(file);
            }
        }
        return problemFiles;
    }

    protected List<File> getFiles(File dir, FileFilter exclude, FileFilter include) {
        File[] contents = dir.listFiles();
        List<File> files = new ArrayList<File>();
        for (File file : contents) {
            if (!exclude.accept(file)) {
                continue;
            }
            if (file.isDirectory()) {
                files.addAll(getFiles(file, exclude, include));
            } else {
                if (include.accept(file)) {
                    files.add(file);
                }
            }
        }
        return files;
    }
}
