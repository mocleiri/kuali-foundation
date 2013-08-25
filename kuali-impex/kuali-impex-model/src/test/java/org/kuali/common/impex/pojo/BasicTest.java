package org.kuali.common.impex.pojo;

import java.util.Arrays;

import org.kuali.common.util.Assert;
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
			String desc = "<metadata>\n<yo>12</yo>\n</metadata>";
			Column column1 = new Column.Builder("ID", DataType.FLOAT).size(10).scale(2).defaultValue("99").primaryKey(true).nullable(false).description(desc).build();
			Column column2 = new Column.Builder("CREATED", DataType.TIMESTAMP).defaultValue("NOW()").nullable(false).description(desc).build();
			UniqueConstraint uc1 = new UniqueConstraint("KS_VERSION_U1", Arrays.asList(column1.getName(), column2.getName()));
			UniqueConstraint uc2 = new UniqueConstraint("KS_VERSION_U1", Arrays.asList(column1.getName(), column2.getName()));
			Table table = new Table.Builder("VERSION").columns(Arrays.asList(column1, column2)).uniqueConstraints(uc1).build();
			Schema schema = new Schema.Builder("KS").table(table).build();
			String xml = service.toXml(schema, encoding);
			System.out.println(xml);
			Schema derived = service.getObjectFromXml(xml, encoding, Schema.class);
			String xml2 = service.toXml(derived, encoding);
			System.out.println(xml2);
			Assert.isTrue(xml.equals(xml2), "xml doesn't match");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
