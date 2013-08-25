package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.xml.jaxb.adapter.OmitFalseAdapter;

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
	@XmlJavaTypeAdapter(OmitFalseAdapter.class)
	private final Boolean contact;

	public String getName() {
		return name;
	}

	public Boolean getContact() {
		return contact;
	}

}
