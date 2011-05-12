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
package org.kuali.rice.kim.workflow.attribute;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.core.util.AttributeSet;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kim.api.group.Group;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kim.api.type.KimType;
import org.kuali.rice.kim.api.type.KimTypeInfoService;
import org.kuali.rice.kim.bo.role.dto.RoleMembershipInfo;
import org.kuali.rice.kim.bo.ui.KimDocumentRoleMember;
import org.kuali.rice.kim.bo.ui.PersonDocumentGroup;
import org.kuali.rice.kim.bo.ui.PersonDocumentRole;
import org.kuali.rice.kim.document.IdentityManagementGroupDocument;
import org.kuali.rice.kim.document.IdentityManagementPersonDocument;
import org.kuali.rice.kim.document.IdentityManagementRoleDocument;
import org.kuali.rice.kim.api.group.GroupService;
import org.kuali.rice.kim.service.KIMServiceLocatorWeb;
import org.kuali.rice.kim.service.RoleService;
import org.kuali.rice.kim.service.support.KimTypeService;
import org.kuali.rice.kim.util.KimConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.workflow.attribute.QualifierResolverBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a description of what this class does - kellerj don't forget to fill this in. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class KimTypeQualifierResolver extends QualifierResolverBase {
	private static final Logger LOG = Logger.getLogger(KimTypeQualifierResolver.class);
	
	protected static final String GROUP_ROUTE_LEVEL = "GroupType";
	protected static final String ROLE_ROUTE_LEVEL = "RoleType";

	private static KimTypeInfoService kimTypeInfoService;
	private static GroupService groupService;
	private static RoleService roleService;
	
	protected static Map<String,KimTypeService> typeServices = new HashMap<String, KimTypeService>();
	
	/**
	 * This overridden method ...
	 * 
	 * @see org.kuali.rice.kew.role.QualifierResolver#resolve(org.kuali.rice.kew.engine.RouteContext)
	 */
	public List<AttributeSet> resolve(RouteContext context) {
        String routeLevel = context.getNodeInstance().getName();
        Document document = getDocument(context);
        List<AttributeSet> qualifiers = new ArrayList<AttributeSet>();
        String customDocTypeName = null;
        
        if ( document instanceof IdentityManagementGroupDocument ) {
        	customDocTypeName = handleGroupDocument(qualifiers, (IdentityManagementGroupDocument)document, routeLevel);
        } else if ( document instanceof IdentityManagementRoleDocument ) {
        	customDocTypeName = handleRoleDocument(qualifiers, (IdentityManagementRoleDocument)document, routeLevel);
        } else if ( document instanceof IdentityManagementPersonDocument ) {
        	customDocTypeName = handlePersonDocument(qualifiers, (IdentityManagementPersonDocument)document, routeLevel);
        }
    	// add standard components
        decorateWithCommonQualifiers(qualifiers, context, customDocTypeName);
    	// return the resulting list of AttributeSets
		return qualifiers;
	}

	protected KimTypeService getTypeService( String typeId ) {
    	KimTypeService typeService = typeServices.get(typeId);
    	if ( typeService == null ) {       		
        	KimType typeInfo = getKimTypeInfoService().getKimType(typeId);
        	if ( typeInfo != null ) {
        		typeService = KIMServiceLocatorWeb.getKimTypeService(typeInfo);
        		typeServices.put(typeId, typeService);
        	} else {
        		LOG.warn( "Unable to retrieve KIM Type Info object for id: " + typeId );
        	}
    	}
    	return typeService;
	}
	
	protected void putMatchingAttributesIntoQualifier( AttributeSet qualifier, AttributeSet itemAttributes, List<String> routingAttributes ) {
		if ( routingAttributes != null && !routingAttributes.isEmpty() ) {
        	// pull the qualifiers off the document object (group or role member)
    		for ( String attribName : routingAttributes ) {
    			qualifier.put( attribName, itemAttributes.get(attribName));
    		}
		}
	}
	
	protected String handleGroupDocument( List<AttributeSet> qualifiers, IdentityManagementGroupDocument groupDoc, String routeLevel ) {
    	// get the appropriate type service for the group being edited
    	String typeId = groupDoc.getGroupTypeId();
    	qualifiers.add( getGroupQualifier(groupDoc.getGroupId(), typeId, groupDoc.getQualifiersAsAttributeSet(), routeLevel) );
    	
        return null;
	}

	protected String handleRoleDocument( List<AttributeSet> qualifiers, IdentityManagementRoleDocument roleDoc, String routeLevel ) {
        String customDocTypeName = null;

//        LOG.warn( "Role member data routing not implemented for the Role document yet!" );
    	// get the appropriate type service for the group being edited
    	String typeId = roleDoc.getRoleTypeId();
    	KimTypeService typeService = getTypeService(typeId);
    	if ( typeService != null ) {
    		// QUESTION: can roles be modified in a way which requires routing?
    		// get the existing role members
    		List<RoleMembershipInfo> currentRoleMembers = KimApiServiceLocator.getRoleService().getRoleMembers( Collections.singletonList( roleDoc.getRoleId() ), null );
    		// loop over the role members on the document, check  if added or removed
    		for ( KimDocumentRoleMember rm : roleDoc.getMembers() ) {
    			boolean foundMember = false;
    			for ( RoleMembershipInfo rmi : currentRoleMembers ) {
    				if ( rmi.getRoleMemberId().equals( rm.getRoleMemberId() ) ) {
    					foundMember = true;
    					if ( !rm.isActive() ) { // don't need to check the role member information 
    											// - only active members are returned
    						// inactivated member, add a qualifier
    						qualifiers.add( getRoleQualifier(rm.getRoleId(), typeId, typeService, rm.getQualifierAsAttributeSet(), routeLevel) );
    					}
    					break;
    				}
    			}
    			if ( !foundMember ) {
    				qualifiers.add( getRoleQualifier(rm.getRoleId(), typeId, typeService, rm.getQualifierAsAttributeSet(), routeLevel) );
    			}
    		}
    		
    		customDocTypeName = typeService.getWorkflowDocumentTypeName();
    	}		
    	return customDocTypeName;
	}
	
	protected String handlePersonDocument( List<AttributeSet> qualifiers, IdentityManagementPersonDocument personDoc, String routeLevel ) {
    	// check the route level - see if we are doing groups or roles at the moment
        String principalId = personDoc.getPrincipalId();
        if ( GROUP_ROUTE_LEVEL.equals(routeLevel) ) {
        	// if groups, find any groups to which the user was added or removed
        	// get the type and service for each group
        	// handle as per the group document, a qualifier for each group
        	List<String> currentGroups = getGroupService().getDirectGroupIdsForPrincipal(principalId);
        	List<PersonDocumentGroup> groups = personDoc.getGroups();
        	for ( PersonDocumentGroup group : groups ) {
        		// if they are being added to the group, add a qualifier set
        		if ( group.isActive() && !currentGroups.contains( group.getGroupId() ) ) {
            		// pull the group to get its attributes for adding to the qualifier 
            		Group kimGroup = getGroupService().getGroup(group.getGroupId());
        			qualifiers.add( getGroupQualifier( group.getGroupId(), kimGroup.getKimTypeId(), kimGroup.getAttributeSet(), routeLevel ) );
        		}
        	}
        	// detect removed groups
        	// get the existing directly assigned groups for the person
        	for ( String groupId : currentGroups ) {
        		for ( PersonDocumentGroup group : groups ) {
        			if ( !group.isActive() ) {
                		Group kimGroup = getGroupService().getGroup(groupId);
            			qualifiers.add( getGroupQualifier( groupId, kimGroup.getKimTypeId(), kimGroup.getAttributeSet(), routeLevel ) );
        			}
        		}
        	}
        } else if ( ROLE_ROUTE_LEVEL.equals(routeLevel) ) {
//        	getRoleService().get
        	for ( PersonDocumentRole pdr : personDoc.getRoles() ) {
            	KimTypeService typeService = getTypeService(pdr.getKimTypeId());
        		for ( KimDocumentRoleMember rm : pdr.getRolePrncpls() ) {
        			boolean foundMember = false;
            		for ( RoleMembershipInfo rmi : getRoleService().getRoleMembers( Collections.singletonList( rm.getRoleId() ), null ) ) {
            			if ( StringUtils.equals( rmi.getRoleMemberId(), rm.getRoleMemberId() ) ) {
            				foundMember = true;
        					if ( !rm.isActive() ) { // don't need to check the role member information 
								// - only active members are returned
        						// inactivated member, add a qualifier
								qualifiers.add( getRoleQualifier(rm.getRoleId(), pdr.getKimRoleType().getId(), typeService, rm.getQualifierAsAttributeSet(), routeLevel) );
							}
							break;
            			}
            		}
        			if ( !foundMember ) {
        				qualifiers.add( getRoleQualifier(rm.getRoleId(), pdr.getKimRoleType().getId(), typeService, rm.getQualifierAsAttributeSet(), routeLevel) );
        			}
        		}
        	}
        	// if roles, check the role member data for any roles added
        	// get the type and service for each role
        	// handle as for the role document, a qualifier for each role membership added
//        	LOG.warn( "Role-based data routing on the person document not implemented!" );
        }
        
    	return null;
	}

    protected AttributeSet getGroupQualifier( String groupId, String kimTypeId, AttributeSet groupAttributes, String routeLevel ) {
		AttributeSet qualifier = new AttributeSet();        			
		// pull the group to get its attributes for adding to the qualifier 
        qualifier.put(KimConstants.PrimaryKeyConstants.KIM_TYPE_ID, kimTypeId);
        qualifier.put(KimConstants.AttributeConstants.QUALIFIER_RESOLVER_PROVIDED_IDENTIFIER, kimTypeId);
        qualifier.put(KimConstants.PrimaryKeyConstants.GROUP_ID, groupId);
    	KimTypeService typeService = getTypeService(kimTypeId);
    	if ( typeService != null ) {
    		// check for the custom document type for the group
    		String customDocTypeName = typeService.getWorkflowDocumentTypeName();
    		if ( StringUtils.isNotBlank(customDocTypeName)) {
    			qualifier.put(KIM_ATTRIBUTE_DOCUMENT_TYPE_NAME, customDocTypeName );
    		}
    		putMatchingAttributesIntoQualifier(qualifier, groupAttributes, typeService.getWorkflowRoutingAttributes(routeLevel) );
    	}
    	return qualifier;
    }
    
    protected AttributeSet getRoleQualifier( String roleId, String kimTypeId, KimTypeService typeService, AttributeSet roleAttributes, String routeLevel ) {
		AttributeSet qualifier = new AttributeSet();        			
		// pull the group to get its attributes for adding to the qualifier 
        qualifier.put(KimConstants.PrimaryKeyConstants.KIM_TYPE_ID, kimTypeId);
        qualifier.put(KimConstants.AttributeConstants.QUALIFIER_RESOLVER_PROVIDED_IDENTIFIER, kimTypeId);
        qualifier.put(KimConstants.PrimaryKeyConstants.ROLE_ID, roleId);
		// check for the custom document type for the group
		String customDocTypeName = typeService.getWorkflowDocumentTypeName();
		if ( StringUtils.isNotBlank(customDocTypeName)) {
			qualifier.put(KIM_ATTRIBUTE_DOCUMENT_TYPE_NAME, customDocTypeName );
		}
		putMatchingAttributesIntoQualifier(qualifier, roleAttributes, typeService.getWorkflowRoutingAttributes(routeLevel) );
    	return qualifier;
    }
	
	public KimTypeInfoService getKimTypeInfoService() {
		if ( kimTypeInfoService == null ) {
			kimTypeInfoService = KimApiServiceLocator.getKimTypeInfoService();
		}
		return kimTypeInfoService;
	}

	public static GroupService getGroupService() {
		if ( groupService == null ) {
			groupService = KimApiServiceLocator.getGroupService();
		}
		return groupService;
	}

	public static RoleService getRoleService() {
		if ( roleService == null ) {
			roleService = KimApiServiceLocator.getRoleService();
		}
		return roleService;
	}
}
