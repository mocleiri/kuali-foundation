package org.kuali.common.impex.pojo;

import java.util.Arrays;

import org.kuali.common.util.xml.DefaultXmlService;

public class BasicTest {

	public static void main(String[] args) {
		try {
			String version = System.getProperty("java.runtime.version");
			System.out.println(version);
			String encoding = "UTF-8";
			DefaultXmlService service = new DefaultXmlService();
			Column column = new Column.Builder("column", DataType.BIT, new DataTypeSize(0)).build();
			Table table = new Table("table", column);
			Schema schema = new Schema.Builder("schema").tables(Arrays.asList(table)).build();
			String xml = service.toXml(schema, encoding);
			System.out.println(xml);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
