package org.kuali.common.util.xml.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class FeeAdapter extends XmlAdapter<String, Double> {

	@Override
	public String marshal(Double fee) {
		if (Club.UNKNOWN_FEE.equals(fee)) {
			return "UNKNOWN";
		} else {
			return fee.toString();
		}
	}

	@Override
	public Double unmarshal(String fee) {
		if ("UNKNOWN".equals(fee)) {
			return Club.UNKNOWN_FEE;
		} else {
			return Double.parseDouble(fee);
		}
	}

}
