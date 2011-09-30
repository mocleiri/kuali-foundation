package org.kuali.maven.plugins;

import java.util.Properties;
import java.util.Set;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Set properties configured on the plugin as system properties
 *
 * @author Jeff Caddel
 * @goal set
 */
public class SetSystemPropertiesMojo extends AbstractMojo {

    /**
     * Any properties specified here will be set as system properties. Overriding any system properties with the same
     * name that were already set.
     *
     * @parameter
     */
    private Properties systemProperties;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            if (systemProperties == null) {
                return;
            }
            Set<String> keys = systemProperties.stringPropertyNames();
            for (String key : keys) {
                String value = systemProperties.getProperty(key);
                System.setProperty(key, value);
                getLog().info("Setting " + key + "=" + value);
            }
        } catch (Exception e) {
            throw new MojoExecutionException("Unexpected error", e);
        }
    }

    public Properties getSystemProperties() {
        return systemProperties;
    }

    public void setSystemProperties(Properties systemProperties) {
        this.systemProperties = systemProperties;
    }
}
