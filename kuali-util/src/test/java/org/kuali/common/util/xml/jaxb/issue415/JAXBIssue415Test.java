package org.kuali.common.util.xml.jaxb.issue415;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.junit.Test;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JAXBIssue415Test {

	public JAXBIssue415Test() {
		this.string = "foo";
	}

	@XmlElement
	@XmlJavaTypeAdapter(JAXBIssue415TestAdapter.class)
	private String string;

	@Test
	public void testIssue415() throws Exception {
		Marshaller m = JAXBContext.newInstance(JAXBIssue415Test.class).createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(new JAXBIssue415Test(), System.out);
	}

	public static class JAXBIssue415TestAdapter extends XmlAdapter<String, String> {

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
