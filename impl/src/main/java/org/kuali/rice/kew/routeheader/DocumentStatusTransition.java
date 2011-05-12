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
package org.kuali.rice.kew.routeheader;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.kuali.rice.kns.bo.PersistableBusinessObjectBase;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * Model bean representing the valid application document statuses for a document type
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
@Entity
@Table(name="KREW_APP_DOC_STAT_TRAN_T")
public class DocumentStatusTransition extends PersistableBusinessObjectBase {
	private static final long serialVersionUID = -2212481684546954746L;

	@Id
	@GeneratedValue(generator="KREW_DOC_HDR_S")
	@GenericGenerator(name="KREW_DOC_HDR_S",strategy="org.hibernate.id.enhanced.SequenceStyleGenerator",parameters={
			@Parameter(name="sequence_name",value="KREW_DOC_HDR_S"),
			@Parameter(name="value_column",value="id")
	})
	@Column(name="APP_DOC_STAT_TRAN_ID")
	private Long statusTransitionId;
	
	@Column(name="DOC_HDR_ID")
	private String documentId;
 	
	@Column(name="APP_DOC_STAT_FROM")
	private String oldAppDocStatus;
	
	@Column(name="APP_DOC_STAT_TO")
	private String newAppDocStatus;
	
	@Column(name="STAT_TRANS_DATE")
	private java.sql.Timestamp statusTransitionDate;
	
    public DocumentStatusTransition() {
    }

    public DocumentStatusTransition(String documentId, String oldStatus, String newStatus) {
    	this.documentId = documentId;
    	this.oldAppDocStatus = oldStatus;
    	this.newAppDocStatus = newStatus;
    	this.statusTransitionDate = new Timestamp(System.currentTimeMillis());
    }

	public Long getStatusTransitionId() {
		return this.statusTransitionId;
	}

	public void setStatusTransitionId(Long statusTransitionId) {
		this.statusTransitionId = statusTransitionId;
	}

	public String getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getOldAppDocStatus() {
		return this.oldAppDocStatus;
	}

	public void setOldAppDocStatus(String oldAppDocStatus) {
		this.oldAppDocStatus = oldAppDocStatus;
	}

	public String getNewAppDocStatus() {
		return this.newAppDocStatus;
	}

	public void setNewAppDocStatus(String newAppDocStatus) {
		this.newAppDocStatus = newAppDocStatus;
	}

	public java.sql.Timestamp getStatusTransitionDate() {
		return this.statusTransitionDate;
	}

	public void setStatusTransitionDate(java.sql.Timestamp statusTransitionDate) {
		this.statusTransitionDate = statusTransitionDate;
	}
}
