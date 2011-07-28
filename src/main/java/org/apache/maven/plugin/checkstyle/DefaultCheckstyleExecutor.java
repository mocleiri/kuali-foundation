/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.maven.plugin.checkstyle;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.logging.AbstractLogEnabled;
import org.codehaus.plexus.resource.ResourceManager;
import org.codehaus.plexus.resource.loader.FileResourceCreationException;
import org.codehaus.plexus.resource.loader.FileResourceLoader;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.StringUtils;

import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.ConfigurationLoader;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.PackageNamesLoader;
import com.puppycrawl.tools.checkstyle.PropertiesExpander;
import com.puppycrawl.tools.checkstyle.api.AuditListener;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;
import com.puppycrawl.tools.checkstyle.api.FilterSet;
import com.puppycrawl.tools.checkstyle.filters.SuppressionsLoader;

/**
 * @author <a href="mailto:olamy@apache.org">olamy</a>
 * @plexus.component role="org.apache.maven.plugin.checkstyle.CheckstyleExecutor" role-hint="default" instantiation-strategy="per-lookup"
 * @since 2.5
 * @version $Id: DefaultCheckstyleExecutor.java 950989 2010-06-03 13:28:57Z dkulp $
 */
public class DefaultCheckstyleExecutor
    extends AbstractLogEnabled
    implements CheckstyleExecutor
{

    /**
     * @plexus.requirement role="org.codehaus.plexus.resource.ResourceManager" role-hint="default"
     */
    private ResourceManager locator;

    private static final File[] EMPTY_FILE_ARRAY = new File[0];

    public CheckstyleResults executeCheckstyle( CheckstyleExecutorRequest request )
        throws CheckstyleExecutorException, CheckstyleException
    {
        // checkstyle will always use the context classloader in order
        // to load resources (dtds),
        // so we have to fix it
        // olamy this hack is not anymore needed in maven 3.x
        ClassLoader checkstyleClassLoader = PackageNamesLoader.class.getClassLoader();
        Thread.currentThread().setContextClassLoader( checkstyleClassLoader );

        if ( getLogger().isDebugEnabled() )
        {
            getLogger().debug( "executeCheckstyle start headerLocation : " + request.getHeaderLocation() );
        }
        locator.setOutputDirectory( new File( request.getProject().getBuild().getDirectory() ) );
        File[] files;
        try
        {
            files = getFilesToProcess( request );
        }
        catch ( IOException e )
        {
            throw new CheckstyleExecutorException( "Error getting files to process", e );
        }

        FilterSet filterSet = getSuppressions( request );

        Checker checker = new Checker();

        // setup classloader, needed to avoid "Unable to get class information
        // for ..." errors
        List<String> classPathStrings = new ArrayList<String>();
        List<String> outputDirectories = new ArrayList<String>();
        try
        {
            classPathStrings = request.getProject().getCompileClasspathElements();
            outputDirectories.add( request.getProject().getBuild().getOutputDirectory() );

            if ( request.isIncludeTestSourceDirectory() && ( request.getSourceDirectory() != null )
                && ( request.getTestSourceDirectory().exists() ) && ( request.getTestSourceDirectory().isDirectory() ) )
            {
                classPathStrings = request.getProject().getTestClasspathElements();
                outputDirectories.add( request.getProject().getBuild().getTestOutputDirectory() );
            }
        }
        catch ( DependencyResolutionRequiredException e )
        {
            throw new CheckstyleExecutorException( e.getMessage(), e );
        }

        if ( classPathStrings == null )
        {
            classPathStrings = Collections.EMPTY_LIST;
        }

        List<URL> urls = new ArrayList<URL>( classPathStrings.size() );

        for ( String path : classPathStrings )
        {
            try
            {
                urls.add( new File( path ).toURL() );
            }
            catch ( MalformedURLException e )
            {
                throw new CheckstyleExecutorException( e.getMessage(), e );
            }
        }

        for ( String outputDirectoryString : outputDirectories )
        {
            try
            {
                if ( outputDirectoryString != null )
                {
                    File outputDirectoryFile = new File( outputDirectoryString );
                    if ( outputDirectoryFile.exists() )
                    {
                        URL outputDirectoryUrl = outputDirectoryFile.toURL();
                        request.getLog().debug(
                                                "Adding the outputDirectory " + outputDirectoryUrl.toString()
                                                    + " to the Checkstyle class path" );
                        urls.add( outputDirectoryUrl );
                    }
                }
            }
            catch ( MalformedURLException e )
            {
                throw new CheckstyleExecutorException( e.getMessage(), e );
            }
        }

        URLClassLoader projectClassLoader = new URLClassLoader( (URL[]) urls.toArray( new URL[urls.size()] ), null );
        checker.setClassloader( projectClassLoader );

        checker.setModuleClassLoader( Thread.currentThread().getContextClassLoader() );

        if ( filterSet != null )
        {
            checker.addFilter( filterSet );
        }
        Configuration configuration = getConfiguration( request );
        checker.configure( configuration );

        AuditListener listener = request.getListener();

        if ( listener != null )
        {
            checker.addListener( listener );
        }

        if ( request.isConsoleOutput() )
        {
            checker.addListener( request.getConsoleListener() );
        }

        CheckstyleReportListener sinkListener = new CheckstyleReportListener( request.getSourceDirectory(), configuration );
        if ( request.isIncludeTestSourceDirectory() && ( request.getTestSourceDirectory() != null )
            && ( request.getTestSourceDirectory().exists() ) && ( request.getTestSourceDirectory().isDirectory() ) )
        {
            sinkListener.addSourceDirectory( request.getTestSourceDirectory() );
        }

        checker.addListener( sinkListener );

        List<File> filesList = new ArrayList<File>();
        for ( int i = 0; i < files.length; i++ )
        {
            filesList.add( files[i] );
        }
        int nbErrors = checker.process( filesList );

        checker.destroy();

        if ( request.getStringOutputStream() != null )
        {
            request.getLog().info( request.getStringOutputStream().toString() );
        }

        if ( request.isFailsOnError() && nbErrors > 0 )
        {
            // TODO: should be a failure, not an error. Report is not meant to
            // throw an exception here (so site would
            // work regardless of config), but should record this information
            throw new CheckstyleExecutorException( "There are " + nbErrors + " checkstyle errors." );
        }
        else if ( nbErrors > 0 )
        {
            request.getLog().info( "There are " + nbErrors + " checkstyle errors." );
        }

        return sinkListener.getResults();
    }

    public Configuration getConfiguration( CheckstyleExecutorRequest request )
        throws CheckstyleExecutorException
    {
        try
        {
            // checkstyle will always use the context classloader in order
            // to load resources (dtds),
            // so we have to fix it
            ClassLoader checkstyleClassLoader = PackageNamesLoader.class.getClassLoader();
            Thread.currentThread().setContextClassLoader( checkstyleClassLoader );
            String configFile = getConfigFile( request );
            Properties overridingProperties = getOverridingProperties( request );
            Configuration config = ConfigurationLoader
                .loadConfiguration( configFile, new PropertiesExpander( overridingProperties ) );
            String effectiveEncoding = StringUtils.isNotEmpty( request.getEncoding() ) ? request.getEncoding() : System
                .getProperty( "file.encoding", "UTF-8" );
            if ( StringUtils.isEmpty( request.getEncoding() ) )
            {
                request.getLog().warn(
                                       "File encoding has not been set, using platform encoding " + effectiveEncoding
                                           + ", i.e. build is platform dependent!" );
            }
            Configuration[] modules = config.getChildren();
            for ( int i = 0; i < modules.length; i++ )
            {
                Configuration module = modules[i];
                if ( "Checker".equals( module.getName() )
                    || "com.puppycrawl.tools.checkstyle.Checker".equals( module.getName() ) )
                {
                    if ( module instanceof DefaultConfiguration )
                    {
                        ( (DefaultConfiguration) module ).addAttribute( "charset", effectiveEncoding );
                    }
                    else
                    {
                        request.getLog().warn( "Failed to configure file encoding on module " + module );
                    }
                }
                if ( "TreeWalker".equals( module.getName() )
                    || "com.puppycrawl.tools.checkstyle.TreeWalker".equals( module.getName() ) )
                {
                    if ( module instanceof DefaultConfiguration )
                    {
                        ( (DefaultConfiguration) module ).addAttribute( "cacheFile", request.getCacheFile() );
                    }
                    else
                    {
                        request.getLog().warn( "Failed to configure cache file on module " + module );
                    }
                }
            }
            return config;
        }
        catch ( CheckstyleException e )
        {
            throw new CheckstyleExecutorException( "Failed during checkstyle configuration", e );
        }
    }

    private Properties getOverridingProperties( CheckstyleExecutorRequest request )
        throws CheckstyleExecutorException
    {
        Properties p = new Properties();

        try
        {
            if ( request.getPropertiesLocation() != null )
            {
                if ( getLogger().isDebugEnabled() )
                {
                    getLogger().debug( "request.getPropertiesLocation() " + request.getPropertiesLocation() );
                }

                File propertiesFile = locator.getResourceAsFile( request.getPropertiesLocation(),
                                                                 "checkstyle-checker.properties" );

                if ( propertiesFile != null )
                {
                    p.load( new FileInputStream( propertiesFile ) );
                }
            }

            if ( StringUtils.isNotEmpty( request.getPropertyExpansion() ) )
            {
                String propertyExpansion = request.getPropertyExpansion();
                // Convert \ to \\, so that p.load will convert it back properly
                propertyExpansion = StringUtils.replace( propertyExpansion, "\\", "\\\\" );
                p.load( new ByteArrayInputStream( propertyExpansion.getBytes() ) );
            }

            // Workaround for MCHECKSTYLE-48
            // Make sure that "config/maven-header.txt" is the default value
            // for headerLocation, if configLocation="config/maven_checks.xml"
            String headerLocation = request.getHeaderLocation();
            if ( "config/maven_checks.xml".equals( request.getConfigLocation() ) )
            {

                if ( "LICENSE.txt".equals( request.getHeaderLocation() ) )
                {
                    headerLocation = "config/maven-header.txt";
                }
            }
            if ( getLogger().isDebugEnabled() )
            {
                getLogger().debug( "headerLocation " + headerLocation );
            }

            if ( StringUtils.isNotEmpty( headerLocation ) )
            {
                try
                {
                    File headerFile = locator.getResourceAsFile( headerLocation, "checkstyle-header.txt" );

                    if ( headerFile != null )
                    {
                        p.setProperty( "checkstyle.header.file", headerFile.getAbsolutePath() );
                    }
                }
                catch ( FileResourceCreationException e )
                {
                    throw new CheckstyleExecutorException( "Unable to process header location: " + headerLocation, e );
                }
                catch ( ResourceNotFoundException e )
                {
                    throw new CheckstyleExecutorException( "Unable to process header location: " + headerLocation, e );
                }
            }

            if ( request.getCacheFile() != null )
            {
                p.setProperty( "checkstyle.cache.file", request.getCacheFile() );
            }
        }
        catch ( IOException e )
        {
            throw new CheckstyleExecutorException( "Failed to get overriding properties", e );
        }
        catch ( FileResourceCreationException e )
        {
            throw new CheckstyleExecutorException( "Failed to get overriding properties", e );
        }
        catch ( ResourceNotFoundException e )
        {
            throw new CheckstyleExecutorException( "Failed to get overriding properties", e );
        }
        if ( request.getSuppressionsFileExpression() != null )
        {
            String suppresionFile = request.getSuppressionsFileExpression();

            if ( suppresionFile != null )
            {
                p.setProperty( request.getSuppressionsFileExpression(), suppresionFile );
            }
        }

        return p;
    }

    private File[] getFilesToProcess( CheckstyleExecutorRequest request )
        throws IOException
    {
        StringBuffer excludesStr = new StringBuffer();

        if ( StringUtils.isNotEmpty( request.getExcludes() ) )
        {
            excludesStr.append( request.getExcludes() );
        }

        String[] defaultExcludes = FileUtils.getDefaultExcludes();
        for ( int i = 0; i < defaultExcludes.length; i++ )
        {
            if ( excludesStr.length() > 0 )
            {
                excludesStr.append( "," );
            }

            excludesStr.append( defaultExcludes[i] );
        }

        if (request.getSourceDirectory() == null || !request.getSourceDirectory().exists())
        {
            return EMPTY_FILE_ARRAY;
        }

        List<File> files =
            FileUtils.getFiles( request.getSourceDirectory(), request.getIncludes(), excludesStr.toString() );
        if ( request.isIncludeTestSourceDirectory() && ( request.getTestSourceDirectory() != null )
            && ( request.getTestSourceDirectory().exists() ) && ( request.getTestSourceDirectory().isDirectory() ) )
        {
            files.addAll( FileUtils.getFiles( request.getTestSourceDirectory(), request.getIncludes(),
                                              excludesStr.toString() ) );
        }

        return (File[]) files.toArray( EMPTY_FILE_ARRAY );
    }

    private FilterSet getSuppressions( CheckstyleExecutorRequest request )
        throws CheckstyleExecutorException
    {
        try
        {
            File suppressionsFile = locator.resolveLocation( request.getSuppressionsLocation(),
                                                             "checkstyle-suppressions.xml" );

            if ( suppressionsFile == null )
            {
                return null;
            }

            return SuppressionsLoader.loadSuppressions( suppressionsFile.getAbsolutePath() );
        }
        catch ( CheckstyleException ce )
        {
            throw new CheckstyleExecutorException( "failed to load suppressions location: "
                + request.getSuppressionsLocation(), ce );
        }
        catch ( IOException e )
        {
            throw new CheckstyleExecutorException( "Failed to process supressions location: "
                + request.getSuppressionsLocation(), e );
        }
    }

    private String getConfigFile( CheckstyleExecutorRequest request )
        throws CheckstyleExecutorException
    {
        try
        {
            if ( getLogger().isDebugEnabled() )
            {
                getLogger().debug( "request.getConfigLocation() " + request.getConfigLocation() );
            }
            
            MavenProject parent = request.getProject();
            while ( parent != null && parent.getFile() != null )
            {
                // MCHECKSTYLE-131 ( olamy ) I don't like this hack.
                // (dkulp) Me either.   It really pollutes the location stuff
                // by allowing searches of stuff outside the current module.
                File dir = parent.getFile().getParentFile();
                locator.addSearchPath( FileResourceLoader.ID, dir.getAbsolutePath() );
                parent = parent.getParent();
            }
            locator.addSearchPath( "url", "" );

            File configFile = locator.getResourceAsFile( request.getConfigLocation(), "checkstyle-checker.xml" );
            if ( configFile == null )
            {
                throw new CheckstyleExecutorException( "Unable to process config location: "
                    + request.getConfigLocation() );
            }
            return configFile.getAbsolutePath();
        }
        catch ( org.codehaus.plexus.resource.loader.ResourceNotFoundException e )
        {
            throw new CheckstyleExecutorException( "Unable to find configuration file at location "
                + request.getConfigLocation(), e );
        }
        catch ( FileResourceCreationException e )
        {
            throw new CheckstyleExecutorException( "Unable to process configuration file location "
                + request.getConfigLocation(), e );
        }

    }
}
