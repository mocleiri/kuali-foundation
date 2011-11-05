package org.kuali.maven.plugins.ecl.filter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SourceFileFilter extends FilenameContainsDotXFilter {
    List<String> fileExtensions = new ArrayList<String>();

    public SourceFileFilter() {
        super();
        fileExtensions.add(".pom");
        fileExtensions.add(".html");
        fileExtensions.add(".htm");
        fileExtensions.add(".jspx");
        fileExtensions.add(".mxml");
        fileExtensions.add(".dtd");
        fileExtensions.add(".fml");
        fileExtensions.add(".php");
        fileExtensions.add(".vm");
        fileExtensions.add(".tld");
        fileExtensions.add(".jsp");
        fileExtensions.add(".js");
        fileExtensions.add(".css");
        fileExtensions.add(".sql");
        fileExtensions.add(".groovy");
        fileExtensions.add(".properties");
        fileExtensions.add(".java");
        fileExtensions.add(".txt");
    }

    @Override
    public boolean accept(File file) {
        if (super.accept(file)) {
            return true;
        } else {
            String path = getPath(file);
            for (String fileExtension : fileExtensions) {
                if (path.endsWith(fileExtension)) {
                    return true;
                }
            }
            return false;
        }
    }

    protected String getPath(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
