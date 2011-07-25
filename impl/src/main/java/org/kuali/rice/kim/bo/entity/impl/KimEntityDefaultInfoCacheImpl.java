/*
 * Copyright 2008-2009 The Kuali Foundation
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
package org.kuali.rice.kim.bo.entity.impl;

import org.kuali.rice.kim.api.identity.affiliation.EntityAffiliation;
import org.kuali.rice.kim.api.identity.employment.EntityEmployment;
import org.kuali.rice.kim.api.identity.entity.EntityDefault;
import org.kuali.rice.kim.api.identity.external.EntityExternalIdentifier;
import org.kuali.rice.kim.api.identity.name.EntityName;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.kim.api.identity.type.EntityTypeContactInfoDefault;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.Collections;

/**
 * Used to store a cache of person information to be used if the user's information disappears from KIM. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
@Entity
@Table(name="KRIM_ENTITY_CACHE_T")
public class KimEntityDefaultInfoCacheImpl extends PersistableBusinessObjectBase {

	private static final long serialVersionUID = 1L;

	@Transient
	protected Long versionNumber; // prevent JPA from attempting to persist the version number attribute
	
	// principal data
	@Id
	@Column(name="PRNCPL_ID")
	protected String principalId;
	@Column(name="PRNCPL_NM")
	protected String principalName;
	@Column(name="ENTITY_ID")
	protected String entityId;
	@Column(name="ENTITY_TYP_CD")
	protected String entityTypeCode;

	// name data
	@Column(name="FIRST_NM")
	protected String firstName = "";
	@Column(name="MIDDLE_NM")
	protected String middleName = "";
	@Column(name="LAST_NM")
	protected String lastName = "";
	@Column(name="PRSN_NM")
	protected String name = "";
	
	@Column(name="CAMPUS_CD")
	protected String campusCode = "";

	// employment data
	@Column(name="PRMRY_DEPT_CD")
	protected String primaryDepartmentCode = "";
	@Column(name="EMP_ID")
	protected String employeeId = "";
	
	@Column(name="LAST_UPDT_TS")
	protected Timestamp lastUpdateTimestamp;

	/**
	 * 
	 */
	public KimEntityDefaultInfoCacheImpl() {
	}
	
	public KimEntityDefaultInfoCacheImpl( EntityDefault entity ) {
		if ( entity != null ) {
			entityId = entity.getEntityId();
			if ( entity.getPrincipals() != null && !entity.getPrincipals().isEmpty() ) {
				principalId = entity.getPrincipals().get(0).getPrincipalId();
				principalName = entity.getPrincipals().get(0).getPrincipalName();
			}
			if ( entity.getEntityTypeContactInfos() != null && !entity.getEntityTypeContactInfos().isEmpty() ) {
				entityTypeCode = entity.getEntityTypeContactInfos().get(0).getEntityTypeCode();
			}
			if ( entity.getName() != null ) {
				firstName = entity.getName().getFirstNameUnmasked();
				middleName = entity.getName().getMiddleNameUnmasked();
				lastName = entity.getName().getLastNameUnmasked();
				name = entity.getName().getFormattedNameUnmasked();
			}
			if ( entity.getDefaultAffiliation() != null ) {
				campusCode = entity.getDefaultAffiliation().getCampusCode();
			}
			if ( entity.getEmployment() != null ) {
				primaryDepartmentCode = entity.getEmployment().getPrimaryDepartmentCode();
				employeeId = entity.getEmployment().getEmployeeId();
			}
		}
	}

    @SuppressWarnings("unchecked")
	public EntityDefault convertCacheToEntityDefaultInfo() {
		EntityDefault.Builder info = EntityDefault.Builder.create(this.entityId);
		
		// identity info
		info.setActive( this.isActive() );

		// principal info
		Principal.Builder principalInfo = Principal.Builder.create(this.getPrincipalName());
		principalInfo.setEntityId(this.getEntityId());
		principalInfo.setPrincipalId(this.getPrincipalId());
		principalInfo.setActive(this.isActive());
		info.setPrincipals(Collections.singletonList(principalInfo));

		// name info
		EntityName.Builder nameInfo = EntityName.Builder.create();
        nameInfo.setEntityId( this.getEntityId());
		nameInfo.setFirstName( this.getFirstName() );
		nameInfo.setLastName( this.getLastName() );
		nameInfo.setMiddleName( this.getMiddleName() );
		info.setName(nameInfo);

		// identity type information
		EntityTypeContactInfoDefault.Builder entityTypeInfo = EntityTypeContactInfoDefault.Builder.create();
        entityTypeInfo.setEntityTypeCode(this.getEntityTypeCode());
		info.setEntityTypeContactInfos(Collections.singletonList(entityTypeInfo));

		// affiliations
		EntityAffiliation.Builder aff = EntityAffiliation.Builder.create();
		aff.setCampusCode(this.getCampusCode());
		aff.setDefaultValue(true);
        aff.setEntityId(info.getEntityId());
		info.setDefaultAffiliation(aff);
		info.setAffiliations(Collections.singletonList(aff));

		// employment information
		EntityEmployment.Builder empInfo = EntityEmployment.Builder.create();
		empInfo.setEmployeeId( this.getEmployeeId() );
		empInfo.setPrimary(true);
		empInfo.setPrimaryDepartmentCode(this.getPrimaryDepartmentCode());
		info.setEmployment(empInfo);
		
		// external identifiers
		info.setExternalIdentifiers( Collections.singletonList(EntityExternalIdentifier.Builder.create()) );
		return info.build();
    	
    }

	public String getPrincipalId() {
		return this.principalId;
	}


	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}


	public String getPrincipalName() {
		return this.principalName;
	}


	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}


	public String getEntityId() {
		return this.entityId;
	}


	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}


	public String getEntityTypeCode() {
		return this.entityTypeCode;
	}


	public void setEntityTypeCode(String entityTypeCode) {
		this.entityTypeCode = entityTypeCode;
	}


	public String getFirstName() {
		return this.firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		return this.middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return this.lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCampusCode() {
		return this.campusCode;
	}


	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}


	public String getPrimaryDepartmentCode() {
		return this.primaryDepartmentCode;
	}


	public void setPrimaryDepartmentCode(String primaryDepartmentCode) {
		this.primaryDepartmentCode = primaryDepartmentCode;
	}


	public String getEmployeeId() {
		return this.employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public boolean isActive() {
		return false;
	}

	public Timestamp getLastUpdateTimestamp() {
		return this.lastUpdateTimestamp;
	}

	// handle automatic updating of the timestamp
	
	@Override
    protected void prePersist() {
    	super.prePersist();
        lastUpdateTimestamp = new Timestamp(System.currentTimeMillis());
    }

	@Override
    protected void preUpdate() {
    	super.preUpdate();
        lastUpdateTimestamp = new Timestamp(System.currentTimeMillis());
    }
	
}
