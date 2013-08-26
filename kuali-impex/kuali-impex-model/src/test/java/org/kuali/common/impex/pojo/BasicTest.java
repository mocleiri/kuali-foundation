package org.kuali.common.impex.pojo;

import java.util.Arrays;

import org.junit.Assert;
import org.kuali.common.util.execute.impl.ShowEnvExec;
import org.kuali.common.util.xml.jaxb.JAXBXmlService;

public class BasicTest {

	public static void main(String[] args) {
		try {
			new ShowEnvExec().execute();
			String encoding = "UTF-8";
			JAXBXmlService service = new JAXBXmlService.Builder().useEclipseLinkMoxyProvider(true).build();
			String desc = null;
			Column column1 = new Column.Builder("ID", DataType.FLOAT).size(10).scale(2).defaultValue("99").primaryKey(true).nullable(false).description(desc).build();
			Column column2 = new Column.Builder("CREATED", DataType.TIMESTAMP).defaultValue("NOW()").nullable(false).description(desc).build();
			UniqueConstraint uc1 = new UniqueConstraint("KS_VERSION_U1", column1.getName(), column2.getName());
			UniqueConstraint uc2 = new UniqueConstraint("KS_VERSION_U2", column1.getName(), column2.getName());
			Index i1 = new Index("KS_VERSION_I1", column1.getName(), column2.getName());
			Index i2 = new Index("KS_VERSION_I2", column1.getName(), column2.getName());
			Sequence s1 = new Sequence("SEQ_1", "10000");
			View v1 = new View("show_sysdate", "select sysdate from dual");
			ForeignKey fk1 = new ForeignKey.Builder("KS_FK1", "VERSION", "VERSION").onDelete(ForeignKeyConstraintType.NO_ACTION).onUpdate(ForeignKeyConstraintType.NO_ACTION)
					.localColumns(Arrays.asList("ID")).foreignColumns(Arrays.asList("ID", "SEQUENCE")).build();
			Table table = new Table.Builder("VERSION").columns(column1, column2).uniqueConstraints(uc1, uc2).indexes(i1, i2).build();
			// Table table = new Table.Builder("VERSION").columns(column1, column2).build();
			Schema schema = new Schema.Builder("KS").table(table).sequence(s1).view(v1).foreignKeys(Arrays.asList(fk1)).build();
			String xml = service.toXml(schema, encoding);
			Schema derived1 = service.getObjectFromXml(xml, encoding, Schema.class);
			String derived1Xml = service.toXml(derived1, encoding);
			Schema derived2 = service.getObjectFromXml(derived1Xml, encoding, Schema.class);
			String derived2Xml = service.toXml(derived2, encoding);
			System.out.println(derived1Xml);
			Assert.assertEquals(derived1Xml, derived2Xml);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
