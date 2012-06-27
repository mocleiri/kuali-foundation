package org.kuali.db.jdbc;

/**
 * Listens for Database events
 */
public interface SQLListener {
    public void messageLogged(SQLEvent event);

    public void beginTransaction(SQLEvent event);

    public void finishTransaction(SQLEvent event);

    public void beforeExecuteSQL(SQLEvent event);

    public void afterExecuteSQL(SQLEvent event);

    public void afterProcessingSQLResults(SQLEvent event);
}
