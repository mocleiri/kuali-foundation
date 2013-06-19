package org.springframework.beans;

import java.io.File;

public class MultipleCopyrightContext extends ProblemFileContext {

    public MultipleCopyrightContext(String basedir) {
        super();
        setBaseDir(new File(basedir));
        setInclude(new SourceFileFilter());
        setExclude(new MultipleCopyrightIgnoresFilter());
        setProblem(new MultipleCopyrightFilter());
    }

}
