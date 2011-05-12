/*
 * Copyright 2007 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.kns.util;

/**
 * Defines the different types of Notes which can be used with the notes
 * and attachments framework. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public enum NoteType {
    
	BUSINESS_OBJECT("BO"), DOCUMENT_HEADER("DH");
    
	private String noteTypeCode;

    private NoteType(String noteTypeCode) {
        this.noteTypeCode = noteTypeCode;
    }

    public String getCode() {
        return this.noteTypeCode;
    }

}