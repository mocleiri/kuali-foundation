package org.kuali.maven.plugins.ecl.filter;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

public abstract class CheckLinesFilter implements FileFilter {

    protected abstract boolean checkLines(List<String> lines);

    @Override
    public boolean accept(File file) {
        List<String> lines = getLines(file);
        return checkLines(lines);
    }

    public List<String> getLines(File file) {
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

}
