package org.kuali.db.liquibase;

import java.sql.Connection;
import java.sql.DriverManager;

public class DerbyTest {

    public static void main(String[] args) {
        try {
            String driver = "org.apache.derby.jdbc.EmbeddedDriver";
            String url = "jdbc:derby:memory:RICE";
            String dbaUrl = url + ";create=true";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(dbaUrl);
            conn.close();
            Connection conn2 = DriverManager.getConnection(url);
            conn2.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
