package appen.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A class to handle the database and connection to it.
 *
 * @author Pete
 * @version 1.1
 * @since 1.0
 */
public class Database {

    private String dataBaseAddress;

    /**
     * Contructor for Database.
     *
     * @param address The path for database
     * @since 1.0
     */
    public Database(String address) {
        this.dataBaseAddress = address;
    }

    /**
     * Returns a Connection variable.
     *
     * @return Returns an object of class Connection.
     * @throws SQLException is handled elsewhere
     * @since 1.0
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dataBaseAddress);
    }

    /**
     * Initializes the database and creates all the tables.
     *
     * @throws SQLException is handled elsewhere
     * @since 1.0
     */
    public void init() throws SQLException {
        Connection connex = getConnection();
        connex.prepareStatement("CREATE TABLE IF NOT EXISTS Players ("
                + "nickname VARCHAR(20) PRIMARY KEY, "
                + "password VARCHAR(20));"
        ).executeUpdate();

        connex.prepareStatement("CREATE TABLE IF NOT EXISTS Exercises ("
                + "id INTEGER PRIMARY KEY,"
                + "question VARCHAR(20),"
                + "answer VARCHAR(20),"
                + "level INTEGER);"
        ).executeUpdate();

        connex.prepareStatement("CREATE TABLE IF NOT EXISTS Performances ("
                + "id INTEGER PRIMARY KEY,"
                + "playerid VARCHAR(20),"
                + "exerciseid INTEGER,"
                + "count INTEGER,"
                + "FOREIGN KEY (playerid) REFERENCES Players(nickname),"
                + "FOREIGN KEY (exerciseid) REFERENCES Exercises(id));"
        ).executeUpdate();
    }

    /**
     * Resets the database by deleting all entries.
     *
     * @throws SQLException is handled elsewhere
     * @since 1.0
     */
    public void reset() throws SQLException {
        Connection connex = getConnection();
        connex.prepareStatement("DELETE FROM Players").executeUpdate();
        connex.prepareStatement("DELETE FROM Exercises").executeUpdate();
        connex.prepareStatement("DELETE FROM Performances").executeUpdate();
    }
}
