package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class FeeAdapter extends XmlAdapter<String, Double> {

	@Override
	public String marshal(Double fee) {
		if (Club.UNKNOWN.equals(fee)) {
			return "FREE";
		} else {
			return fee.toString();
		}
	}

	@Override
	public Double unmarshal(String fee) {
		if ("FREE".equals(fee)) {
			return Club.UNKNOWN;
		} else {
			return Double.parseDouble(fee);
		}
	}

}
