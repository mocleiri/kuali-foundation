package org.kuali.maven.plugins.jenkins.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RsyncHelper {
    private static final Logger logger = LoggerFactory.getLogger(RsyncHelper.class);

    /**
     * Recursively scan the file system starting at <code>dir</code> and return a list of directories matching the
     * pattern passed in
     *
     * @param dir
     * @param pattern
     * @param filter
     * @return
     */
    public List<File> getMatchingDirs(File basedir, File dir, String pattern, DirectoryFileFilter filter) {
        List<File> fileList = new ArrayList<File>();
        String basedirPath = basedir.getAbsolutePath();
        String path = dir.getAbsolutePath();
        int pos = path.indexOf(pattern, basedirPath.length());
        if (pos == -1) {
            File[] dirs = dir.listFiles(filter);
            for (File newDir : dirs) {
                fileList.addAll(getMatchingDirs(basedir, newDir, pattern, filter));
            }
        } else {
            fileList.add(dir);
        }
        return fileList;
    }

    /**
     * Return a list of rsync friendly exclude patterns based on a base directory and directories underneath it that
     * should be excluded.
     *
     * @param basedir
     * @param dirs
     * @param pattern
     * @return
     */
    public List<String> getExcludesList(File basedir, List<File> excludeDirs) {
        String basedirPath = basedir.getAbsolutePath();
        List<String> excludes = new ArrayList<String>();
        for (File excludeDir : excludeDirs) {
            String path = excludeDir.getAbsolutePath();
            logger.info("path=" + path);
            if (excludeDir.equals(basedir)) {
                continue;
            }
            logger.info("basedirPath=" + basedirPath);
            String token = path.substring(basedirPath.length() + 1);
            excludes.add(token);
        }
        return excludes;
    }

}
