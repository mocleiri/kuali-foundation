package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

@XmlRootElement
public final class Club extends Activity {

	@SuppressWarnings("unused")
	private Club() {
		this(NullUtils.NONE);
	}

	public Club(String name) {
		this(name, UNKNOWN_FEE);
	}

	public Club(String name, double fee) {
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
