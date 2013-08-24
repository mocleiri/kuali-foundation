package org.kuali.common.impex.pojo;

import org.kuali.common.util.xml.DefaultXmlService;

public class BasicTest {

	public static void main(String[] args) {
		try {
			String version = System.getProperty("java.runtime.version");
			System.out.println(version);
			String encoding = "UTF-8";
			DefaultXmlService service = new DefaultXmlService();
			Schema schema = new Schema.Builder("foo").build();
			String xml = service.toXml(schema, encoding);
			System.out.println(xml);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
