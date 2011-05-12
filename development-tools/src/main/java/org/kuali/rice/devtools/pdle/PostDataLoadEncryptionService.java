/*
 * Copyright 2006-2011 The Kuali Foundation
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
package org.kuali.rice.devtools.pdle;

import org.kuali.rice.kns.bo.PersistableBusinessObject;

import java.util.Set;

public interface PostDataLoadEncryptionService {

    public static final String POST_DATA_LOAD_ENCRYPTION_SERVICE = "postDataLoadEncryptionService";
	
	public void checkArguments(Class businessObjectClass, Set<String> attributeNames);
	
    public void checkArguments(Class businessObjectClass, Set<String> attributeNames, boolean checkOjbEncryptConfig);

    public void createBackupTable(Class businessObjectClass);

    public void prepClassDescriptor(Class businessObjectClass, Set<String> attributeNames);

    public void truncateTable(Class businessObjectClass);

    public void encrypt(PersistableBusinessObject businessObject, Set<String> attributeNames);

    public void restoreClassDescriptor(Class businessObjectClass, Set<String> attributeNames);

    public void restoreTableFromBackup(Class businessObjectClass);

    public void dropBackupTable(Class businessObjectClass);
}
