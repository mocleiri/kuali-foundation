/*
 * Copyright 2007-2009 The Kuali Foundation
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
package org.kuali.rice.kim.test.service;

import org.junit.Test;
import org.kuali.rice.kim.api.group.Group;
import org.kuali.rice.kim.api.group.GroupService;
import org.kuali.rice.kim.api.group.GroupUpdateService;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kim.test.KIMTestCase;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test the GroupService 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class GroupServiceTest extends KIMTestCase {

	private GroupService groupService;
	private GroupUpdateService groupUpdateService;

	public void setUp() throws Exception {
		super.setUp();
		setGroupService(KimApiServiceLocator.getGroupService());
		setGroupUpdateService(KimApiServiceLocator.getGroupUpdateService());
	}
	
	@Test
	public void testGetDirectMemberGroupIds() {
		List<String> groupIds = getGroupService().getDirectMemberGroupIds("g1");

		assertTrue( "g1 must contain group g2", groupIds.contains( "g2" ) );
		assertFalse( "g1 must not contain group g3", groupIds.contains( "g3" ) );

		groupIds = getGroupService().getDirectMemberGroupIds("g2");
		
		assertTrue( "g2 must contain group g3", groupIds.contains( "g3" ) );
		assertFalse( "g2 must not contain group g4 (inactive)", groupIds.contains( "g4" ) );
		
	}
	
	@Test
	public void testGetMemberGroupIds() {
		List<String> groupIds = getGroupService().getMemberGroupIds("g1");

		assertTrue( "g1 must contain group g2", groupIds.contains( "g2" ) );
		assertTrue( "g1 must contain group g3", groupIds.contains( "g3" ) );
		assertFalse( "g1 must not contain group g4 (inactive)", groupIds.contains( "g4" ) );

		groupIds = getGroupService().getMemberGroupIds("g2");

		assertTrue( "g2 must contain group g3", groupIds.contains( "g3" ) );
		assertFalse( "g2 must not contain group g1", groupIds.contains( "g1" ) );
	}
	
	// test principal membership
	@Test
	public void testPrincipalMembership() {
		assertTrue( "p1 must be in g2", getGroupService().isMemberOfGroup("p1", "g2") );
		assertTrue( "p1 must be direct member of g2", getGroupService().isDirectMemberOfGroup("p1", "g2") );
		assertTrue( "p3 must be in g2", getGroupService().isMemberOfGroup("p3", "g2") );
		assertFalse( "p3 should not be a direct member of g2", getGroupService().isDirectMemberOfGroup("p3", "g2") );
		assertFalse( "p4 should not be reported as a member of g2 (g4 is inactive)", getGroupService().isMemberOfGroup("p4", "g2") );
		
		// re-activate group 4
		Group g4Info = getGroupService().getGroup("g4");
        Group.Builder builder = Group.Builder.create(g4Info);
		builder.setActive(true);

        //todo: reenable this once update service updated
		getGroupUpdateService().updateGroup("g4", builder.build());
		g4Info = getGroupService().getGroup("g4");
		
		assertTrue( "p4 should be reported as a member of g2 (now that g4 is active)", getGroupService().isMemberOfGroup("p4", "g2") );
	}

	public GroupService getGroupService() {
		return this.groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public GroupUpdateService getGroupUpdateService() {
		return this.groupUpdateService;
	}

	public void setGroupUpdateService(GroupUpdateService groupUpdateService) {
		this.groupUpdateService = groupUpdateService;
	}

}
