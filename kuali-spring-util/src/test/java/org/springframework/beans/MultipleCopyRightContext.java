package org.springframework.beans;

import java.io.File;

public class MultipleCopyRightContext extends ProblemFileContext {

    public MultipleCopyRightContext(String basedir) {
        super();
        setBaseDir(new File(basedir));
        setInclude(new SourceFileFilter());
        setExclude(new CommonIgnoresFilter());
        setProblem(new MultipleCopyrightFilter());
    }

}
