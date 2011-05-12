/*
 * Copyright 2005-2007 The Kuali Foundation
 *
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
package org.kuali.rice.kew.quicklinks.dao;

import java.util.List;

import org.kuali.rice.core.util.KeyValue;
import org.kuali.rice.kew.quicklinks.ActionListStats;
import org.kuali.rice.kew.quicklinks.InitiatedDocumentType;
import org.kuali.rice.kew.quicklinks.WatchedDocument;


public interface QuickLinksDAO {
    public List<WatchedDocument> getWatchedDocuments(String principalId);
    public List<KeyValue> getRecentSearches(String principalId);
    public List<KeyValue> getNamedSearches(String principalId);
    public List<ActionListStats> getActionListStats(String principalId);
    public List<InitiatedDocumentType> getInitiatedDocumentTypesList(String principalId);
}
