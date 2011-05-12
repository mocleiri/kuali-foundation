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
package org.kuali.rice.kns.lookup;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;

import org.displaytag.export.CsvView;
import org.displaytag.model.TableModel;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * This class allows for plugging in custom CSV export into the Display Tag library. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class CustomCsvExportView extends CsvView {

	private ExportViewHelper helper;
	
	@Override
	public void setParameters(TableModel tableModel, boolean exportFullList,
			boolean includeHeader, boolean decorateValues) {
		this.helper = new ExportViewHelper(tableModel);
		super.setParameters(tableModel, exportFullList, includeHeader, decorateValues);
	}

	@Override
	public void doExport(Writer writer) throws IOException, JspException {
		if (!helper.attemptCustomExport(writer, KNSConstants.CSV_FORMAT)) {
			super.doExport(writer);
		}
	}

}
