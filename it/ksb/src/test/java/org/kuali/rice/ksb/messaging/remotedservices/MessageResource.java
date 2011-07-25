/*
 * Copyright 2007-2010 The Kuali Foundation
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
package org.kuali.rice.ksb.messaging.remotedservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * RESTful service interface for working with a Message
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
@Path("message")
public interface MessageResource {

	@POST
	@Consumes("application/xml")
	@Produces("application/xml")
	public Message createMessage(Message message);

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Message retrieve(@PathParam("id") String id);

	@PUT
	@Consumes("application/xml")
	public void update(Message message);

	@DELETE
	public void delete(@PathParam("id") String id);

}
