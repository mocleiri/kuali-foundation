package org.kuali.db.impex;

import junit.framework.Assert;

import org.apache.torque.engine.database.model.Database;
import org.junit.Test;

public class SchemaParserTest {

    @Test
    public void test() throws Exception {
        ImpexMetadata imd = new ImpexMetadata();
        imd.setPlatform("oracle");
        imd.setSchemaLocation("classpath:ks-rice-db.xml");
        SchemaParser parser = new SchemaParser();
        parser.setImpexMetadata(imd);
        parser.parse();
        Database db = parser.getDatabase();
        Assert.assertEquals("ks-rice-db", db.getName());
    }
}
