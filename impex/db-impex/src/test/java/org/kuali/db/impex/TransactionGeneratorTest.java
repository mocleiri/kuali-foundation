package org.kuali.db.impex;

import org.junit.Test;
import org.kuali.db.jdbc.Transaction;

public class TransactionGeneratorTest {

	@Test
	public void test() throws Exception {
		ImpexMetadata imd = new ImpexMetadata();
		imd.setPlatform("oracle");
		imd.setSchemaLocation("classpath:ks-rice-db.xml");
		SchemaParser parser = new SchemaParser();
		parser.setImpexMetadata(imd);
		parser.parse();
		TransactionGenerator tg = new TransactionGenerator();
		tg.setParser(parser);
		tg.setPrefix("sql");
		tg.setSuffix(".sql");
		tg.setConstraintsSuffix("-constraints");
		tg.generate();
		for (Transaction t : tg.getTransactions()) {
			System.out.println(t.getResourceLocation());
		}
	}
}
