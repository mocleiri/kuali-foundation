package org.kuali.maven.plugins.ecl.filter;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class MisplacedXMLPrologFilter implements FileFilter {

    @Override
    public boolean accept(File file) {
        List<String> lines = getLines(file);
        int prologIndex = getPrologIndex(lines);
        return prologIndex > 0;
    }

    protected List<String> getLines(File file) {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            return IOUtils.readLines(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    protected boolean isProlog(String line) {
        String lowerCase = line.toLowerCase();
        int pos1 = lowerCase.indexOf("<?xml");
        int pos2 = lowerCase.indexOf("?>");
        boolean flag1 = pos1 != -1 && pos2 != -1;
        boolean flag2 = pos2 > pos1;
        return flag1 && flag2;
    }

    protected int getPrologIndex(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (isProlog(line)) {
                return i;
            }
        }
        return -1;
    }

}
