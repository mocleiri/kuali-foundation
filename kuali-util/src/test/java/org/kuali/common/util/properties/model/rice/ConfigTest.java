package org.kuali.common.util.properties.model.rice;

import java.util.Arrays;

import org.junit.Test;
import org.kuali.common.util.xml.jaxb.JAXBXmlService;
import org.kuali.common.util.xml.service.XmlService;

public class ConfigTest {

	@Test
	public void test() {
		try {
			Param param = new Param.Builder("foo").value("bar").build();
			Config config = new Config(Arrays.asList(param));
			XmlService service = new JAXBXmlService.Builder().build();
			String xml = service.toXml(config, "UTF-8");
			System.out.println(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
