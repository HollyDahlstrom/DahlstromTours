/**
 * DBUtil.java - DahlstromTours
 * Utility class for managing database connections using a connection pool.
 * Provides a centralized method for all database access classes to obtain a Connection object.
 *
 * Functionality:
 *   - Uses JNDI to look up the DataSource configured in context.xml.
 *   - Ensures a single DataSource instance is initialized via a static block.
 *   - Provides getConnection() to retrieve a Connection from the pool.
 *
 * Error Handling:
 *   - Prints an error message if the JNDI lookup fails.
 *   - Throws SQLException if the DataSource is not initialized.
 *
 * Usage:
 *   All database-related classes (UserDB, TourDB, OrderDB, etc.) call DBUtil.getConnection()
 *   to interact with the database.
 */

package murach.DB;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    private static DataSource dataSource = null;

    // Static block to initialize the data source once
    static {
        try {
            InitialContext ic = new InitialContext();
            // The JNDI name from context.xml
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/tour_site_db");
        } catch (NamingException e) {
            System.err.println("JNDI Lookup Error: " + e.getMessage());
        }
    }

    // Public method for all DB classes to get a connection
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Database connection pool not initialized.");
        }
        return dataSource.getConnection();
    }
}