/*
 * Copyright 2005-2008 The Kuali Foundation
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
package org.kuali.rice.kns.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * This Compound Primary Class has been generated by the rice ojb2jpa Groovy script.  Please
 * note that there are no setter methods, only getters.  This is done purposefully as cpk classes
 * can not change after they have been created.  Also note they require a public no-arg constructor.
 * TODO: Implement the equals() and hashCode() methods. 
 */
public class AdHocRoutePersonId implements Serializable {

    private static final long serialVersionUID = -2030160650786561367L;
    
	@Column(name="RECIP_TYP_CD")
    private Integer type;
    @Id
    @Column(name="ACTN_RQST_CD")
    private String actionRequested;
    @Id
    @Column(name="ACTN_RQST_RECIP_ID")
    private String id;

    public AdHocRoutePersonId() {}
    
    public AdHocRoutePersonId(Integer type, String actionRequested, String id) {
    	this.type = type;
    	this.actionRequested = actionRequested;
    	this.id = id;
    }

    public Integer getType() {
        return this.type;
    }

    public String getActionRequested() {
        return this.actionRequested;
    }

    public String getId() {
        return this.id;
    }
        
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AdHocRoutePersonId)) return false;
        if (o == null) return false;
        AdHocRoutePersonId pk = (AdHocRoutePersonId) o;
        return getType() != null && getActionRequested() != null && getId() != null && getType().equals(pk.getType()) && getActionRequested().equals(pk.getActionRequested()) && getId().equals(pk.getId());        
    }

    public int hashCode() {
    	return new HashCodeBuilder().append(type).append(actionRequested).append(id).toHashCode();
    }

}

