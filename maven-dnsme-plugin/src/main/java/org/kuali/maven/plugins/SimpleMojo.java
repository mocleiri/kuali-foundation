package org.kuali.maven.plugins;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @author Jeff Caddel
 * @goal simple
 */
public class SimpleMojo extends AbstractMojo {
    // Sat, 12 Feb 2011 20:59:04 GMT
    String format = "EEE, dd MMM YYYY HH:mm:ss z";
    SimpleDateFormat sdf = new SimpleDateFormat(format);

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        sdf.setTimeZone(timeZone);
        Date date = new Date(System.currentTimeMillis());
        getLog().info(sdf.format(date));
    }
}
