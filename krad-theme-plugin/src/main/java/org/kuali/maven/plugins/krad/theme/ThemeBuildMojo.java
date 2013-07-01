package org.kuali.maven.plugins.krad.theme;

import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.*;
import org.codehaus.plexus.util.Scanner;
import org.lesscss.LessCompiler;
import org.lesscss.LessException;
import org.lesscss.LessSource;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.util.*;

/**
 * @goal build
 * @phase process-resources
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
@Mojo(name = "build", defaultPhase = LifecyclePhase.PROCESS_RESOURCES, threadSafe = false)
@Execute(goal ="build")
public class ThemeBuildMojo extends AbstractMojo {

    /**
     * Webapp source directory.
     *
     * @parameter expression="${webappSourceDirectory}" default-value="${basedir}/src/main/webapp/themes"
     */
    @Parameter(property = "webappSourceDirectory")
    private String webappSourceDir;

    /**
     * additionalThemeDirectories
     *
     * @parameter expression="${additionalThemeDirectories}"
     */
    @Parameter(property = "additionalThemeDirectories")
    private List<String> additionalThemeDirectories;

    private Map<String, String> themeNamePathMapping;

    private Map<String, Properties> themeNamePropertiesMapping;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("ThemeBuilding");
        getLog().info("  webappSourceDirectory="+webappSourceDir);

        if(additionalThemeDirectories == null) {
            additionalThemeDirectories = new ArrayList();
        }

        getLog().info("  additionalThemeDirectories="+additionalThemeDirectories);
        String themesDirectoryPath = this.webappSourceDir + PluginConstants.DEFAULT_THEMES_DIRECTORY;

        File themesDirectory = new File(themesDirectoryPath);
        File[] themeDirectories = themesDirectory.listFiles();

        getLog().info("  theme Directory " + themesDirectory);

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

        if (themeProperties == null ) {
            themeProperties = new Properties();
        }

        for(String property : themeProperties.stringPropertyNames()){
          getLog().info("property:"+property);
        }


        String themePath = themeNamePathMapping.get(themeName);
        File themeDirectory = new File(themePath);

        // if parent theme exists, overlay the assets
        if (themeProperties.containsKey(PluginConstants.ThemeConfiguration.PARENT)) {
            String parentThemeName = themeProperties.getProperty(PluginConstants.ThemeConfiguration.PARENT);

            String[] parentExcludes = new String[] {} ;
            if (themeProperties.containsKey(PluginConstants.ThemeConfiguration.PARENT_EXCLUDES)) {
                String parentExcludesString = themeProperties.getProperty(PluginConstants.ThemeConfiguration.PARENT_EXCLUDES);

                parentExcludes = StringUtils.split(parentExcludesString, ",");
            }

            overlayParentAssets(themeDirectory, parentThemeName, parentExcludes);
        }

        String[] themeExcludes = new String[]{};
        if (themeProperties.containsKey(PluginConstants.ThemeConfiguration.EXCLUDES)) {
            String excludeString = themeProperties.getProperty(PluginConstants.ThemeConfiguration.EXCLUDES);

            themeExcludes = StringUtils.split(excludeString, ",");
        }

        List<String> themeAssets =  getDirectoryFileNames(themeDirectory, null, themeExcludes);
        compileLessFiles(themeName, themeAssets);
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
            getLog().info("    parentThemeDirectory: " + parentThemeDirectory);
            getLog().info("    themeDirectory: " + themeDirectory);
            getLog().info("    copyDirectoryExcludes: " + StringUtils.join(copyDirectoryExcludes, ","));

            String[] filesToCopy = getIncludedFiles(parentThemeDirectory, new String[] {"**/*"},  copyDirectoryExcludes.toArray(new String[copyDirectoryExcludes.size()]) );
            for ( String file : filesToCopy){
                getLog().info("        copy file:"+file);
                File input = new File(parentThemeDirectory, file);
                File output = new File(themeDirectory, file);
                FileUtils.copyFile(input, output);
            }
             //FileUtils.copyDirectory(parentThemeDirectory, themeDirectory, "**/*", StringUtils.join(copyDirectoryExcludes, ","));
        } catch (IOException e) {
            throw new MojoExecutionException("Unable to copy parent directory", e);
        }
    }

    protected void buildThemeMappings(File[] themeDirectories) throws IOException {
        themeNamePathMapping = new HashMap<String, String>();
        themeNamePropertiesMapping = new HashMap<String, Properties>();

        if(ObjectUtils.isEmpty(themeDirectories)) {
            return;
        }

        for (File themeDirectory : themeDirectories) {
            if(!themeDirectory.isDirectory()) {
                continue;
            }

            getLog().info("  theme " + themeDirectory);
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
            getLog().debug("    file:" + includedFilename);
            files.add(includedFilename);
        }

        return files;
    }

    protected void compileLessFiles(String themeName, List<String> themeAssets) throws MojoExecutionException{
        LessCompiler lessCompiler = new LessCompiler();
        lessCompiler.setCompress(true);
        boolean forceCompile = true;

        for (String file : themeAssets) {
            if(StringUtils.substringAfterLast(file,".").equals("less")){
                getLog().info("compiling " + file);
                String directory = themeNamePathMapping.get(themeName);
                File input = new File(directory, file);
                File output = new File(directory, file.replace(".less", ".css"));

                if (!output.getParentFile().exists() && !output.getParentFile().mkdirs()) {
                    throw new MojoExecutionException("Cannot create output directory " + output.getParentFile());
                }

                try {
                    LessSource lessSource = new LessSource(input);

                   // if (output.lastModified() < lessSource.getLastModifiedIncludingImports()) {
                        getLog().info("Compiling LESS source: " + file + "...");
                        lessCompiler.compile(lessSource, output, forceCompile);
                   // }
                   // else {
                   //     getLog().info("Bypassing LESS source: " + file + " (not modified)");
                   // }
                } catch (IOException e) {
                    throw new MojoExecutionException("Error while compiling LESS source: " + file, e);
                } catch (LessException e) {
                    throw new MojoExecutionException("Error while compiling LESS source: " + file, e);
                }
            }
        }
    }

    /**
     * Scans for the files in directory
     *
     * @return The list of files
     */
    protected String[] getIncludedFiles(File directory, String[] includes,String[] excludes) {
        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(directory);
        scanner.setIncludes(includes);
        scanner.setExcludes(excludes);
        scanner.scan();

        return scanner.getIncludedFiles();
    }
}
