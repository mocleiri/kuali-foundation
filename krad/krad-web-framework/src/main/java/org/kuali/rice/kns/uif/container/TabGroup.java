/*
 * Copyright 2011 The Kuali Foundation
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
package org.kuali.rice.kns.uif.container;

import java.util.HashSet;
import java.util.Set;

import org.kuali.rice.kns.uif.core.Component;
import org.kuali.rice.kns.uif.widget.Tabs;

/**
 * A group that presents its child Groups as tabs.  Items in this group's item list must be Groups
 * themselves.
 * 
 * @see Group
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class TabGroup extends Group{
	
	private static final long serialVersionUID = 3L;
	private Tabs tabsWidget;
	/**
	 * @see org.kuali.rice.kns.uif.core.Component#getComponentTypeName()
	 */
	@Override
	public String getComponentTypeName() {
		return "tabGroup";
	}
	
	/**
	 * Only groups are supported for this group.
	 * @see org.kuali.rice.krad.web.view.container.ContainerBase#getSupportedComponents()
	 */
	@Override
	public Set<Class<? extends Component>> getSupportedComponents() {
		Set<Class<? extends Component>> supportedComponents = new HashSet<Class<? extends Component>>();
		supportedComponents.add(Group.class);

		return supportedComponents;
	}

	/**
	 * Gets the widget which contains any configuration for the tab widget component used to render
	 * this TabGroup
	 * @return the tabsWidget
	 */
	public Tabs getTabsWidget() {
		return this.tabsWidget;
	}

	/**
	 * @param tabsWidget the tabsWidget to set
	 */
	public void setTabsWidget(Tabs tabsWidget) {
		this.tabsWidget = tabsWidget;
	}
	
	
}
