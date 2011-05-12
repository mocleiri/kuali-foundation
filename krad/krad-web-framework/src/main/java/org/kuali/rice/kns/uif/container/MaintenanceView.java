/*
 * Copyright 2011 The Kuali Foundation Licensed under the Educational Community
 * License, Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.opensource.org/licenses/ecl1.php Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.kuali.rice.kns.uif.container;

import org.kuali.rice.kns.uif.UifConstants.ViewType;

/**
 * View type for Maintenance documents
 * 
 * <p>
 * Supports primary display for a new maintenance record, in which case the
 * fields are display for populating the new record, and an edit maintenance
 * record, which is a comparison view with the old record read-only on the left
 * side and the new record (changed record) on the right side
 * </p>
 * 
 * <p>
 * The <code>MaintenanceView</code> provides the interface for the maintenance
 * framework. It works with the <code>Maintainable</code> service and
 * maintenance controller.
 * </p>
 * 
 * <p>
 * Maintenance views are primarily configured by the object class they are
 * associated with. This provides the default dictionary information for the
 * fields. If more than one maintenance view is needed for the same object
 * class, the view name can be used to further identify an unique view
 * </p>
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class MaintenanceView extends DocumentView {
    private static final long serialVersionUID = -3382802967703882341L;

    private Class<?> dataObjectClassName;

    private String oldObjectBindingPath;

    // TODO: figure out what this is used for
    private boolean allowsRecordDeletion = false;

    public MaintenanceView() {
        super();

        setViewTypeName(ViewType.MAINTENANCE);
    }

    /**
     * The following initialization is performed:
     * 
     * <ul>
     * <li>Set the abstractTypeClasses map for the maintenance object path</li>
     * </ul>
     * </p>
     * 
     * @see org.kuali.rice.kns.uif.container.ContainerBase#performInitialization(org.kuali.rice.kns.uif.container.View)
     */
    @Override
    public void performInitialization(View view) {
        super.performInitialization(view);

        getAbstractTypeClasses().put(getDefaultBindingObjectPath(), dataObjectClassName);
        getAbstractTypeClasses().put(getOldObjectBindingPath(), dataObjectClassName);
    }

    /**
     * Class name for the object the maintenance document applies to
     * 
     * <p>
     * The object class name is used to pick up a dictionary entry which will
     * feed the attribute field definitions and other configuration. In addition
     * it is used to configure the <code>Maintainable</code> which will carry
     * out the maintenance action
     * </p>
     * 
     * @return Class<?> maintenance object class
     */
    public Class<?> getDataObjectClassName() {
        return this.dataObjectClassName;
    }

    /**
     * Setter for the object class name
     * 
     * @param dataObjectClassName
     */
    public void setDataObjectClassName(Class<?> dataObjectClassName) {
        this.dataObjectClassName = dataObjectClassName;
    }

    public String getOldObjectBindingPath() {
        return this.oldObjectBindingPath;
    }

    public void setOldObjectBindingPath(String oldObjectBindingPath) {
        this.oldObjectBindingPath = oldObjectBindingPath;
    }

    public boolean isAllowsRecordDeletion() {
        return this.allowsRecordDeletion;
    }

    public void setAllowsRecordDeletion(boolean allowsRecordDeletion) {
        this.allowsRecordDeletion = allowsRecordDeletion;
    }

}
