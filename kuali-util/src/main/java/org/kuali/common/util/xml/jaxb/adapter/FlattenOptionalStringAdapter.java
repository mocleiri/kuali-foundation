package org.kuali.common.util.xml.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.Str;

import com.google.common.base.Optional;

public final class FlattenOptionalStringAdapter extends XmlAdapter<String, Optional<String>> {

	public static final String DEFAULT_CR_REPLACEMENT = "${jaxb.cr}";
	public static final String DEFAULT_LF_REPLACEMENT = "${jaxb.lf}";

	public FlattenOptionalStringAdapter() {
		this(DEFAULT_CR_REPLACEMENT, DEFAULT_LF_REPLACEMENT);
	}

	public FlattenOptionalStringAdapter(String carriageReturnReplacement, String linefeedReplacement) {
		Assert.noNullStrings(carriageReturnReplacement, linefeedReplacement);
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
			return Optional.<String> of(Str.unflatten(value, carriageReturnReplacement, linefeedReplacement));
		}
	}

	public String getCarriageReturnReplacement() {
		return carriageReturnReplacement;
	}

	public String getLinefeedReplacement() {
		return linefeedReplacement;
	}

}
