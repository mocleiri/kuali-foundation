package org.springframework.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class ContentRemover {

    public void removeContent(File file, List<String> contentsToRemove) {
        String content = getExistingContent(file);
        for (int i = 0; i < contentsToRemove.size(); i++) {
            String contentToRemove = contentsToRemove.get(i);
            int pos = content.indexOf(contentToRemove);
            if (pos != -1) {
                System.out.println("Matched " + i + " replacing");
                content = content.replace(contentToRemove, "");
            }
        }
        write(file, content);
    }

    protected void write(File file, String content) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            IOUtils.write(content.getBytes(), out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    protected String getExistingContent(File file) {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            return IOUtils.toString(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}
