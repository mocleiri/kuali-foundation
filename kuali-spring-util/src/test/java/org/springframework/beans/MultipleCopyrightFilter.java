package org.springframework.beans;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class MultipleCopyrightFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        try {
            List<String> lines = IOUtils.readLines(new FileInputStream(file));
            return isMultipleCopyRights(lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
