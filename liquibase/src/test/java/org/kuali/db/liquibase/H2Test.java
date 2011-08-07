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
            String dbaUrl = "jdbc:h2:tcp://localhost:" + port + "/" + db + ";DB_CLOSE_DELAY=-1";
            String url = "jdbc:h2:tcp://localhost:" + port + "/" + db + ";IFEXISTS=true";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(dbaUrl, "TEST", "TEST");
            conn.close();
            Connection conn2 = DriverManager.getConnection(url, "TEST", "TEST");
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
