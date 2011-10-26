package org.kuali.db.impex;

import java.util.List;

import junit.framework.Assert;

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
        List<Transaction> trans = tg.getTransactions();
        int size = trans.size();
        Assert.assertEquals(64, size);
        Assert.assertEquals("classpath:sql/oracle/ks-rice-db.sql", trans.get(0).getResourceLocation());
        Assert.assertEquals("classpath:sql/oracle/ks-rice-db-constraints.sql", trans.get(size - 1)
                .getResourceLocation());
    }
}
