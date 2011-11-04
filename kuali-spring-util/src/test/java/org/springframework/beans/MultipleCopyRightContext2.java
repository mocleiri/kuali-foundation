package org.springframework.beans;

import java.io.File;

public class MultipleCopyRightContext2 extends ProblemFileContext {

    public MultipleCopyRightContext2(String basedir) {
        super();
        setBaseDir(new File(basedir));
        setInclude(new SourceFileFilter());
        setExclude(new CommonIgnoresFilter());
        setProblem(new MultipleCopyrightFilter());
    }

}
