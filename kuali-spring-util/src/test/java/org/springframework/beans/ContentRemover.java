package org.springframework.beans;

import java.io.File;
import java.util.Set;

public class ContentRemover {

    public boolean removeContent(File file, Set<String> contentsToRemove) {
        String content = CopyrightHandler.read(file);
        content = CopyrightHandler.flatten(content);

        boolean updated = false;
        for (String contentToRemove : contentsToRemove) {
            int pos = content.indexOf(contentToRemove);
            if (pos != -1) {
                content = content.replace(contentToRemove, "");
                updated = true;
            }
        }
        if (!updated) {
            return false;
        } else {
            content = CopyrightHandler.expand(content);
            CopyrightHandler.write(file, content);
            return true;
        }
    }

}
