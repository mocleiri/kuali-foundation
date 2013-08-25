package org.kuali.common.impex.pojo;

import org.kuali.common.util.xml.jaxb.JAXBXmlService;

public class BasicTest {

	public static void main(String[] args) {
		try {
			String os = System.getProperty("os.name") + ", " + System.getProperty("os.version");
			String jdk = System.getProperty("java.vm.name") + ", " + System.getProperty("java.runtime.version");
			System.out.println(os);
			System.out.println(jdk);
			String encoding = "UTF-8";
			JAXBXmlService service = new JAXBXmlService.Builder().useEclipseLinkMoxyProvider(true).build();
			Column column = new Column.Builder("column", DataType.FLOAT).size(10).scale(2).defaultValue("'Y'\n\n").build();
			Table table = new Table("table", column);
			Schema schema = new Schema.Builder("schema").table(table).build();
			String xml = service.toXml(schema, encoding);
			System.out.println(xml);
			Schema derived = service.getObjectFromXml(xml, encoding, Schema.class);
			String xml2 = service.toXml(derived, encoding);
			System.out.println(xml2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
