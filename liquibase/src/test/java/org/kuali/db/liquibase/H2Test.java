package org.kuali.db.liquibase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;

public class H2Test {

    public static void main(String[] args) {
        new H2Test().execute(args);
    }

    protected void execute(String[] args) {
        try {
            String port = "9092";
            String dir = "/temp/h2";
            String db = "TEST";
            String[] serverArgs = { "-tcp", "-tcpDaemon", "-tcpPort", port, "-baseDir", dir, "-trace" };
            Server.main(serverArgs);
            String driver = "org.h2.Driver";
            String dbaUrl = "jdbc:h2:tcp://localhost:" + port + "/" + db + ";DB_CLOSE_DELAY=-1;MODE=Oracle";
            String url = "jdbc:h2:tcp://localhost:" + port + "/" + db + ";IFEXISTS=true;MODE=Oracle";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(dbaUrl, "TEST", "TEST");
            conn.close();
            String createTable = "CREATE TABLE ACCT_DD_ATTR_DOC (DOC_HDR_ID VARCHAR2(14 BYTE) NOT NULL, OBJ_ID VARCHAR2(36 BYTE), VER_NBR NUMBER(14,0), ACCT_NUM NUMBER(14,0) NOT NULL, ACCT_OWNR VARCHAR2(50 BYTE) NOT NULL, ACCT_BAL NUMBER(16,2) NOT NULL, ACCT_OPN_DAT DATE NOT NULL, ACCT_STAT VARCHAR2(30 BYTE) NOT NULL, ACCT_UPDATE_DT_TM DATE, ACCT_AWAKE VARCHAR2(1 BYTE), CONSTRAINT ACCT_DD_ATTR_DOCP1 PRIMARY KEY (DOC_HDR_ID) USING INDEX TABLESPACE USERS);";
            Connection conn2 = DriverManager.getConnection(url, "TEST", "TEST");
            executeSql(conn2, createTable);
            conn2.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    protected void executeSql(Connection conn, String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        stmt.close();
    }
}
