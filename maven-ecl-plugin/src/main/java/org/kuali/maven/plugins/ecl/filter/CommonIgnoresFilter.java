package org.kuali.maven.plugins.ecl.filter;


public class CommonIgnoresFilter extends PathContainsExcludeFilter {

    public CommonIgnoresFilter() {
        super();
        excludes.add("/.svn/");
        excludes.add("/target/");
        excludes.add("/config/ide/");
        excludes.add("/db/impex/");
        excludes.add("/it/");
    }
}
