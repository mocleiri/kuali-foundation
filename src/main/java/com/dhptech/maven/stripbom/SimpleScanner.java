/**
 * Copyright 2011-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dhptech.maven.stripbom;

import static java.util.Arrays.asList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.plexus.util.DirectoryScanner;

/**
 * Logic for recursively scanning a base directory for files that match include/exclude patterns
 */
public final class SimpleScanner {

    private final File directory;
    private final String[] included;
    private final String[] excluded;

    private DirectoryScanner scanner;

    public SimpleScanner(File directory, String[] included, String[] excluded, boolean useDefaultExcludes) {
        this.directory = directory;
        this.included = buildInclusions(included);
        this.excluded = buildExclusions(useDefaultExcludes, excluded);
    }

    public String[] getSelectedFiles() {
        scanIfNeeded();
        return scanner.getIncludedFiles();
    }

    public File getDirectory() {
        return directory;
    }

    public String[] getIncluded() {
        return included;
    }

    public String[] getExcluded() {
        return excluded;
    }

    protected synchronized void scanIfNeeded() {
        if (scanner == null) {
            scanner = new DirectoryScanner();
            scanner.setBasedir(directory);
            scanner.setIncludes(included);
            scanner.setExcludes(excluded);
            scanner.scan();
        }
    }

    protected static String[] buildExclusions(boolean useDefaultExcludes, String... excludes) {
        List<String> exclusions = new ArrayList<String>();
        if (useDefaultExcludes) {
            exclusions.addAll(asList(DEFAULT_EXCLUDES));
        }
        if (excludes != null && excludes.length > 0) {
            exclusions.addAll(asList(excludes));
        }
        return exclusions.toArray(new String[exclusions.size()]);
    }

    protected static String[] buildInclusions(String... includes) {
        return includes != null && includes.length > 0 ? includes : DEFAULT_INCLUDE;
    }

    static final String[] DEFAULT_INCLUDE = new String[] { "**" };

    static final String[] DEFAULT_EXCLUDES = {
            // Miscellaneous typical temporary files
            "**/*~", "**/#*#", "**/.#*",
            "**/%*%",
            "**/._*",
            "**/.repository/**",

            // CVS
            "**/CVS",
            "**/CVS/**",
            "**/.cvsignore",

            // RCS
            "**/RCS",
            "**/RCS/**",

            // SCCS
            "**/SCCS",
            "**/SCCS/**",

            // Visual SourceSafe
            "**/vssver.scc",

            // Subversion
            "**/.svn",
            "**/.svn/**",

            // Arch
            "**/.arch-ids",
            "**/.arch-ids/**",

            // Bazaar
            "**/.bzr",
            "**/.bzr/**",

            // SurroundSCM
            "**/.MySCMServerInfo",

            // Mac
            "**/.DS_Store",

            // Serena Dimensions Version 10
            "**/.metadata",
            "**/.metadata/**",

            // Mercurial
            "**/.hg",
            "**/.hg/**",

            // git
            "**/.git",
            "**/.git/**",

            // BitKeeper
            "**/BitKeeper", "**/BitKeeper/**", "**/ChangeSet",
            "**/ChangeSet/**",

            // darcs
            "**/_darcs", "**/_darcs/**", "**/.darcsrepo", "**/.darcsrepo/**", "**/-darcs-backup*",
            "**/.darcs-temp-mail",

            // maven project's temporary files
            "**/target/**", "**/test-output/**", "**/release.properties", "**/dependency-reduced-pom.xml",

            // code coverage tools
            "**/cobertura.ser", "**/.clover/**",

            // eclipse project files
            "**/.classpath", "**/.project", "**/.settings/**",

            // IDEA project files
            "**/*.iml", "**/*.ipr", "**/*.iws",

            // descriptors
            "**/MANIFEST.MF",

            // binary files - images
            "**/*.jpg", "**/*.png", "**/*.gif", "**/*.ico", "**/*.bmp", "**/*.tiff", "**/*.tif", "**/*.cr2",
            "**/*.xcf",

            // binary files - programs
            "**/*.class", "**/*.exe",

            // checksum files
            "**/*.md5", "**/*.sha1",

            // binary files - archives
            "**/*.jar", "**/*.war", "**/*.zip", "**/*.rar", "**/*.tar", "**/*.tar.gz", "**/*.tar.bz2", "**/*.gz",

            // binary files - documents
            "**/*.xls",

            // ServiceLoader files
            "**/META-INF/services/**" };

}