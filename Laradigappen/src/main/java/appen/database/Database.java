
package appen.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private String dataBaseAddress;
    
    public Database(String address) {
        this.dataBaseAddress = address;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dataBaseAddress);
    }
    
    public void init() throws SQLException {
        Connection connex = getConnection();
        connex.prepareStatement("CREATE TABLE IF NOT EXISTS Players ("
                + "nickname VARCHAR(20) PRIMARY KEY, "
                + "password VARCHAR(20));"
        ).executeUpdate();
        
        connex.prepareStatement("CREATE TABLE IF NOT EXISTS Exercises ("
                + "question VARCHAR(50) PRIMARY KEY,"
                + "answer VARCHAR(20),"
                + "level INTEGER);"
        ).executeUpdate();
    }
    
    public void reset() throws SQLException {
        Connection connex = getConnection();
        connex.prepareStatement("DELETE FROM Players").executeUpdate();
        connex.prepareStatement("DELETE FROM Exercises").executeUpdate();
    }
}
