package org.apache.ojb.tools.mapping.reversedb2.ojbmetatreemodel;

import org.apache.ojb.broker.metadata.DescriptorRepository;

public class OjbMetaExtentClassNode extends OjbMetaTreeNode
{

    private static java.util.ArrayList supportedActions = new java.util.ArrayList();
        
    private String extentClassName;
/* Copyright 2002-2005 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
    public OjbMetaExtentClassNode(
        DescriptorRepository pRepository,
        OjbMetaDataTreeModel pTreeModel,
        OjbMetaTreeNode pparent,
        String pExtentClassName)
    {
        super(pRepository, pTreeModel, pparent);
        this.extentClassName = pExtentClassName;
    }

    /**
     * @see OjbMetaTreeNode#_load()
     */
    protected boolean _load()
    {
        return true;
    }

    /**
     * @see OjbMetaTreeNode#isLeaf()
     */
    public boolean isLeaf()
    {
        return false;
    }

    /**
     * @see OjbMetaTreeNode#getAllowsChildren()
     */
    public boolean getAllowsChildren()
    {
        return false;
    }

    /**
     * @see OjbMetaTreeNode#setAttribute(String, Object)
     */
    public void setAttribute(String strKey, Object value)
    {
    }

    /**
     * @see OjbMetaTreeNode#getAttribute(String)
     */
    public Object getAttribute(String strKey)
    {
        return null;
    }

    /**
     * @see PropertyEditorTarget#getPropertyEditorClass()
     */
    public Class getPropertyEditorClass()
    {
        return null;
    }
    
    public String toString()
    {
    	return "extent Class:" + extentClassName;
    }
    
    /**
     * @see ActionTarget#getActions()
     */    
    public java.util.Iterator getActions() 
    {
        return supportedActions.iterator();
    }      
    
    /**
     * @see ActionTarget#actionListCacheable()
     */    
    public boolean actionListCachable()
    {
        return true;
    }
    
    public boolean actionListStatic()
    {
        return true;
    }
    
    /**
     * Return the descriptor object this node is associated with. E.g. if the 
     * node displays a class descriptor, the ClassDescriptor describing the class
     * should be returned. Used for creating a Transferable. I'm not sure that
     * a string here is really what we want....
     */    
    public Object getAssociatedDescriptor()
    {
        return extentClassName;
    }    

}

