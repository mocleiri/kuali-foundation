package org.springframework.beans;

import java.io.File;
import java.io.FileFilter;

public class ProblemFileContext {

    File baseDir;
    FileFilter include;
    FileFilter exclude;
    FileFilter problem;

    public ProblemFileContext() {
        this(null, null, null, null);
    }

    public ProblemFileContext(File baseDir, FileFilter include, FileFilter exclude, FileFilter problem) {
        super();
        this.baseDir = baseDir;
        this.include = include;
        this.exclude = exclude;
        this.problem = problem;
    }

    public File getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(File baseDir) {
        this.baseDir = baseDir;
    }

    public FileFilter getInclude() {
        return include;
    }

    public void setInclude(FileFilter include) {
        this.include = include;
    }

    public FileFilter getExclude() {
        return exclude;
    }

    public void setExclude(FileFilter exclude) {
        this.exclude = exclude;
    }

    public FileFilter getProblem() {
        return problem;
    }

    public void setProblem(FileFilter problem) {
        this.problem = problem;
    }
}
