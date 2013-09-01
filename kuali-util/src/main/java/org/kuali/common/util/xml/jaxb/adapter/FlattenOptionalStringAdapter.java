package org.kuali.common.util.xml.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.Str;

import com.google.common.base.Optional;

public final class FlattenOptionalStringAdapter extends XmlAdapter<String, Optional<String>> {

	public FlattenOptionalStringAdapter() {
		this(FlattenConstants.DEFAULT_CR_REPLACEMENT, FlattenConstants.DEFAULT_LF_REPLACEMENT);
	}

	public FlattenOptionalStringAdapter(String carriageReturnReplacement, String linefeedReplacement) {
		// No blanks because this needs to work in both directions (flatten + inflate)
		Assert.noBlanks(carriageReturnReplacement, linefeedReplacement);
		this.carriageReturnReplacement = carriageReturnReplacement;
		this.linefeedReplacement = linefeedReplacement;
	}

	private final String carriageReturnReplacement;
	private final String linefeedReplacement;

	@Override
	public String marshal(Optional<String> optional) {
		if (optional.isPresent()) {
			return Str.flatten(optional.get(), carriageReturnReplacement, linefeedReplacement);
		} else {
			return null;
		}
	}

	@Override
	public Optional<String> unmarshal(String value) {
		if (value == null) {
			return Optional.<String> absent();
		} else {
			return Optional.<String> of(Str.inflate(value, carriageReturnReplacement, linefeedReplacement));
		}
	}

	public String getCarriageReturnReplacement() {
		return carriageReturnReplacement;
	}

	public String getLinefeedReplacement() {
		return linefeedReplacement;
	}

}
