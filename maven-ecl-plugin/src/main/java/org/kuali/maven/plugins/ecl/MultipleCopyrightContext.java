package org.kuali.maven.plugins.ecl;

import java.io.File;

import org.kuali.maven.plugins.ecl.filter.MultipleCopyrightFilter;
import org.kuali.maven.plugins.ecl.filter.MultipleCopyrightIgnoresFilter;
import org.kuali.maven.plugins.ecl.filter.SourceFileFilter;

public class MultipleCopyrightContext extends ProblemFileContext {

    public MultipleCopyrightContext(String basedir) {
        super();
        setBaseDir(new File(basedir));
        setInclude(new SourceFileFilter());
        setExclude(new MultipleCopyrightIgnoresFilter());
        setProblem(new MultipleCopyrightFilter());
    }

}
