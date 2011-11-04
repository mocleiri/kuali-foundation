package org.springframework.beans;

import java.io.File;
import java.util.List;

public class ContentRemover {

    public boolean removeContent(File file, List<String> contentsToRemove) {
        String content = CopyrightHandler.read(file);
        content = CopyrightHandler.flatten(content);

        boolean updated = false;
        for (int i = 0; i < contentsToRemove.size(); i++) {
            String contentToRemove = contentsToRemove.get(i);
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
