package org.kuali.db.impex;

import java.util.ArrayList;
import java.util.List;

import org.apache.torque.engine.database.model.Database;
import org.apache.torque.engine.database.model.Table;
import org.kuali.db.jdbc.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class TransactionGenerator {
    final Logger logger = LoggerFactory.getLogger(TransactionGenerator.class);

    ResourceLoader loader = new DefaultResourceLoader();
    String prefix;
    String suffix;
    String constraintsSuffix;
    SchemaParser parser;
    List<Transaction> transactions;

    public SchemaParser getParser() {
        return parser;
    }

    public void setParser(SchemaParser parser) {
        this.parser = parser;
    }

    protected Transaction getResourceTransaction(String resourceLocation) {
        Transaction t = new Transaction();
        t.setResourceLocation(resourceLocation);
        return t;
    }

    protected String getImpexClasspathResourceLocation(String filename) {
        StringBuilder sb = new StringBuilder();
        sb.append("classpath:");
        sb.append(getPrefix());
        sb.append("/");
        sb.append(getParser().getImpexMetadata().getPlatform());
        sb.append("/");
        sb.append(filename);
        return sb.toString();
    }

    protected boolean exists(String location) {
        Resource resource = loader.getResource(location);
        return resource.exists();
    }

    public void generate() {
        transactions = new ArrayList<Transaction>();
        Database database = parser.getDatabase();
        String name = database.getName();
        String ddlResource = getImpexClasspathResourceLocation(name + getSuffix());
        String ddlConstraintsResource = getImpexClasspathResourceLocation(name + getConstraintsSuffix() + getSuffix());
        transactions.add(getResourceTransaction(ddlResource));
        List<Table> tables = database.getTables();
        for (Table table : tables) {
            String tablename = table.getName();
            String tableResource = getImpexClasspathResourceLocation(tablename + getSuffix());
            if (exists(tableResource)) {
                transactions.add(getResourceTransaction(tableResource));
                logger.debug("Adding " + tableResource);
            } else {
                logger.debug("Skipping '" + tableResource + "'  Resource not found");
            }

        }
        transactions.add(getResourceTransaction(ddlConstraintsResource));
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getConstraintsSuffix() {
        return constraintsSuffix;
    }

    public void setConstraintsSuffix(String constraintsSuffix) {
        this.constraintsSuffix = constraintsSuffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
