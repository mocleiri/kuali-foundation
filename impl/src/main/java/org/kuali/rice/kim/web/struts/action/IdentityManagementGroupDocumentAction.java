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
package org.kuali.rice.kim.web.struts.action;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.rice.core.util.RiceConstants;
import org.kuali.rice.core.util.RiceKeyConstants;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kim.api.group.Group;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kim.api.type.KimType;
import org.kuali.rice.kim.bo.Role;
import org.kuali.rice.kim.bo.entity.dto.KimPrincipalInfo;
import org.kuali.rice.kim.bo.role.dto.KimRoleInfo;
import org.kuali.rice.kim.bo.ui.GroupDocumentMember;
import org.kuali.rice.kim.document.IdentityManagementGroupDocument;
import org.kuali.rice.kim.rule.event.ui.AddGroupMemberEvent;
import org.kuali.rice.kim.util.KimConstants;
import org.kuali.rice.kim.web.struts.form.IdentityManagementGroupDocumentForm;
import org.kuali.rice.kns.service.KNSServiceLocatorWeb;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiTableRenderFormMetadata;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class IdentityManagementGroupDocumentAction extends IdentityManagementDocumentActionBase {

	/**
	 * This constructs a ...
	 * 
	 */
	public IdentityManagementGroupDocumentAction() {
		super();
		addMethodToCallToUncheckedList( CHANGE_MEMBER_TYPE_CODE_METHOD_TO_CALL );
		addMethodToCallToUncheckedList( CHANGE_NAMESPACE_METHOD_TO_CALL );
	}
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
        IdentityManagementGroupDocumentForm groupDocumentForm = (IdentityManagementGroupDocumentForm) form;
        if ( StringUtils.isBlank( groupDocumentForm.getGroupId() ) ) {
            String groupId = request.getParameter(KimConstants.PrimaryKeyConstants.GROUP_ID);
        	groupDocumentForm.setGroupId(groupId);
        }
		String kimTypeId = request.getParameter(KimConstants.PrimaryKeyConstants.KIM_TYPE_ID);
        setKimType(kimTypeId, groupDocumentForm);

        
		KualiTableRenderFormMetadata memberTableMetadata = groupDocumentForm.getMemberTableMetadata();
		if (groupDocumentForm.getMemberRows() != null) {
			memberTableMetadata.jumpToPage(memberTableMetadata.getViewedPageNumber(), groupDocumentForm.getMemberRows().size(), groupDocumentForm.getRecordsPerPage());
		}
		
		ActionForward forward = super.execute(mapping, groupDocumentForm, request, response);
		
		groupDocumentForm.setCanAssignGroup(validAssignGroup(groupDocumentForm.getGroupDocument()));
		return forward;
    }
    
    protected void setKimType(String kimTypeId, IdentityManagementGroupDocumentForm groupDocumentForm){
		if ( StringUtils.isNotBlank(kimTypeId) ) {
			KimType kType = KimApiServiceLocator.getKimTypeInfoService().getKimType(kimTypeId);
			groupDocumentForm.setKimType(kType);
			if (groupDocumentForm.getGroupDocument() != null) {
				groupDocumentForm.getGroupDocument().setKimType(kType);
			}
		} else if ( groupDocumentForm.getGroupDocument() != null && StringUtils.isNotBlank(groupDocumentForm.getGroupDocument().getGroupTypeId() ) ) {
			groupDocumentForm.setKimType(KimApiServiceLocator.getKimTypeInfoService().getKimType(
					groupDocumentForm.getGroupDocument().getGroupTypeId()));
			groupDocumentForm.getGroupDocument().setKimType(groupDocumentForm.getKimType());
		}
    }
    
    /**
     * This overridden method ...
     * 
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#loadDocument(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    protected void loadDocument(KualiDocumentFormBase form)
    		throws WorkflowException {
    	super.loadDocument(form);
    	
    	IdentityManagementGroupDocumentForm groupDocumentForm = (IdentityManagementGroupDocumentForm) form;
        setKimType(groupDocumentForm.getGroupId(), groupDocumentForm);
        groupDocumentForm.setMember(groupDocumentForm.getGroupDocument().getBlankMember());

		KualiTableRenderFormMetadata memberTableMetadata = groupDocumentForm.getMemberTableMetadata();
		if (groupDocumentForm.getMemberRows() != null) {
		    memberTableMetadata.jumpToFirstPage(groupDocumentForm.getMemberRows().size(), groupDocumentForm.getRecordsPerPage());
		}
    }
    
    /**
     * This overridden method ...
     * 
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#createDocument(org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    protected void createDocument(KualiDocumentFormBase form)
    		throws WorkflowException {
    	super.createDocument(form);
    	IdentityManagementGroupDocumentForm groupDocumentForm = (IdentityManagementGroupDocumentForm) form;
    	if ( groupDocumentForm.getGroupId() == null ) {
    		groupDocumentForm.getGroupDocument().setKimType(groupDocumentForm.getKimType());
    		groupDocumentForm.getGroupDocument().initializeDocumentForNewGroup();
    		groupDocumentForm.setGroupId( groupDocumentForm.getGroupDocument().getGroupId() );
    		setKimType(groupDocumentForm.getGroupDocument().getGroupTypeId(), groupDocumentForm);
    	} else {
    		loadGroupIntoDocument( groupDocumentForm.getGroupId(), groupDocumentForm );
    	}
		KualiTableRenderFormMetadata memberTableMetadata = groupDocumentForm.getMemberTableMetadata();
		if (groupDocumentForm.getMemberRows() != null) {
		    memberTableMetadata.jumpToFirstPage(groupDocumentForm.getMemberRows().size(), groupDocumentForm.getRecordsPerPage());
		}
    }


    protected void loadGroupIntoDocument( String groupId, IdentityManagementGroupDocumentForm groupDocumentForm){
        Group group = KimApiServiceLocator.getGroupService().getGroup(groupId);
        getUiDocumentService().loadGroupDoc(groupDocumentForm.getGroupDocument(), group);
    }    
        
	/***
	 * @see org.kuali.rice.kim.web.struts.action.IdentityManagementDocumentActionBase#getActionName()
	 */
	public String getActionName(){
		return KimConstants.KimUIConstants.KIM_GROUP_DOCUMENT_ACTION;
	}
	
	protected boolean validAssignGroup(IdentityManagementGroupDocument document){
        boolean rulePassed = true;
        Map<String,String> additionalPermissionDetails = new HashMap<String,String>();
        if (!StringUtils.isEmpty(document.getGroupNamespace())) {
        	additionalPermissionDetails.put(KimConstants.AttributeConstants.NAMESPACE_CODE, document.getGroupNamespace());
        	additionalPermissionDetails.put(KimConstants.AttributeConstants.GROUP_NAME, document.getGroupName());
        	if (!getDocumentHelperService().getDocumentAuthorizer(document).isAuthorizedByTemplate(
        			document, 
        			KimConstants.NAMESPACE_CODE, 
        			KimConstants.PermissionTemplateNames.POPULATE_GROUP, 
        			GlobalVariables.getUserSession().getPrincipalId(), 
        			additionalPermissionDetails, null)){
        		rulePassed = false;
        	}
        }
		return rulePassed;
	}

	public ActionForward changeMemberTypeCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IdentityManagementGroupDocumentForm groupDocumentForm = (IdentityManagementGroupDocumentForm) form;
        groupDocumentForm.getMember().setMemberName("");
        return refresh(mapping, groupDocumentForm, request, response);
	}	
	
    public ActionForward addMember(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IdentityManagementGroupDocumentForm groupDocumentForm = (IdentityManagementGroupDocumentForm) form;
        GroupDocumentMember newMember = groupDocumentForm.getMember();
        
        //See if possible to add with just Group Details filled in (not returned from lookup)
        if (StringUtils.isEmpty(newMember.getMemberId()) 
        		&& StringUtils.isNotEmpty(newMember.getMemberName())
        		&& StringUtils.isNotEmpty(newMember.getMemberNamespaceCode())
        		&& StringUtils.equals(newMember.getMemberTypeCode(), KimConstants.KimGroupMemberTypes.GROUP_MEMBER_TYPE)) {
        	Group tempGroup = KimApiServiceLocator.getIdentityManagementService().getGroupByName(newMember.getMemberNamespaceCode(), newMember.getMemberName());
        	if (tempGroup != null) {
        		newMember.setMemberId(tempGroup.getId());
        	}
        }
        
        //See if possible to grab details for Principal
        if (StringUtils.isEmpty(newMember.getMemberId()) 
        		&& StringUtils.isNotEmpty(newMember.getMemberName())
        		&& StringUtils.equals(newMember.getMemberTypeCode(), KimConstants.KimGroupMemberTypes.PRINCIPAL_MEMBER_TYPE)) {
        	KimPrincipalInfo principal = KimApiServiceLocator.getIdentityManagementService().getPrincipalByPrincipalName(newMember.getMemberName());
        	if (principal != null) {
        		newMember.setMemberId(principal.getPrincipalId());
        	}
        }
        if(checkKimDocumentGroupMember(newMember) && 
        		KNSServiceLocatorWeb.getKualiRuleService().applyRules(new AddGroupMemberEvent("", groupDocumentForm.getGroupDocument(), newMember))){
        	newMember.setDocumentNumber(groupDocumentForm.getDocument().getDocumentNumber());
        	groupDocumentForm.getGroupDocument().addMember(newMember);
	        groupDocumentForm.setMember(groupDocumentForm.getGroupDocument().getBlankMember());
	        groupDocumentForm.getMemberTableMetadata().jumpToLastPage(groupDocumentForm.getMemberRows().size(), groupDocumentForm.getRecordsPerPage());
       }
        return mapping.findForward(RiceConstants.MAPPING_BASIC);
    }

    protected boolean checkKimDocumentGroupMember(GroupDocumentMember newMember){
        if(StringUtils.isBlank(newMember.getMemberTypeCode()) || StringUtils.isBlank(newMember.getMemberId())){
        	GlobalVariables.getMessageMap().putError("document.member.memberId", RiceKeyConstants.ERROR_EMPTY_ENTRY,
        			new String[] {"Member Type Code and Member ID"});
        	return false;
		}
    	if(KimConstants.KimUIConstants.MEMBER_TYPE_PRINCIPAL_CODE.equals(newMember.getMemberTypeCode())){
        	KimPrincipalInfo principalInfo = null;
        	principalInfo = getIdentityManagementService().getPrincipal(newMember.getMemberId());
        	if (principalInfo == null) {
        		GlobalVariables.getMessageMap().putError("document.member.memberId", RiceKeyConstants.ERROR_MEMBERID_MEMBERTYPE_MISMATCH,
            			new String[] {newMember.getMemberId()});
            	return false;
        	}
        	else {
        		newMember.setMemberName(principalInfo.getPrincipalName());
        	}
        } else if(KimConstants.KimUIConstants.MEMBER_TYPE_GROUP_CODE.equals(newMember.getMemberTypeCode())){
        	Group groupInfo = null;
        	groupInfo = getIdentityManagementService().getGroup(newMember.getMemberId());
        	if (groupInfo == null) {
        		GlobalVariables.getMessageMap().putError("document.member.memberId", RiceKeyConstants.ERROR_MEMBERID_MEMBERTYPE_MISMATCH,
            			new String[] {newMember.getMemberId()});
            	return false;
        	}
        	else {
        		newMember.setMemberName(groupInfo.getName());
                newMember.setMemberNamespaceCode(groupInfo.getNamespaceCode());
        	}
        } else if(KimConstants.KimUIConstants.MEMBER_TYPE_ROLE_CODE.equals(newMember.getMemberTypeCode())){
        	KimRoleInfo roleInfo = null;
        	roleInfo = KimApiServiceLocator.getRoleService().getRole(newMember.getMemberId());
        	if (roleInfo == null) {
        		GlobalVariables.getMessageMap().putError("document.member.memberId", RiceKeyConstants.ERROR_MEMBERID_MEMBERTYPE_MISMATCH,
            			new String[] {newMember.getMemberId()});
            	return false;
        	}
        	else if(StringUtils.equals(newMember.getMemberTypeCode(), KimConstants.KimUIConstants.MEMBER_TYPE_ROLE_CODE) 
            		&& !validateRole(newMember.getMemberId(), (Role)roleInfo, "document.member.memberId", "Role")){
            	return false;
            }
        	else {
        		newMember.setMemberName(roleInfo.getRoleName());
                newMember.setMemberNamespaceCode(roleInfo.getNamespaceCode());
        	}
		}
        return true;
    }
    
    public ActionForward deleteMember(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IdentityManagementGroupDocumentForm groupDocumentForm = (IdentityManagementGroupDocumentForm) form;
        GroupDocumentMember memberToBeInactivated = groupDocumentForm.getGroupDocument().getMembers().get(getLineToDelete(request));    
        Calendar cal = Calendar.getInstance();
        memberToBeInactivated.setActiveToDate(new Timestamp(cal.getTimeInMillis()));        
        groupDocumentForm.getGroupDocument().getMembers().set(getLineToDelete(request), memberToBeInactivated);   
        groupDocumentForm.setMember(groupDocumentForm.getGroupDocument().getBlankMember());
        return mapping.findForward(RiceConstants.MAPPING_BASIC);
    }
    
}
