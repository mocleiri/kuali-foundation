package org.kuali.rice.krms.api.repository.action;

import java.util.Set;

public interface ActionDefinitionContract {
	/**
	 * This is the ID for the Action
	 *
	 * <p>
	 * It is a ID of a Action
	 * </p>
	 * @return ID for Action
	 */
	public String getId();

	/**
	 * This is the name of the Action 
	 *
	 * <p>
	 * name - the name of the Action
	 * </p>
	 * @return the name of the Action
	 */
	public String getName();

	/**
	 * This is the namespace of the Action 
	 *
	 * <p>
	 * The namespace of the Action
	 * </p>
	 * @return the namespace of the Action
	 */
	public String getNamespace();

    /**
     * This is the description for what the parameter is used for.  This can be null or a blank string.
     * @return description
     */
	public String getDescription();

	/**
	 * This is the KrmsType of the Action
	 *
	 * @return id for KRMS type related of the Action
	 */
	public String getTypeId();
	
	/**
	 * This method returns the id of the rule associated with the action
	 * 
	 * @return id for the Rule associated with the action.
	 */
	public String getRuleId();
	
	/**
	 * This method returns the id of the rule associated with the action
	 * 
	 * @return id for the Rule associated with the action.
	 */
	public Integer getSequenceNumber();
	
	/**
	 * This method returns a set of attributes associated with the 
	 * Action
	 * 
	 * @return a set of ActionAttribute objects.
	 */
	public Set<? extends ActionAttributeContract> getAttributes();
	

}
