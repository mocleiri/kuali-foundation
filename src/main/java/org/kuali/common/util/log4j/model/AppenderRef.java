package org.kuali.common.util.log4j.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @deprecated
 */
@Deprecated
public class AppenderRef {

	String ref;

	public AppenderRef(AppenderRef reference) {
		super();
		this.ref = reference.getRef();
	}

	public AppenderRef() {
		this((String) null);
	}

	public AppenderRef(String ref) {
		super();
		this.ref = ref;
	}

	@XmlAttribute
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

}
