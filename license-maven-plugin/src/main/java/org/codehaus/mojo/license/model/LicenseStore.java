/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.codehaus.mojo.license.model;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The {@code LicenseStore} offers {@link License} coming from different {@link
 * LicenseRepository}.
 *
 * @author tchemit <chemit@codelutin.com>
 * @since 1.0
 */
public class LicenseStore
    implements Iterable<LicenseRepository>
{

    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog( LicenseStore.class );

    /**
     * class-path directory where is the licenses repository
     */
    public static final String JAR_LICENSE_REPOSITORY = "/META-INF/licenses";

    /**
     * list of available license repositories
     */
    protected List<LicenseRepository> repositories;

    /**
     * flag to know if store was init
     */
    protected boolean init;

    public static LicenseStore createLicenseStore( org.apache.maven.plugin.logging.Log log, String... extraResolver )
        throws MojoExecutionException
    {
        LicenseStore store;
        try
        {
            store = new LicenseStore();
            store.addJarRepository();
            if ( extraResolver != null )
            {
                for ( String s : extraResolver )
                {
                    if ( StringUtils.isNotEmpty( s ) )
                    {
                        log.info( "adding extra resolver " + s );
                        store.addRepository( s );
                    }
                }
            }
            store.init();
        }
        catch ( IllegalArgumentException ex )
        {
            throw new MojoExecutionException( "could not obtain the license repository", ex );
        }
        catch ( IOException ex )
        {
            throw new MojoExecutionException( "could not obtain the license repository", ex );
        }
        return store;
    }

    public void init()
        throws IOException
    {
        checkNotInit( "init" );
        try
        {
            if ( repositories == null )
            {
                // adding the default class-path repository
                addJarRepository();
            }
            for ( LicenseRepository r : this )
            {
                r.load();
            }
        }
        finally
        {
            init = true;
        }
    }

    public List<LicenseRepository> getRepositories()
    {
        return repositories;
    }

    public String[] getLicenseNames()
    {
        checkInit( "getLicenseNames" );
        List<String> result = new ArrayList<String>();
        for ( LicenseRepository repository : this )
        {
            for ( License license : repository )
            {
                result.add( license.getName() );
            }
        }
        return result.toArray( new String[result.size()] );
    }

    public License[] getLicenses()
    {
        checkInit( "getLicenses" );
        List<License> result = new ArrayList<License>();
        if ( repositories != null )
        {
            for ( LicenseRepository repository : this )
            {
                for ( License license : repository )
                {
                    result.add( license );
                }
            }
        }
        return result.toArray( new License[result.size()] );
    }

    public License getLicense( String licenseName )
    {
        checkInit( "getLicense" );
        Iterator<LicenseRepository> itr = iterator();
        License result = null;
        while ( itr.hasNext() )
        {
            LicenseRepository licenseRepository = itr.next();
            License license = licenseRepository.getLicense( licenseName );
            if ( license != null )
            {
                result = license;
                break;
            }
        }
        if ( result == null && log.isDebugEnabled() )
        {
            log.debug( "could not find license named '" + licenseName + "'" );
        }
        return result;
    }

    public void addRepository( String extraResolver )
        throws IOException
    {
        addRepository( new URL( extraResolver ) );
    }

    public void addRepository( URL baseURL )
        throws IOException
    {
        checkNotInit( "addRepository" );
        LicenseRepository repository = new LicenseRepository();
        repository.setBaseURL( baseURL );
        if ( log.isDebugEnabled() )
        {
            log.debug( "Adding a license repository " + repository );
        }
        addRepository( repository );
    }

    public void addJarRepository()
        throws IOException
    {
        checkNotInit( "addJarRepository" );
        URL baseURL = getClass().getResource( JAR_LICENSE_REPOSITORY );
        LicenseRepository repository = new LicenseRepository();
        repository.setBaseURL( baseURL );
        if ( log.isDebugEnabled() )
        {
            log.debug( "Adding a jar license repository " + repository );
        }
        addRepository( repository );
    }

    public Iterator<LicenseRepository> iterator()
    {
        return getRepositories().iterator();
    }

    protected void addRepository( LicenseRepository repository )
    {
        checkNotInit( "addRepository" );
        if ( repositories == null )
        {
            repositories = new ArrayList<LicenseRepository>();

        }
        if ( log.isInfoEnabled() )
        {
            log.info( "Adding a license repository " + repository.getBaseURL() );
        }
        repositories.add( repository );
    }

    protected void checkInit( String operation )
        throws IllegalStateException
    {
        if ( !init )
        {
            throw new IllegalStateException( "store was not init, operation [" + operation + "] not possible." );
        }
    }

    protected void checkNotInit( String operation )
        throws IllegalStateException
    {
        if ( init )
        {
            throw new IllegalStateException( "store was init, operation [" + operation + "+] not possible." );
        }
    }
}
