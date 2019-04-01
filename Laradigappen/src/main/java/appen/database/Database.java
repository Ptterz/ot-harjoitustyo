
package appen.database;

import java.sql.Connection;
import java.sql.DriverManager;
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
        connex.prepareStatement("CREATE TABLE IF NOT EXISTS Player ("
                + "nickname VARCHAR(20) PRIMARY KEY,"
                + "password VARCHAR(20));"
        ).executeUpdate();
        
        connex.prepareStatement("CREATE TABLE IF NOT EXISTS Exercise ("
                + "id INTEGER PRIMARY KEY,"
                + "question VARCHAR(50),"
                + "answer VARCHAR(30));"
        ).executeUpdate();
    }
}