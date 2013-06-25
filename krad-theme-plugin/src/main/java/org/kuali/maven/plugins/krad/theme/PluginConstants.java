package org.kuali.maven.plugins.krad.theme;

/**
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class PluginConstants {
    public static final String DEFAULT_THEMES_DIRECTORY = "/themes";
    public static final String THEME_PROPERTIES_FILE = "theme.properties";

    public static final String[] cssInclude = {".css"};
    public static final String[] jsInclude = {".js"};

    public static class ThemeConfiguration {
        public static final String EXCLUDES = "excludes";
        public static final String PARENT = "parent";
        public static final String PARENT_EXCLUDES = "parentExcludes";
    }
}
