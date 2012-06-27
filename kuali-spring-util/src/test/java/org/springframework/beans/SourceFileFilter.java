package org.springframework.beans;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SourceFileFilter implements FileFilter {
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
        try {
            String path = file.getCanonicalPath();
            if (path.contains(".x")) {
                return true;
            }
            for (String fileExtension : fileExtensions) {
                if (path.endsWith(fileExtension)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
