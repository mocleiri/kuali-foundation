package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

@XmlRootElement
public final class Sport extends Activity {

	@SuppressWarnings("unused")
	private Sport() {
		this(NullUtils.NONE);
	}

	public Sport(String name) {
		this(name, UNKNOWN_FEE, false);
	}

	public Sport(String name, double fee, boolean contact) {
		super(fee);
		Assert.noBlanks(name);
		this.name = name;
		this.contact = contact;
	}

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final Boolean contact;

	public String getName() {
		return name;
	}

	public boolean getContact() {
		return contact;
	}

}
