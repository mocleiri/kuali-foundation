package org.kuali.common.util.xml.jaxb.issue415;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JAXBIssue415Test {

	public JAXBIssue415Test() {
		this(null);
	}

	public JAXBIssue415Test(String string) {
		this.string = string;
	}

	@XmlElement
	@XmlJavaTypeAdapter(JAXBIssue415TestAdapter.class)
	private String string;

	public static void main(String[] args) {
		try {
			Marshaller m = JAXBContext.newInstance(JAXBIssue415Test.class).createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(new JAXBIssue415Test("foo"), System.out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
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


}
