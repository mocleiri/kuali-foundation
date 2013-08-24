package org.kuali.common.util.xml.jaxb.issue415;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JAXBIssue415TestAdapter extends XmlAdapter<String, String> {

	@Override
	public String unmarshal(String v) {
		return null;
	}

	@Override
	public String marshal(String v) {
		return null;
	}

}
