package org.kuali.common.util.xml.jaxb.issue415;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for https://java.net/jira/browse/JAXB-415
 * 
 * Marshaller.marshall throws NPE if an adapter adapts a non-null bound value to null
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JAXBIssue415Test {

	@XmlElement
	@XmlJavaTypeAdapter(JAXBIssue415TestAdapter.class)
	private String value = "foo"; // Bound value is always initialized to "foo"

	@Test
	public void testIssue415() throws Exception {
		String os = System.getProperty("os.name") + ", " + System.getProperty("os.version");
		String jdk = System.getProperty("java.vm.name") + ", " + System.getProperty("java.runtime.version");
		try {
			Marshaller m = JAXBContext.newInstance(JAXBIssue415Test.class).createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(new JAXBIssue415Test(), System.out);
		} catch (NullPointerException e) {
			Assert.fail("JAXB issue 415 is still occurring on [" + os + "] [" + jdk + "]");
		}
	}

	public static class JAXBIssue415TestAdapter extends XmlAdapter<String, String> {

		@Override
		public String marshal(String value) {
			return null; // Ignore the bound value and just always return null
		}

		@Override
		public String unmarshal(String value) {
			return null;
		}
	}
}
