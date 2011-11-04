package org.springframework.beans;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProblemFileDetector {

    public static void main(String[] args) {
        new ProblemFileDetector().exec(args);
    }

    protected void exec(String[] args) {
        try {
            long beg = System.currentTimeMillis();
            String dir = "c:/eclipse/sts/2.6.1/ide/ws/rice-trunk";
            FileFilter exclude = new CommonIgnoresFilter();
            FileFilter include = new FilenameContainsDotXFilter();
            FileFilter problem = new MisplacedXMLPrologFilter();
            List<File> files = getFiles(new File(dir), exclude, include);
            System.out.println(files.size());
            List<File> problemFiles = getProblemFiles(files, problem);
            System.out.println(problemFiles.size());
            for (int i = 0; i < problemFiles.size(); i++) {
                File problemFile = problemFiles.get(i);
                System.out.println(problemFile.getAbsolutePath());
            }
            long end = System.currentTimeMillis();
            long elapsed = end - beg;
            double seconds = elapsed / 1000D;
            System.out.println(seconds);
        } catch (Throwable t) {
            t.printStackTrace();
        }
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
            if (exclude.accept(file)) {
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
