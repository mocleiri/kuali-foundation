package org.kuali.common.impex.pojo;

import org.kuali.common.util.xml.JAXBXmlService;

public class BasicTest {

	public static void main(String[] args) {
		try {
			String os = System.getProperty("os.name") + ", " + System.getProperty("os.version");
			String jdk = System.getProperty("java.vm.name") + ", " + System.getProperty("java.runtime.version");
			System.out.println(os);
			System.out.println(jdk);
			String encoding = "UTF-8";
			JAXBXmlService service = new JAXBXmlService.Builder().useEclipseLinkMoxyProvider(true).build();
			Column column = new Column.Builder("column", DataType.FLOAT, new DataTypeSize(10, 2)).build();
			Table table = new Table("table", column);
			Schema schema = new Schema.Builder("schema").table(table).build();
			String xml = service.toXml(schema, encoding);
			System.out.println(xml);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
