package org.kuali.core.db.torque;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.Task;
import org.apache.torque.engine.platform.Platform;
import org.kuali.db.ConnectionHandler;
import org.kuali.db.Credentials;

public class DumpTask extends Task {

    Utils utils = new Utils();

    ConnectionHandler connectionHandler = new ConnectionHandler();

    boolean antCompatibilityMode;

    protected void showConfiguration() {
        log("Schema: " + schema);
        log("Artifact Id: " + artifactId);
        log("Database Vendor: " + getTargetDatabase());
        if (getEncoding() == null) {
            log("Encoding: " + System.getProperty("file.encoding"));
        } else {
            log("Encoding: " + getEncoding());
        }
    }

    protected void updateConfiguration(Platform platform) {
        if (StringUtils.isEmpty(schema)) {
            schema = platform.getSchemaName(artifactId);
        }
        if (StringUtils.isEmpty(username)) {
            username = schema;
        }
        if (StringUtils.isEmpty(password)) {
            password = schema;
        }
    }

    protected Connection getConnection() throws SQLException {
        try {
            BeanUtils.copyProperties(connectionHandler, this);
        } catch (Exception e) {
            throw new SQLException("Error copying properties", e);
        }
        Credentials credentials = new Credentials(username, password);
        connectionHandler.setCredentials(credentials);
        return connectionHandler.getConnection();
    }

    /**
     * This is the maven concept of an artifactId
     */
    String artifactId;

    /**
     * The encoding to use
     */
    String encoding;

    /**
     * List of regular expression patterns for tables to include
     */
    List<String> includePatterns;

    /**
     * List of regular expression patterns for tables to exclude
     */
    List<String> excludePatterns;

    /**
     * This is the "autogenerated by" comment in the XML
     */
    String comment;

    /**
     * JDBC URL.
     */
    String url;

    /**
     * JDBC driver.
     */
    String driver;

    /**
     * JDBC user name.
     */
    String username;

    /**
     * JDBC password.
     */
    String password;

    /**
     * DB schema to use.
     */
    String schema;

    /**
     * The type of database eg oracle, mysql etc
     */
    String targetDatabase;

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public List<String> getIncludePatterns() {
        return includePatterns;
    }

    public void setIncludePatterns(List<String> includePatterns) {
        this.includePatterns = includePatterns;
    }

    public List<String> getExcludePatterns() {
        return excludePatterns;
    }

    public void setExcludePatterns(List<String> excludePatterns) {
        this.excludePatterns = excludePatterns;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public ConnectionHandler getConnectionHandler() {
        return connectionHandler;
    }

    public void setConnectionHandler(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public boolean isAntCompatibilityMode() {
        return antCompatibilityMode;
    }

    public void setAntCompatibilityMode(boolean antCompatibilityMode) {
        this.antCompatibilityMode = antCompatibilityMode;
    }

}
