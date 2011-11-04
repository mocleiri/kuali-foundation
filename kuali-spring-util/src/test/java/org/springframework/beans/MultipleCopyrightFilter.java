package org.springframework.beans;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class MultipleCopyrightFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            List<String> lines = IOUtils.readLines(in);
            return isMultipleCopyRights(lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(in);
        }

    }

    protected boolean isMultipleCopyRights(List<String> strings) {
        int count = 0;
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            String lowerCase = s.toLowerCase();
            int pos = lowerCase.indexOf("copyright");
            if (pos != -1) {
                count++;
            }
        }
        return count > 1;
    }

}
