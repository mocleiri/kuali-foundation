package org.kuali.maven.plugins.ecl;

import java.io.File;

import org.kuali.maven.plugins.ecl.filter.CommonIgnoresFilter;
import org.kuali.maven.plugins.ecl.filter.FilenameContainsDotXFilter;
import org.kuali.maven.plugins.ecl.filter.MisplacedXMLPrologFilter;

public class MisplacedXMLPrologContext extends ProblemFileContext {

    public MisplacedXMLPrologContext(String basedir) {
        super();
        setBaseDir(new File(basedir));
        setInclude(new FilenameContainsDotXFilter());
        setExclude(new CommonIgnoresFilter());
        setProblem(new MisplacedXMLPrologFilter());
    }

}
