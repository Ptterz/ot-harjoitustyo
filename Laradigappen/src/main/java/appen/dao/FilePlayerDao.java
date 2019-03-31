package appen.dao;

import appen.database.Database;
import appen.domain.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FilePlayerDao implements PlayerDao {

    public List<Player> players;
    private Database db;
    private boolean upToDate;

    public FilePlayerDao(Database db) {
        this.players = new ArrayList<>();
        this.db = db;
        
        fetchPlayers();
    }
    
    private void fetchPlayers() {
        try {
            Connection connex = db.getConnection();
            Statement statement = connex.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Player");
            while(rs.next()) {
                Player p = new Player(rs.getString("nickname"), rs.getString("password"));
                players.add(p);
            }
            statement.close();
            rs.close();
            connex.close();
            upToDate = true;
        } catch (Exception e) {
            upToDate = false;
        }
    }
    
    private void saveChangedPlayers() throws Exception {
        Connection connex = db.getConnection();
        for (Player p : players) {
            if (!p.isChanged()) {
                continue;
            }
            String query = "UPDATE Player SET password = " + p.getPassword()
                    + " WHERE nickname = " + p.getNickname();
            Statement statement = connex.createStatement();
            statement.executeUpdate(query);
            statement.close();
        }
        connex.close();
    }

    private void addNewPlayer(String nickname, String password) throws Exception {
        Connection connex = db.getConnection();
        String query = "INSERT INTO Player VALUES (" + nickname
                + "," + password + ")";
        Statement statement = connex.createStatement();
        statement.executeUpdate(query);
        statement.close();
        connex.close();
    }
    
    @Override
    public boolean createNewPlayer(String nickname, String password) {
        Player player = new Player(nickname, password);
        players.add(player);
        try {
            addNewPlayer(nickname, password);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Player> listAllPlayers() {
        if (!upToDate) {
            fetchPlayers();
        }
        return players;
    }
    
    @Override
    public boolean quit() {
        try {
            saveChangedPlayers();
            return true;
        } catch (Exception e) {
            return false;
        }
    } 

}
