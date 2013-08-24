package org.kuali.common.util.xml.jaxb.issue415;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JAXBIssue415Test {

	@XmlElement
	@XmlJavaTypeAdapter(JAXBIssue415TestAdapter.class)
	private String string;

}
