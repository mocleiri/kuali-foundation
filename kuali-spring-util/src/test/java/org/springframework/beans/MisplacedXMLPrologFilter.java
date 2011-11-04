package org.springframework.beans;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class MisplacedXMLPrologFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        try {
            List<String> lines = IOUtils.readLines(new FileInputStream(file));
            int prologIndex = getPrologIndex(lines);
            if (prologIndex != -1 && prologIndex != 0) {
                return true;
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    protected boolean isProlog(String s) {
        String lowerCase = s.toLowerCase();
        int pos1 = lowerCase.indexOf("<?xml");
        int pos2 = lowerCase.indexOf("?>");
        boolean flag1 = pos1 != -1 && pos2 != -1;
        boolean flag2 = pos2 > pos1;
        return flag1 && flag2;
    }

    protected int getPrologIndex(List<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i).trim();
            if (isProlog(s)) {
                return i;
            }
        }
        return -1;
    }

}
