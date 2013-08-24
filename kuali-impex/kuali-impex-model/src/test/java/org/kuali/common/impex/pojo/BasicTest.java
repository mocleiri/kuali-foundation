package org.kuali.common.impex.pojo;

import org.kuali.common.util.xml.DefaultXmlService;

import com.google.common.base.Optional;

public class BasicTest {

	public static void main(String[] args) {
		try {
			String version = System.getProperty("java.runtime.version");
			System.out.println(version);
			String encoding = "UTF-8";
			DefaultXmlService service = new DefaultXmlService();
			Optional<Integer> scale = Optional.<Integer> of(2);
			Column column = new Column.Builder("column", DataType.FLOAT, new DataTypeSize(10, scale)).build();
			Table table = new Table("table", column);
			Schema schema = new Schema.Builder("schema").table(table).build();
			String xml = service.toXml(schema, encoding);
			System.out.println(xml);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
