package org.kuali.maven.plugins.jenkins.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RsyncHelper {

    /**
     * Recursively scan the file system starting at <code>dir</code> and return a list of directories matching the
     * pattern passed in
     *
     * @param dir
     * @param pattern
     * @param filter
     * @return
     */
    public List<File> getMatchingDirs(File dir, String pattern, DirectoryFileFilter filter) {
        List<File> fileList = new ArrayList<File>();
        String path = dir.getAbsolutePath();
        int pos = path.indexOf(pattern);
        if (pos == -1) {
            File[] files = dir.listFiles(filter);
            for (File file : files) {
                fileList.addAll(getMatchingDirs(file, pattern, filter));
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
            String token = path.substring(basedirPath.length() + 1);
            excludes.add(token);
        }
        return excludes;
    }

}
