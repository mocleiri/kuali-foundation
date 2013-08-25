package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UseEclipseLinkMoxyProvider {

	/**
	 * <p>
	 * The only purpose for this class is to make JAXB bootstrap itself with the jaxb.properties file in this directory and thus switch from the reference implementation to the
	 * EclipseLink MOXy implementation.
	 * </p>
	 * 
	 * <p>
	 * If issue https://java.net/jira/browse/JAXB-415 gets resolved, this class and the corresponding jaxb.properties file can be removed, and the useEclipseLinkMoxyProvider flag
	 * can be defaulted to false.
	 * </p>
	 */
}
