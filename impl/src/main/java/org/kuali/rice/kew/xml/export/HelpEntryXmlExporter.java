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
package org.kuali.rice.kew.xml.export;

import static org.kuali.rice.core.api.impex.xml.XmlConstants.HELP_ENTRIES;
import static org.kuali.rice.core.api.impex.xml.XmlConstants.HELP_ENTRY;
import static org.kuali.rice.core.api.impex.xml.XmlConstants.HELP_KEY;
import static org.kuali.rice.core.api.impex.xml.XmlConstants.HELP_NAME;
import static org.kuali.rice.core.api.impex.xml.XmlConstants.HELP_NAMESPACE;
import static org.kuali.rice.core.api.impex.xml.XmlConstants.HELP_SCHEMA_LOCATION;
import static org.kuali.rice.core.api.impex.xml.XmlConstants.HELP_TEXT;
import static org.kuali.rice.core.api.impex.xml.XmlConstants.SCHEMA_LOCATION_ATTR;
import static org.kuali.rice.core.api.impex.xml.XmlConstants.SCHEMA_NAMESPACE;

import java.util.Iterator;

import org.jdom.Element;
import org.kuali.rice.core.api.impex.ExportDataSet;
import org.kuali.rice.core.framework.impex.xml.XmlExporter;
import org.kuali.rice.core.util.xml.XmlRenderer;
import org.kuali.rice.kew.export.KewExportDataSet;
import org.kuali.rice.kew.help.HelpEntry;
/**
 * Exports {@link HelpEntry}s to XML.
 *
 * @see HelpEntry
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class HelpEntryXmlExporter implements XmlExporter {

    protected final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(getClass());
    
    private XmlRenderer renderer = new XmlRenderer(HELP_NAMESPACE);
    
	@Override
	public boolean supportPrettyPrint() {
		return true;
	}
    
    public Element export(ExportDataSet exportDataSet) {
    	KewExportDataSet dataSet = KewExportDataSet.fromExportDataSet(exportDataSet);
        if (!dataSet.getHelp().isEmpty()) {
            Element rootElement = renderer.renderElement(null, HELP_ENTRIES);
            rootElement.setAttribute(SCHEMA_LOCATION_ATTR, HELP_SCHEMA_LOCATION, SCHEMA_NAMESPACE);
            for (Iterator iterator = dataSet.getHelp().iterator(); iterator.hasNext();) {
                HelpEntry helpEntry = (HelpEntry)iterator.next();
                exportHelpEntry(rootElement, helpEntry);
            }
            return rootElement;
        }
        return null;
    }
    
    private void exportHelpEntry(Element parent, HelpEntry helpEntry) {
        Element helpElement = renderer.renderElement(parent, HELP_ENTRY);
        renderer.renderTextElement(helpElement, HELP_NAME, helpEntry.getHelpName());
        renderer.renderTextElement(helpElement, HELP_KEY, helpEntry.getHelpKey());
        renderer.renderCDATAElement(helpElement, HELP_TEXT, helpEntry.getHelpText());
    }
    
}
