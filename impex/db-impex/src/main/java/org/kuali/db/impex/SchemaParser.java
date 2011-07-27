package org.kuali.db.impex;

import java.io.IOException;

import org.apache.torque.engine.database.model.Database;
import org.kuali.core.db.torque.KualiXmlToAppData;

public class SchemaParser {
    ImpexMetadata impexMetadata;
    Database database;

    public void parse() throws IOException {
        KualiXmlToAppData xmlParser = new KualiXmlToAppData(impexMetadata.getPlatform(), "");

        // Parse schema.xml into a database object
        try {
            database = xmlParser.parseResource(impexMetadata.getSchemaLocation());
        } catch (Exception e) {
            throw new IOException("Error parsing: " + impexMetadata.getSchemaLocation(), e);
        }
    }

    public ImpexMetadata getImpexMetadata() {
        return impexMetadata;
    }

    public void setImpexMetadata(ImpexMetadata impexMetadata) {
        this.impexMetadata = impexMetadata;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

}
