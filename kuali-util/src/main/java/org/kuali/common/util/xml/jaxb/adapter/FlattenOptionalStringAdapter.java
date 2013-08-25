package org.kuali.common.util.xml.jaxb.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.Str;

import com.google.common.base.Optional;

public final class FlattenOptionalStringAdapter extends XmlAdapter<String, Optional<String>> {

	public static final String DEFAULT_CR_REPLACEMENT = "${xml.cr}";
	public static final String DEFAULT_LF_REPLACEMENT = "${xml.lf}";

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
		if (optional == null || !optional.isPresent()) {
			return null;
		} else {
			return Str.flatten(optional.get(), carriageReturnReplacement, linefeedReplacement);
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
