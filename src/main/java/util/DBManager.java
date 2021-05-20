package util;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DB manager. Works with MySQL DB.
 *
 * @author A.Savchuk
 */
public class DBManager {
    private static final Logger logger = Logger.getLogger(DBManager.class);
    private static DBManager instance;

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    /**
     * Returns a DB connection from the Pool Connections using context.xml file
     * configured in the webapp/META-INF/context.xml file.
     *
     * @return a DB connection.
     */
    public Connection getConnection() {
        Connection con = null;
        logger.info("Started getConnection method.");
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/selection_committee");
            con = ds.getConnection();
        } catch (NamingException | SQLException ex) {
            logger.error("Unable to get connection from a pool : ", ex);
        }
        logger.info("Finished getConnection method.");
        return con;
    }

    /**
     * Commits and close the given connection.
     *
     * @param connection Connection to be committed and closed.
     */
    public void commitAndClose(Connection connection) {
        try {
            connection.commit();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param connection Connection to be rollbacks and closed.
     */
    public void rollbackAndClose(Connection connection) {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
