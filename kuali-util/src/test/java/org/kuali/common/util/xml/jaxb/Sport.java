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
		this(name, UNKNOWN);
	}

	public Sport(String name, double fee) {
		super(fee);
		Assert.noBlanks(name);
		this.name = name;
	}

	@XmlAttribute
	private final String name;

	public String getName() {
		return name;
	}

}
