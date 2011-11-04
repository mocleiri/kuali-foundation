package org.springframework.beans;

import java.io.File;

public class MisplacedXMLPrologContext extends ProblemFileContext {

    public MisplacedXMLPrologContext(String basedir) {
        super();
        setBaseDir(new File(basedir));
        setInclude(new FilenameContainsDotXFilter());
        setExclude(new CommonIgnoresFilter());
        setProblem(new MisplacedXMLPrologFilter());
    }

}
