package org.kuali.common.util.xml.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.Str;

public final class FlattenStringAdapter extends XmlAdapter<String, String> {

	public FlattenStringAdapter() {
		this(FlattenConstants.DEFAULT_CR_REPLACEMENT, FlattenConstants.DEFAULT_LF_REPLACEMENT);
	}

	public FlattenStringAdapter(String carriageReturnReplacement, String linefeedReplacement) {
		// No blanks because this needs to work in both directions (flatten + inflate)
		Assert.noBlanks(carriageReturnReplacement, linefeedReplacement);
		this.carriageReturnReplacement = carriageReturnReplacement;
		this.linefeedReplacement = linefeedReplacement;
	}

	private final String carriageReturnReplacement;
	private final String linefeedReplacement;

	@Override
	public String marshal(String string) {
		if (string != null) {
			return Str.flatten(string, carriageReturnReplacement, linefeedReplacement);
		} else {
			return null;
		}
	}

	@Override
	public String unmarshal(String value) {
		if (value == null) {
			return null;
		} else {
			return Str.inflate(value, carriageReturnReplacement, linefeedReplacement);
		}
	}

	public String getCarriageReturnReplacement() {
		return carriageReturnReplacement;
	}

	public String getLinefeedReplacement() {
		return linefeedReplacement;
	}

}
