package appen.dao;

import java.sql.*;

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
     * @since 1.0
     */
    public void init() {
        try {
            Connection connex = getConnection();
            initPlayer(connex);
            initExercise(connex);
            initPerformance(connex);
        } catch (Exception e) {
            System.out.println("");
        }
    }

    private void initPlayer(Connection connex) throws Exception {
        connex.prepareStatement("CREATE TABLE IF NOT EXISTS Players ("
                + "nickname VARCHAR(20) PRIMARY KEY, "
                + "password VARCHAR(20));"
        ).executeUpdate();
    }

    private void initExercise(Connection connex) throws Exception {
        connex.prepareStatement("CREATE TABLE IF NOT EXISTS Exercises ("
                + "id INTEGER PRIMARY KEY,"
                + "question VARCHAR(20),"
                + "answer VARCHAR(20),"
                + "level INTEGER);"
        ).executeUpdate();
    }

    private void initPerformance(Connection connex) throws Exception {
        connex.prepareStatement("CREATE TABLE IF NOT EXISTS Performances ("
                + "id INTEGER PRIMARY KEY,"
                + "playerid VARCHAR(20),"
                + "exerciseid INTEGER,"
                + "score INTEGER,"
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
