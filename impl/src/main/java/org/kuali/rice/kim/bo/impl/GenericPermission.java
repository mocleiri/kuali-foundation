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
package org.kuali.rice.kim.bo.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Type;
import org.kuali.rice.kim.impl.permission.PermissionBo;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */

@SuppressWarnings("unchecked")
@Entity
@Table(name="KRIM_PERM_T")
public class GenericPermission extends PersistableBusinessObjectBase {
//	private static final Logger LOG = Logger.getLogger(GenericPermission.class);	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PERM_ID")
	protected String permissionId;
	@Column(name="NMSPC_CD")
	protected String namespaceCode;
	@Column(name="NM")
	protected String name;
	@Column(name="DESC_TXT", length=400)
	protected String description;
	@Type(type="yes_no")
	@Column(name="ACTV_IND")
	protected boolean active;
	@Column(name="PERM_TMPL_ID")
	protected String templateId;
	@Transient
	protected String detailValues;
	@Transient
	protected Map<String, String> details;
	
	/**
	 * This constructs a ...
	 * 
	 */
	public GenericPermission() {
	}
	
	public GenericPermission( PermissionBo perm ) {
		loadFromKimPermission( perm );
	}
	public void loadFromKimPermission( PermissionBo perm ) {
		setPermissionId( perm.getId() );
		setNamespaceCode( perm.getNamespaceCode() );
		setName( perm.getName() );
		setTemplateId( perm.getTemplateId() );
		setDescription( perm.getDescription() );
		setActive( perm.isActive() );
		setDetails( perm.getDetails() );
	}
	
	public String getDetailValues() {
		/*StringBuffer sb = new StringBuffer();
		if ( details != null ) {
			Iterator<String> keyIter = details.keySet().iterator();
			while ( keyIter.hasNext() ) {
				String key = keyIter.next();
				sb.append( key ).append( '=' ).append( details.get( key ) );
				if ( keyIter.hasNext() ) {
					sb.append( '\n' );
				}
			}
		}
		return sb.toString();*/
		return detailValues;
	}
	
	public void setDetailValues( String detailValues ) {
		this.detailValues = detailValues;
		String detailValuesTemp = detailValues;
		Map<String,String> details = new HashMap<String,String>();
		if ( detailValuesTemp != null ) {
			// ensure that all line delimiters are single linefeeds
			detailValuesTemp = detailValuesTemp.replace( "\r\n", "\n" );
			detailValuesTemp = detailValuesTemp.replace( '\r', '\n' );
			if ( StringUtils.isNotBlank( detailValuesTemp ) ) {
				String[] values = detailValuesTemp.split( "\n" );
				for ( String attrib : values ) {
					if ( attrib.indexOf( '=' ) != -1 ) {
						String[] keyValueArray = attrib.split( "=", 2 );
						details.put( keyValueArray[0].trim(), keyValueArray[1].trim() );
					}
				}
			}
		}
		this.details = details;
	}
	
	public void setDetailValues( Map<String, String> detailsAttribs ) {
		StringBuffer sb = new StringBuffer();
		if ( detailsAttribs != null ) {
			Iterator<String> keyIter = detailsAttribs.keySet().iterator();
			while ( keyIter.hasNext() ) {
				String key = keyIter.next();
				sb.append( key ).append( '=' ).append( detailsAttribs.get( key ) );
				if ( keyIter.hasNext() ) {
					sb.append( '\n' );
				}
			}
		}
		detailValues = sb.toString();
	}
	
	/**
	 * @see org.kuali.rice.krad.bo.MutableInactivatable#isActive()
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @see org.kuali.rice.krad.bo.MutableInactivatable#setActive(boolean)
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @see org.kuali.rice.kim.bo.role.KimPermission#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @see org.kuali.rice.kim.bo.role.KimPermission#getPermissionId()
	 */
	public String getPermissionId() {
		return permissionId;
	}

	/**
	 * @see org.kuali.rice.kim.bo.role.KimPermission#getName()
	 */
	public String getName() {
		return name;
	}

	public void setDescription(String permissionDescription) {
		this.description = permissionDescription;
	}

	public void setName(String permissionName) {
		this.name = permissionName;
	}

	public void setDetails( Map<String, String> details ) {
		this.details = details;
		setDetailValues(details);
	}
	
	public String getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public Map<String, String> getDetails() {
		return details;
	}
	
	public String getNamespaceCode() {
		return this.namespaceCode;
	}

	public void setNamespaceCode(String namespaceCode) {
		this.namespaceCode = namespaceCode;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}


	public void refresh() {
		// do nothing - not a persistable object
	}
	
	@Override
	public void refreshNonUpdateableReferences() {
		// do nothing - not a persistable object
	}
	@Override
	public void refreshReferenceObject(String referenceObjectName) {
		// do nothing - not a persistable object
	}

	@Override
	protected void prePersist() {
		throw new UnsupportedOperationException( "This object should never be persisted.");
	}
	
	@Override
	protected void preUpdate() {
		throw new UnsupportedOperationException( "This object should never be persisted.");
	}

	@Override
	protected void preRemove() {
		throw new UnsupportedOperationException( "This object should never be persisted.");
	}

}
