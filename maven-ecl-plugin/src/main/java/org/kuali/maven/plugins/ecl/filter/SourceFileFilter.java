package org.kuali.maven.plugins.ecl.filter;


public class SourceFileFilter extends XMLRelatedFilter {

    public SourceFileFilter() {
        super();
        extensions.add(".pom");
        extensions.add(".html");
        extensions.add(".htm");
        extensions.add(".jspx");
        extensions.add(".dtd");
        extensions.add(".fml");
        extensions.add(".php");
        extensions.add(".vm");
        extensions.add(".tld");
        extensions.add(".jsp");
        extensions.add(".js");
        extensions.add(".css");
        extensions.add(".sql");
        extensions.add(".groovy");
        extensions.add(".properties");
        extensions.add(".java");
        extensions.add(".txt");
    }

}
