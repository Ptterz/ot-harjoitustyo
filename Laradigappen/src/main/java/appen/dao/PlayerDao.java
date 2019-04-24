package appen.dao;

import appen.database.*;
import appen.domain.*;
import java.sql.*;
import java.util.*;

public class PlayerDao implements Dao<Player, String> {

    private Database db;
    
    /**
     * Contructor for PlayerDao.
     * @param db Initalized database
     * @since 1.0
     */
    public PlayerDao(Database db) {
        this.db = db;
    }

    @Override
    public void create(Player p) throws SQLException {
        Connection connex = db.getConnection();

        PreparedStatement stmt = connex.prepareStatement("INSERT INTO Players"
                + " (nickname, password)"
                + " VALUES (?, ?)");
        stmt.setString(1, p.getNickname());
        stmt.setString(2, p.getPassword());

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public Player read(String key) throws SQLException {
        Connection connex = db.getConnection();
        PreparedStatement stmt = connex.prepareStatement("SELECT * FROM Players WHERE nickname = ?");
        stmt.setString(1, key);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        Player player = new Player(rs.getString("nickname"), rs.getString("password"));

        stmt.close();
        rs.close();

        return player;
    }

    @Override
    public Player update(Player p) throws SQLException {
        Connection connex = db.getConnection();
        PreparedStatement stmt = connex.prepareStatement("UPDATE Players SET password = ? WHERE nickname = ?");
        stmt.setString(1, p.getPassword());
        stmt.setString(2, p.getNickname());
        int i = stmt.executeUpdate();
        
        if (i == 0) {
            //There was no rows updated
            throw new SQLException();
        }

        stmt.close();

        return p;
    }

    @Override
    public void delete(String key) throws SQLException {
        Connection connex = db.getConnection();
        PreparedStatement stmt = connex.prepareStatement("DELETE FROM Players WHERE nickname = ?");
        stmt.setString(1, key);
        stmt.executeUpdate();

        stmt.close();
    }

    @Override
    public List<Player> list() throws SQLException {
        List<Player> plays = new ArrayList<>();

        Connection connex = db.getConnection();
        PreparedStatement stmt = connex.prepareStatement("SELECT * FROM Players");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String nickname = rs.getString("nickname");
            String password = rs.getString("password");
            Player p = new Player(nickname, password);
            plays.add(p);
        }

        return plays;
    }

}
