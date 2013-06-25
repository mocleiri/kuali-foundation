package org.kuali.maven.plugins.krad.theme;

import com.samaxes.maven.minify.common.FilenameComparator;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.DirectoryScanner;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
@Mojo(name = "build", defaultPhase = LifecyclePhase.PROCESS_RESOURCES, threadSafe = false)
public class ThemeBuildMojo extends AbstractMojo {

    @Parameter(property = "webappSourceDirectory")
    private String webappSourceDir;

    @Parameter(property = "additionalThemeDirectories")
    private List<String> additionalThemeDirectories;

    private Map<String, String> themeNamePathMapping;

    private Map<String, Properties> themeNamePropertiesMapping;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        String themesDirectoryPath = this.webappSourceDir + PluginConstants.DEFAULT_THEMES_DIRECTORY;

        File themesDirectory = new File(themesDirectoryPath);
        File[] themeDirectories = themesDirectory.listFiles();

        // build mappings for convenient access
        try {
            buildThemeMappings(themeDirectories);
        } catch (IOException e) {
            throw new MojoExecutionException("Unable to build theme mappings", e);
        }

        List<String> orderedThemes = orderThemesForBuilding();

        for (String themeName : orderedThemes) {
            buildTheme(themeName);
        }
    }

    protected List<String> orderThemesForBuilding() {
        List<String> orderedThemes = new ArrayList<String>();

        for (String themeName : themeNamePathMapping.keySet()) {
            String themePath = themeNamePathMapping.get(themeName);

            if (orderedThemes.contains(themeName)) {
                continue;
            }

            List<String> themeParents = getAllThemeParents(themeName);
            for (String themeParent : themeParents) {
                if (!orderedThemes.contains(themeParent)) {
                    orderedThemes.add(themeParent);
                }
            }

            orderedThemes.add(themeName);
        }

        return orderedThemes;
    }

    protected List<String> getAllThemeParents(String themeName) {
        List<String> themeParents = new ArrayList<String>();

        if (!themeNamePropertiesMapping.containsKey(themeName)) {
            return themeParents;
        }

        Properties themeProperties = themeNamePropertiesMapping.get(themeName);
        if (themeProperties.containsKey(PluginConstants.ThemeConfiguration.PARENT)) {
            String parentThemeName = themeProperties.getProperty(PluginConstants.ThemeConfiguration.PARENT);

            themeParents.addAll(getAllThemeParents(parentThemeName));

            themeParents.add(parentThemeName);
        }

        return themeParents;
    }

    protected void buildTheme(String themeName) throws MojoExecutionException, MojoFailureException {
        super.getLog().info("Building theme " + themeName);

        Properties themeProperties = themeNamePropertiesMapping.get(themeName);

        String themePath = themeNamePathMapping.get(themeName);
        File themeDirectory = new File(themePath);

        // if parent theme exists, overlay the assets
        if (themeProperties.containsKey(PluginConstants.ThemeConfiguration.PARENT)) {
            String parentThemeName = themeProperties.getProperty(PluginConstants.ThemeConfiguration.PARENT);

            String[] parentExcludes = null;
            if (themeProperties.containsKey(PluginConstants.ThemeConfiguration.PARENT_EXCLUDES)) {
                String parentExcludesString = themeProperties.getProperty(PluginConstants.ThemeConfiguration.PARENT_EXCLUDES);

                parentExcludes = StringUtils.split(parentExcludesString, ",");
            }

            overlayParentAssets(themeDirectory, parentThemeName, parentExcludes);
        }
    }

    protected void overlayParentAssets(File themeDirectory, String parentThemeName,
                                       String[] parentExcludes) throws
            MojoExecutionException, MojoFailureException {
        String parentThemePath = this.themeNamePathMapping.get(parentThemeName);

        File parentThemeDirectory = new File(parentThemePath);
        if (!parentThemeDirectory.exists()) {
            throw new MojoExecutionException("Parent theme does not exist at path: " + parentThemePath);
        }

        List<String> copyDirectoryExcludes = new ArrayList<String>();
        copyDirectoryExcludes.addAll(Arrays.asList(parentExcludes));

        // add theme assets as excludes from the parent directory ncopy
        List<String> themeAssets = getDirectoryFileNames(themeDirectory, null, null);
        for (String themeAsset : themeAssets) {
            copyDirectoryExcludes.add(themeAsset);
        }

        try {
            FileUtils.copyDirectory(parentThemeDirectory, themeDirectory, "*", StringUtils.join(copyDirectoryExcludes, ","));
        } catch (IOException e) {
            throw new MojoExecutionException("Unable to copy parent directory", e);
        }
    }

    protected void buildThemeMappings(File[] themeDirectories) throws IOException {
        themeNamePathMapping = new HashMap<String, String>();
        themeNamePropertiesMapping = new HashMap<String, Properties>();

        for (File themeDirectory : themeDirectories) {
            this.themeNamePathMapping.put(themeDirectory.getName(), themeDirectory.getPath());

            Properties themeProperties = retrieveThemeProperties(themeDirectory.getPath());
            if (themeProperties != null) {
                this.themeNamePropertiesMapping.put(themeDirectory.getName(), themeProperties);
            }
        }

        if (this.additionalThemeDirectories != null) {
            for (String additionalThemeDirectory : this.additionalThemeDirectories) {
                String themeName = additionalThemeDirectory;

                if (additionalThemeDirectory.contains(File.pathSeparator)) {
                    themeName = StringUtils.substringAfterLast(additionalThemeDirectory, File.pathSeparator);
                }

                String themePath = this.webappSourceDir + additionalThemeDirectory;

                this.themeNamePathMapping.put(themeName, themePath);

                Properties themeProperties = retrieveThemeProperties(themePath);
                if (themeProperties != null) {
                    this.themeNamePropertiesMapping.put(themeName, themeProperties);
                }
            }
        }
    }

    protected Properties retrieveThemeProperties(String themeDirectory) throws IOException {
        Properties themeProperties = null;

        File propertiesFile = new File(themeDirectory, PluginConstants.THEME_PROPERTIES_FILE);
        if (propertiesFile.exists()) {
            themeProperties = new Properties();

            themeProperties.load(new FileInputStream(propertiesFile));
        }

        return themeProperties;
    }

    protected List<File> getDirectoryFiles(File baseDirectory, String[] includes, String[] excludes) {
        List<File> files = new ArrayList<File>();

        List<String> fileNames = getDirectoryFileNames(baseDirectory, includes, excludes);
        for (String fileName : fileNames) {
            files.add(new File(baseDirectory, fileName));
        }

        return files;
    }

    protected List<String> getDirectoryFileNames(File baseDirectory, String[] includes, String[] excludes) {
        List<String> files = new ArrayList<String>();

        DirectoryScanner scanner = new DirectoryScanner();

        if (includes != null) {
            scanner.setIncludes(includes);
        }

        if (excludes != null) {
            scanner.setExcludes(excludes);
        }

        scanner.addDefaultExcludes();
        scanner.setBasedir(baseDirectory);

        scanner.scan();

        for (String includedFilename : scanner.getIncludedFiles()) {
            files.add(includedFilename);
        }

        return files;
    }

}
