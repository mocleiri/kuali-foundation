package org.kuali.common.impex.pojo;

import org.kuali.common.util.xml.JAXBXmlService;

public class BasicTest {

	public static void main(String[] args) {
		try {
			String version = System.getProperty("java.runtime.version");
			System.out.println(version);
			String encoding = "UTF-8";
			JAXBXmlService service = new JAXBXmlService.Builder().build();
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
