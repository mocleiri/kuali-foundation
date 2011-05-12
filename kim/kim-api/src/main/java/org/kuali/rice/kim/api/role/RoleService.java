/*
 * Copyright 2006-2011 The Kuali Foundation
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

package org.kuali.rice.kim.api.role;

import org.kuali.rice.core.util.jaxb.AttributeSetAdapter;
import org.kuali.rice.core.util.jaxb.MapStringStringAdapter;
import org.kuali.rice.core.util.AttributeSet;
import org.kuali.rice.kim.api.KimConstants;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * This service provides operations for querying role and role qualification
 * data.
 *
 * <p>A role is where permissions and responsibilities are granted.  Roles have
 * a membership consisting of principals, groups or even other roles.  By
 * being assigned as members of a role, the associated principals will be
 * granted all permissions and responsibilities that have been granted to the
 * role.
 *
 * <p>Each membership assignment on the role can have a qualification which
 * defines extra information about that particular member of the role.  For
 * example, one may have the role of "Dean" but that can be further qualified
 * by the school they are the dean of, such as "Dean of Computer Science".
 * Authorization checks that are then done in the permission service can pass
 * qualifiers as part of the operation if they want to restrict the subset of
 * the role against which the check is made.
 *
 * <p>This service provides read-only operations.  For write operations, see
 * {@link RoleUpdateService}.
 *
 * @see RoleUpdateService
 * @see org.kuali.rice.kim.service.PermissionService
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
@WebService(name = "RoleService", targetNamespace = KimConstants.Namespaces.KIM_NAMESPACE_2_0 )
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface RoleService {
    // --------------------
    // Role Data
    // --------------------

	/**
	 * Get the KIM Role object with the given ID.
	 *
	 * If the roleId is blank, this method returns <code>null</code>.
	 */
    @WebMethod(operationName = "getRole")
    @WebResult(name = "role")
    Role getRole(@WebParam(name = "roleId") String roleId);

	/**
	 * Get the KIM Role objects for the role IDs in the given List.
	 */
    @WebMethod(operationName = "getRoles")
    @WebResult(name = "roles")
	List<Role> getRoles( @WebParam(name="roleIds") List<String> roleIds );

	/** Get the KIM Role object with the unique combination of namespace, component,
	 * and role name.
	 *
	 * If any parameter is blank, this method returns <code>null</code>.
	 */
    @WebMethod(operationName = "getRoleByName")
    @WebResult(name = "role")
    Role getRoleByName(@WebParam(name = "namespaceCode") String namespaceCode,
                       @WebParam(name = "roleName") String roleName);

	/**
	 * Return the Role ID for the given unique combination of namespace,
	 * component and role name.
	 */
    @WebMethod(operationName = "getRoleIdByName")
    @WebResult(name = "roleId")
	String getRoleIdByName( @WebParam(name="namespaceCode") String namespaceCode,
                            @WebParam(name="roleName") String roleName );

	/**
	 * Checks whether the role with the given role ID is active.
	 *
	 * @param roleId
	 * @return
	 */
    @WebMethod(operationName = "isRoleActive")
    @WebResult(name = "isRoleActive")
    boolean isRoleActive( @WebParam(name="roleId") String roleId );

    /**
     * Returns a list of role qualifiers that the given principal has without taking into consideration
     * that the principal may be a member via an assigned group or role.  Use in situations where
     * you are only interested in the qualifiers that are directly assigned to the principal.
     */
    @WebMethod(operationName = "getRoleQualifersForPrincipalByRoleIds")
    @WebResult(name = "attributeSets")
    List<AttributeSet> getRoleQualifiersForPrincipal(@WebParam(name="principalId") String principalId,
                                                     @WebParam(name="roleIds") List<String> roleIds,
                                                     @WebParam(name="qualification") @XmlJavaTypeAdapter(value = AttributeSetAdapter.class) AttributeSet qualification );

    /**
     * Returns a list of role qualifiers that the given principal has without taking into consideration
     * that the principal may be a member via an assigned group or role.  Use in situations where
     * you are only interested in the qualifiers that are directly assigned to the principal.
     */
    @WebMethod(operationName = "getRoleQualifersForPrincipalByNamespaceAndRolename")
    @WebResult(name = "attributeSets")
    List<AttributeSet> getRoleQualifiersForPrincipal(@WebParam(name="principalId") String principalId,
                                                     @WebParam(name="namespaceCode") String namespaceCode,
                                                     @WebParam(name="roleName") String roleName,
                                                     @WebParam(name="qualification") @XmlJavaTypeAdapter(value = AttributeSetAdapter.class) AttributeSet qualification );

    /**
     * Returns a list of role qualifiers that the given principal.  If the principal's membership
     * is via a group or role, that group or role's qualifier on the given role is returned.
     */
    @WebMethod(operationName = "getNestedRoleQualifersForPrincipalByNamespaceAndRolename")
    @WebResult(name = "attributeSets")
	List<AttributeSet> getNestedRoleQualifiersForPrincipal(@WebParam(name = "principalId") String principalId,
                                                           @WebParam(name = "namespaceCode") String namespaceCode,
                                                           @WebParam(name = "roleName") String roleName,
                                                           @WebParam(name = "qualification") @XmlJavaTypeAdapter(value = AttributeSetAdapter.class) AttributeSet qualification);

    /**
     * Returns a list of role qualifiers that the given principal.  If the principal's membership
     * is via a group or role, that group or role's qualifier on the given role is returned.
     */
    @WebMethod(operationName = "getNestedRoleQualifiersForPrincipalByRoleIds")
    @WebResult(name = "attributeSets")
	List<AttributeSet> getNestedRoleQualifiersForPrincipal(@WebParam(name = "principalId") String principalId, @WebParam(name = "roleIds") List<String> roleIds, @WebParam(name = "qualification") @XmlJavaTypeAdapter(value = AttributeSetAdapter.class) AttributeSet qualification);


    // --------------------
    // Role Membership Checks
    // --------------------

    /**
     * Get all the role members (groups and principals) associated with the given list of roles
     * where their role membership/assignment matches the given qualification.
     *
     * The return object will have each membership relationship along with the delegations
     *
     */
    @WebMethod(operationName = "getRoleMembers")
    @WebResult(name = "roleMemberships")
    List<RoleMembership> getRoleMembers( @WebParam(name="roleIds") List<String> roleIds,
                                             @WebParam(name="qualification") @XmlJavaTypeAdapter(value = AttributeSetAdapter.class) AttributeSet qualification );

    /**
	 * This method gets all the members, then traverses down into members of type role and group to obtain the nested principal ids
	 *
	 * @return list of member principal ids
	 */
    @WebMethod(operationName = "getRoleMemberPrincipalIds")
    @WebResult(name = "principalIds")
    Collection<String> getRoleMemberPrincipalIds(@WebParam(name="namespaceCode") String namespaceCode,
                                                 @WebParam(name="roleName") String roleName,
                                                 @WebParam(name="qualification") @XmlJavaTypeAdapter(value = AttributeSetAdapter.class) AttributeSet qualification);

    /**
     * Returns whether the given principal has any of the passed role IDs with the given qualification.
     */
    @WebMethod(operationName = "principalHasRole")
    @WebResult(name = "principalHasRole")
    boolean principalHasRole( @WebParam(name="principalId") String principalId,
                              @WebParam(name="roleIds") List<String> roleIds,
                              @WebParam(name="qualification") @XmlJavaTypeAdapter(value = AttributeSetAdapter.class) AttributeSet qualification );

    /**
     * Returns the subset of the given principal ID list which has the given role and qualification.
     * This is designed to be used by lookups of people by their roles.
     */
    @WebMethod(operationName = "getPrincipalIdSubListWithRole")
    @WebResult(name = "principalIds")
    List<String> getPrincipalIdSubListWithRole( @WebParam(name="principalIds") List<String> principalIds,
                                                @WebParam(name="roleNamespaceCode") String roleNamespaceCode,
                                                @WebParam(name="roleName") String roleName,
                                                @WebParam(name="qualification") @XmlJavaTypeAdapter(value = AttributeSetAdapter.class) AttributeSet qualification );

    /**
	 *
	 * This method gets search results for role lookup
	 */
    @WebMethod(operationName = "getRolesSearchResults")
    @WebResult(name = "roles")
	List<? extends Role> getRolesSearchResults(
            @XmlJavaTypeAdapter(value = MapStringStringAdapter.class) @WebParam(name = "fieldValues") Map<String,String> fieldValues);

	/**
	 * Notifies all of a principal's roles and role types that the principal has been inactivated.
	 */
    @WebMethod(operationName = "principalInactivated")
	void principalInactivated( @WebParam(name="principalId") String principalId );

	/**
	 * Notifies the role service that the role with the given id has been inactivated.
	 */
    @WebMethod(operationName = "roleInactivated")
	void roleInactivated(@WebParam(name="roleId") String roleId);

	/**
	 * Notifies the role service that the group with the given id has been inactivated.
	 */
    @WebMethod(operationName = "groupInactivated")
    void groupInactivated(@WebParam(name="groupId") String groupId);

    /**
     * Gets all direct members of the roles that have ids within the given list
     * of role ids.  This method does not recurse into any nested roles.
     *
     *  <p>The resulting List of role membership will contain membership for
     *  all the roles with the specified ids.  The list is not guaranteed to be
     *  in any particular order and may have membership info for the
     *  different roles interleaved with each other.
     */
    @WebMethod(operationName = "getFirstLevelRoleMembers")
    @WebResult(name = "roleMemberships")
	List<RoleMembership> getFirstLevelRoleMembers(@WebParam(name="roleIds") List<String> roleIds);

	/**
	 * Gets role member information based on the given search criteria.  The
	 * map of criteria contains attributes of RoleMembership as it's
	 * key and the values to search on as the value.
	 */
    @WebMethod(operationName = "findRoleMembers")
    @WebResult(name = "roleMemberships")
	List<RoleMembership> findRoleMembers(
            @XmlJavaTypeAdapter(value = MapStringStringAdapter.class) @WebParam(name="fieldValues") java.util.Map<String, String> fieldValues);

	/**
	 * Gets a list of Roles that the given member belongs to.
	 */
    @WebMethod(operationName = "getMemberParentRoleIds")
    @WebResult(name = "roleIds")
	List<String> getMemberParentRoleIds(String memberType, String memberId);


    @WebMethod(operationName = "findRoleMembersCompleteInfo")
    @WebResult(name = "roleMemberInfos")
	List<RoleMember> findRoleMembersCompleteInfo(
            @XmlJavaTypeAdapter(value = MapStringStringAdapter.class) @WebParam(name="fieldValues") java.util.Map<String, String> fieldValues);

    @WebMethod(operationName = "findDelegateMembersCompleteInfo")
    @WebResult(name = "delegateMemberInfos")
    List<Delegate> findDelegateMembersCompleteInfo
            (@XmlJavaTypeAdapter(value = MapStringStringAdapter.class) @WebParam(name = "fieldValues") java.util.Map<String, String> fieldValues);

	/**
	 * Gets delegation member information based on the given search criteria.  The
	 * map of criteria contains attributes of Delegate as it's
	 * key and the values to search on as the value.
	 */
    @WebMethod(operationName = "getDelegationMembersByDelegationId")
    @WebResult(name = "delegateMemberInfos")
    List<Delegate> getDelegationMembersByDelegationId(
            @WebParam(name = "delegationId") String delegationId);

    @WebMethod(operationName = "getDelegationMemberByDelegationAndMemberId")
    @WebResult(name = "delegateMemberInfo")
    Delegate getDelegationMemberByDelegationAndMemberId(
            @WebParam(name = "delegationId") String delegationId, @WebParam(name = "memberId") String memberId);

    @WebMethod(operationName = "getDelegationMemberById")
    @WebResult(name = "delegateMemberInfo")
    Delegate getDelegationMemberById(@WebParam(name = "delegationMemberId") String delegationMemberId);

    @WebMethod(operationName = "getRoleResponsibilities")
    @WebResult(name = "roleResponsibilities")
	List<RoleResponsibility> getRoleResponsibilities(@WebParam(name="roleId") String roleId);

    @WebMethod(operationName = "getRoleMemberResponsibilityActionInfo")
    @WebResult(name = "roleResponsibilityActions")
	List<RoleResponsibilityAction> getRoleMemberResponsibilityActionInfo(
            @WebParam(name="roleMemberId") String roleMemberId);

    @WebMethod(operationName = "getDelegateTypeInfo")
    @WebResult(name = "delegateType")
    DelegateType getDelegateTypeInfo(
            @WebParam(name="roleId") String roleId, @WebParam(name="delegationTypeCode") String delegationTypeCode);

    @WebMethod(operationName = "getDelegateTypeInfoById")
    @WebResult(name = "delegateType")
    DelegateType getDelegateTypeInfoById( @WebParam(name="delegationId") String delegationId);

	@WebMethod(operationName = "applicationRoleMembershipChanged")
    void applicationRoleMembershipChanged( @WebParam(name="roleId") String roleId );

    @WebMethod(operationName = "lookupRoles")
    @WebResult(name = "roles")
	List<Role> lookupRoles(@WebParam(name="searchCriteria") @XmlJavaTypeAdapter(value = MapStringStringAdapter.class) Map<String, String> searchCriteria);

	/**
	 * Flushes an internal role cache used by the base implementation to prevent repeated database I/O.
	 */
    @WebMethod(operationName = "flushInternalRoleCache")
	void flushInternalRoleCache();

	/**
	 * Flushes an internal role member cache used by the base implementation to prevent repeated database I/O.
	 */
    @WebMethod(operationName = "flushInternalRoleMemberCache")
	void flushInternalRoleMemberCache();

	/**
	 * Flushes an internal delegation cache used by the base implementation to prevent repeated database I/O.
	 */
    @WebMethod(operationName = "flushInternalDelegationCache")
	void flushInternalDelegationCache();

	/**
	 * Flushes an internal delegation member cache used by the base implementation to prevent repeated database I/O.
	 */
    @WebMethod(operationName = "flushInternalDelegationMemberCache")
	void flushInternalDelegationMemberCache();
}
