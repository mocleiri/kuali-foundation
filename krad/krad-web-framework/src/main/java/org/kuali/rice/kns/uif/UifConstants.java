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
package org.kuali.rice.kns.uif;


/**
 * General constants used within the User Interface Framework
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class UifConstants {
	private static final long serialVersionUID = 3935664282036793486L;

	public static final String CONTROLLER_METHOD_DISPATCH_PARAMETER_NAME = "methodToCall";

	public static final String DEFAULT_MODEL_NAME = "KualiForm";
	public static final String DEFAULT_VIEW_NAME = "default";
	public static final String SPRING_VIEW_ID = "ApplicationView";

	public static class Position {
		public static final String BOTTOM = "BOTTOM";
		public static final String LEFT = "LEFT";
		public static final String RIGHT = "RIGHT";
		public static final String TOP = "TOP";
	}
	
	public static class Order {
	    public static final String FIRST = "FIRST";
	}

	public static class NavigationType {
		public static final String VERTICAL_MENU = "VERTICAL_MENU";
		public static final String HORIZONTAL_TABS = "HORIZONTAL_TABS";
	}

	public static class Orientation {
		public static final String HORIZONTAL = "HORIZONTAL";
		public static final String VERTICAL = "VERTICAL";
	}

	public static class MessageType {
		public static final String NORMAL = "NORMAL";
		public static final String SUMMARY = "SUMMARY";
		public static final String CONSTRAINT = "CONSTRAINT";
		public static final String REQUIRED = "REQUIRED";
	}

	public static class ViewType {
		public static final String DEFAULT = "DEFAULT";
		public static final String INQUIRY = "INQUIRY";
		public static final String LOOKUP = "LOOKUP";
		public static final String MAINTENANCE = "MAINTENANCE";
		public static final String TRANSACTIONAL = "TRANSACTIONAL";
	}

	// TODO - move the following two parameters into the UifParameters constants file including rename of NAME constant
	public static class ViewTypeParameterNames {
		public static final String NAME = "name";
		public static final String DATA_OBJECT_CLASS_NAME = "dataObjectClassName";
	}

	public static class MethodToCallNames {
		public static final String NAVIGATE = "navigate";
		public static final String START = "start";
		public static final String SAVE = "save";
		public static final String SEARCH = "search";
		public static final String CLOSE = "close";
		public static final String ADD_LINE = "addLine";
		public static final String DELETE_LINE = "deleteLine";
		public static final String REFRESH = "refresh";
		public static final String CANCEL = "cancel";
	}

	public static class LayoutComponentOptions {
		public static final String COLUMN_SPAN = "colSpan";
		public static final String ROW_SPAN = "rowSpan";
	}

	public static class IdSuffixes {
		public static final String ADD_LINE = "_addLine";
		public static final String ATTRIBUTE = "_attribute";
		public static final String CONSTRAINT = "_constraint";
		public static final String DIV = "_div";
		public static final String ERRORS = "_errors";
		public static final String LABEL = "_label";
		public static final String SUMMARY = "_summary";
		public static final String QUICK_FINDER = "_quickfinder";
	}

	public static class ViewPhases {
		public static final String INITIALIZE = "INITIALIZE";
		public static final String APPLY_MODEL = "APPLY_MODEL";
		public static final String FINALIZE = "FINALIZE";
	}

	public static class ViewStatus {
		public static final String CREATED = "C";
		public static final String INITIALIZED = "I";
		public static final String FINAL = "F";
	}

	public static final String EL_PLACEHOLDER_PREFIX = "@{";
	public static final String EL_PLACEHOLDER_SUFFIX = "}";
	public static final String EL_CONDITIONAL_PROPERTY_PREFIX = "conditional";

	public static class ContextVariableNames {
		public static final String CONFIG_PROPERTIES = "ConfigProperties";
		public static final String COMPONENT = "component";
		public static final String CONSTANTS = "Constants";
		public static final String INDEX = "index";
		public static final String LINE = "line";
		public static final String MANAGER = "manager";
		public static final String PARENT = "parent";
		public static final String VIEW = "view";
	}

	public static class TableToolsKeys{
		public static final String SDOM = "sDom";
		public static final String LANGUAGE = "oLanguage";
		public static final String EMPTY_TABLE = "sEmptyTable";
		public static final String AO_COLUMNS = "aoColumns";
		public static final String SORT_SKIP_ROWS = "aiSortingSkipRows";
		public static final String SORT_DATA_TYPE = "sSortDataType";
		public static final String SORTABLE = "bSortable";
		public static final String SORT_TYPE = "sType";
		public static final String TABLE_SORT = "bSort";
	}
	
	public static class TableToolsValues{
		public static final String DOM_TEXT = "dom-text";
		public static final String NUMERIC = "numeric";
		public static final String DATE = "kuali_date";
		public static final int ADD_ROW_DEFAULT_INDEX = 0;
	}
}
