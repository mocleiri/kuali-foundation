/*
 * Copyright 2007 The Kuali Foundation Licensed under the Educational Community
 * License, Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.opensource.org/licenses/ecl1.php Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.kuali.rice.kns.uif.container;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kns.uif.core.BindingInfo;
import org.kuali.rice.kns.uif.core.Component;
import org.kuali.rice.kns.uif.core.DataBinding;
import org.kuali.rice.kns.uif.field.ActionField;
import org.kuali.rice.kns.uif.field.AttributeField;
import org.kuali.rice.kns.uif.field.Field;
import org.kuali.rice.kns.uif.field.LabelField;

/**
 * Group that holds a collection of objects and configuration for presenting the
 * collection in the UI. Supports functionality such as add line, line actions,
 * and nested collections.
 * <p>
 * Note the standard header/footer can be used to give a header to the
 * collection as a whole, or to provide actions that apply to the entire
 * collection
 * </p>
 * <p>
 * For binding purposes the binding path of each row field is indexed. The name
 * property inherited from <code>ComponentBase</code> is used as the collection
 * name. The collectionObjectClass property is used to lookup attributes from
 * the data dictionary.
 * </p>
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class CollectionGroup extends Group implements DataBinding {
    private static final long serialVersionUID = -6496712566071542452L;

    private Class<?> collectionObjectClass;

    private String propertyName;
    private BindingInfo bindingInfo;

    private boolean renderAddLine;
    private String addLinePropertyName;
    private BindingInfo addLineBindingInfo;
    private LabelField addLineLabelField;
    private List<ActionField> addLineActionFields;

    private boolean renderLineActions;
    private List<ActionField> actionFields;

    private List<CollectionGroup> subCollections;

    private CollectionGroupBuilder collectionGroupBuilder;

    public CollectionGroup() {
        renderAddLine = true;
        renderLineActions = true;

        actionFields = new ArrayList<ActionField>();
        addLineActionFields = new ArrayList<ActionField>();
        subCollections = new ArrayList<CollectionGroup>();
    }

    /**
     * The following actions are performed:
     * <ul>
     * <li>Set fieldBindModelPath to the collection model path (since the fields
     * have to belong to the same model as the collection)</li>
     * <li>Set defaults for binding</li>
     * <li>Sets the dictionary entry (if blank) on each of the items to the
     * collection class</li>
     * </ul>
     * 
     * @see org.kuali.rice.kns.uif.core.ComponentBase#performInitialization(org.kuali.rice.kns.uif.container.View)
     */
    @Override
    public void performInitialization(View view) {
        setFieldBindingObjectPath(getBindingInfo().getBindingObjectPath());

        super.performInitialization(view);

        if (bindingInfo != null) {
            bindingInfo.setDefaults(view, getPropertyName());
        }

        if (addLineBindingInfo != null) {
            // add line binds to model property
            if (StringUtils.isNotBlank(addLinePropertyName)) {
                addLineBindingInfo.setDefaults(view, getPropertyName());
                addLineBindingInfo.setBindingName(addLinePropertyName);
                if (StringUtils.isNotBlank(getFieldBindByNamePrefix())) {
                    addLineBindingInfo.setBindByNamePrefix(getFieldBindByNamePrefix());
                }
            }
        }

        for (Component item : getItems()) {
            if (item instanceof AttributeField) {
                AttributeField field = (AttributeField) item;

                if (StringUtils.isBlank(field.getDictionaryObjectEntry())) {
                    field.setDictionaryObjectEntry(collectionObjectClass.getName());
                }
            }
        }

        // initialize container items and sub-collections (since they are not in child list)
        for (Component item : getItems()) {
            view.getViewHelperService().performComponentInitialization(view, item);
        }
        
        for (CollectionGroup collectionGroup : getSubCollections()) {
            view.getViewHelperService().performComponentInitialization(view, collectionGroup);
        }
    }

    /**
     * Calls the configured <code>CollectionGroupBuilder</code> to build the
     * necessary components based on the collection data
     * 
     * @see org.kuali.rice.kns.uif.container.ContainerBase#performApplyModel(org.kuali.rice.kns.uif.container.View,
     *      java.lang.Object)
     */
    @Override
    public void performApplyModel(View view, Object model) {
        super.performApplyModel(view, model);

        getCollectionGroupBuilder().build(view, model, this);
    }

    /**
     * New collection lines are handled in the framework by maintaining a map on
     * the form. The map contains as a key the collection name, and as value an
     * instance of the collection type. An entry is created here for the
     * collection represented by the <code>CollectionGroup</code> if an instance
     * is not available (clearExistingLine will force a new instance). The given
     * model must be a subclass of <code>UifFormBase</code> in order to find the
     * Map.
     * 
     * @param model
     *            - Model instance that contains the new collection lines Map
     * @param clearExistingLine
     *            - boolean that indicates whether the line should be set to a
     *            new instance if it already exists
     */
    public void initializeNewCollectionLine(View view, Object model, CollectionGroup collectionGroup,
            boolean clearExistingLine) {
        getCollectionGroupBuilder().initializeNewCollectionLine(view, model, collectionGroup, clearExistingLine);
    }

    /**
     * @see org.kuali.rice.kns.uif.container.ContainerBase#getNestedComponents()
     */
    @Override
    public List<Component> getNestedComponents() {
        List<Component> components = super.getNestedComponents();

        components.add(addLineLabelField);
        components.addAll(actionFields);
        components.addAll(addLineActionFields);

        // remove the containers items because we don't want them as children
        // (they will become children of the layout manager as the rows are
        // created)
        for (Component item : getItems()) {
            if (components.contains(item)) {
                components.remove(item);
            }
        }

        return components;
    }

    /**
     * Object class the collection maintains. Used to get dictionary information
     * in addition to creating new instances for the collection when necessary
     * 
     * @return Class<?> collection object class
     */
    public Class<?> getCollectionObjectClass() {
        return this.collectionObjectClass;
    }

    /**
     * Setter for the collection object class
     * 
     * @param collectionObjectClass
     */
    public void setCollectionObjectClass(Class<?> collectionObjectClass) {
        this.collectionObjectClass = collectionObjectClass;
    }

    /**
     * @see org.kuali.rice.kns.uif.core.DataBinding#getPropertyName()
     */
    public String getPropertyName() {
        return this.propertyName;
    }

    /**
     * Setter for the collections property name
     * 
     * @param propertyName
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * Determines the binding path for the collection. Used to get the
     * collection value from the model in addition to setting the binding path
     * for the collection attributes
     * 
     * @see org.kuali.rice.kns.uif.core.DataBinding#getBindingInfo()
     */
    public BindingInfo getBindingInfo() {
        return this.bindingInfo;
    }

    /**
     * Setter for the binding info instance
     * 
     * @param bindingInfo
     */
    public void setBindingInfo(BindingInfo bindingInfo) {
        this.bindingInfo = bindingInfo;
    }

    /**
     * Action fields that should be rendered for each collection line. Example
     * line action is the delete action
     * 
     * @return List<ActionField> line action fields
     */
    public List<ActionField> getActionFields() {
        return this.actionFields;
    }

    /**
     * Setter for the line action fields list
     * 
     * @param actionFields
     */
    public void setActionFields(List<ActionField> actionFields) {
        this.actionFields = actionFields;
    }

    /**
     * Indicates whether the action column for the collection should be rendered
     * 
     * @return boolean true if the actions should be rendered, false if not
     * @see #getActionFields()
     */
    public boolean isRenderLineActions() {
        return this.renderLineActions;
    }

    /**
     * Setter for the render line actions indicator
     * 
     * @param renderLineActions
     */
    public void setRenderLineActions(boolean renderLineActions) {
        this.renderLineActions = renderLineActions;
    }

    /**
     * Indicates whether an add line should be rendered for the collection
     * 
     * @return boolean true if add line should be rendered, false if it should
     *         not be
     */
    public boolean isRenderAddLine() {
        return this.renderAddLine;
    }

    /**
     * Setter for the render add line indicator
     * 
     * @param renderAddLine
     */
    public void setRenderAddLine(boolean renderAddLine) {
        this.renderAddLine = renderAddLine;
    }

    /**
     * Convenience getter for the add line label field text. The text is used to
     * label the add line when rendered and its placement depends on the
     * <code>LayoutManager</code>.
     * <p>
     * For the <code>TableLayoutManager</code> the label appears in the sequence
     * column to the left of the add line fields. For the
     * <code>StackedLayoutManager</code> the label is placed into the group
     * header for the line.
     * </p>
     * 
     * @return String add line label
     */
    public String getAddLineLabel() {
        if (getAddLineLabelField() != null) {
            return getAddLineLabelField().getLabelText();
        }

        return null;
    }

    /**
     * Setter for the add line label text
     * 
     * @param addLineLabel
     */
    public void setAddLineLabel(String addLineLabel) {
        if (getAddLineLabelField() != null) {
            getAddLineLabelField().setLabelText(addLineLabel);
        }
    }

    /**
     * <code>LabelField</code> instance for the add line label
     * 
     * @return LabelField add line label field
     * @see #getAddLineLabel()
     */
    public LabelField getAddLineLabelField() {
        return this.addLineLabelField;
    }

    /**
     * Setter for the <code>LabelField</code> instance for the add line label
     * 
     * @param addLineLabelField
     * @see #getAddLineLabel()
     */
    public void setAddLineLabelField(LabelField addLineLabelField) {
        this.addLineLabelField = addLineLabelField;
    }

    /**
     * Name of the property that contains an instance for the add line. If set
     * this is used with the binding info to create the path to the add line.
     * Can be left blank in which case the framework will manage the add line
     * instance in a generic map.
     * 
     * @return String add line property name
     */
    public String getAddLinePropertyName() {
        return this.addLinePropertyName;
    }

    /**
     * Setter for the add line property name
     * 
     * @param addLinePropertyName
     */
    public void setAddLinePropertyName(String addLinePropertyName) {
        this.addLinePropertyName = addLinePropertyName;
    }

    /**
     * <code>BindingInfo</code> instance for the add line property used to
     * determine the full binding path. If add line name given
     * {@link #getAddLineLabel()} then it is set as the binding name on the
     * binding info. Add line label and binding info are not required, in which
     * case the framework will manage the new add line instances through a
     * generic map (model must extend UifFormBase)
     * 
     * @return BindingInfo add line binding info
     */
    public BindingInfo getAddLineBindingInfo() {
        return this.addLineBindingInfo;
    }

    /**
     * Setter for the add line binding info
     * 
     * @param addLineBindingInfo
     */
    public void setAddLineBindingInfo(BindingInfo addLineBindingInfo) {
        this.addLineBindingInfo = addLineBindingInfo;
    }

    /**
     * Action fields that should be rendered for the add line. This is generally
     * the add action (button) but can be configured to contain additional
     * actions
     * 
     * @return List<ActionField> add line action fields
     */
    public List<ActionField> getAddLineActionFields() {
        return this.addLineActionFields;
    }

    /**
     * Setter for the add line action fields
     * 
     * @param addLineActionFields
     */
    public void setAddLineActionFields(List<ActionField> addLineActionFields) {
        this.addLineActionFields = addLineActionFields;
    }

    /**
     * List of <code>CollectionGroup</code> instances that are sub-collections
     * of the collection represented by this collection group
     * 
     * @return List<CollectionGroup> sub collections
     */
    public List<CollectionGroup> getSubCollections() {
        return this.subCollections;
    }

    /**
     * Setter for the sub collection list
     * 
     * @param subCollections
     */
    public void setSubCollections(List<CollectionGroup> subCollections) {
        this.subCollections = subCollections;
    }

    /**
     * <code>CollectionGroupBuilder</code> instance that will build the
     * components dynamically for the collection instance
     * 
     * @return CollectionGroupBuilder instance
     */
    public CollectionGroupBuilder getCollectionGroupBuilder() {
        if (this.collectionGroupBuilder == null) {
            this.collectionGroupBuilder = new CollectionGroupBuilder();
        }
        return this.collectionGroupBuilder;
    }

    /**
     * Setter for the collection group building instance
     * 
     * @param collectionGroupBuilder
     */
    public void setCollectionGroupBuilder(CollectionGroupBuilder collectionGroupBuilder) {
        this.collectionGroupBuilder = collectionGroupBuilder;
    }

    /**
     * @see org.kuali.rice.kns.uif.container.ContainerBase#getItems()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<? extends Field> getItems() {
        return (List<? extends Field>) super.getItems();
    }

}
