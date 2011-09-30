package org.kuali.nexus.plugins.rest;

import org.restlet.Context;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.resource.ResourceException;
import org.restlet.resource.Variant;
import org.sonatype.plexus.rest.resource.PlexusResource;
import org.sonatype.plexus.rest.resource.AbstractPlexusResource;
import org.sonatype.plexus.rest.resource.PathProtectionDescriptor;

/**
 * A sample rest resource to get you started. By default this will automatically be mounted at:
 * http://localhost:8081/nexus/service/local/sample/hello
 */
public class HelloWorldPlexusResource extends AbstractPlexusResource implements PlexusResource {

	@Override
	public Object getPayloadInstance() {
		// if you allow PUT or POST you would need to return your object.
		return null;
	}

	@Override
	public PathProtectionDescriptor getResourceProtection() {
		// to be controlled by a new permission
		// return new PathProtectionDescriptor( this.getResourceUri(), "authcBasic,perms[nexus:somepermission]" );

		// for an anonymous resource
		return new PathProtectionDescriptor(this.getResourceUri(), "anon");
	}

	@Override
	public String getResourceUri() {
		// note this must start with a '/'
		return "/sample/hello";
	}

	@Override
	public Object get(Context context, Request request, Response response, Variant variant) throws ResourceException {

		// you can basically return any object, and it will be serialized

		// we will keep it simple
		return "Hello Sample Resource";
	}
}