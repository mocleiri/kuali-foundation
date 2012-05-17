package org.kuali.db.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Various JDBC related utility methods
 */
public class JDBCUtils {

    public static void closeQuietly(final ResultSet rs, final Statement stmt, final Connection conn) {
        closeQuietly(rs);
        closeQuietly(stmt);
        closeQuietly(conn);
    }

    public static void closeQuietly(final Statement stmt, final Connection conn) {
        closeQuietly(null, stmt, conn);
    }

    public static void closeQuietly(final ResultSet rs) {
        if (rs == null) {
            return;
        }
        try {
            rs.close();
        } catch (SQLException e) {
            // ignore
        }
    }

    public static void closeQuietly(final Statement stmt) {
        if (stmt == null) {
            return;
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            // ignore
        }
    }

    public static void closeQuietly(final Connection conn) {
        if (conn == null) {
            return;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            // ignore
        }
    }

    public static void nullSafeRollback(final Connection conn) throws SQLException {
        if (conn == null) {
            return;
        }
        conn.rollback();
    }

}
