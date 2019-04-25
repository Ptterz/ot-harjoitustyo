package appen.dao;

import appen.domain.*;
import java.sql.*;

public class PerformanceDao implements perfDao<Performance, Integer> {

    private Database db;
    private int idCounter;

    /**
     * Contructor for PerformanceDao.
     *
     * @param db Initalized database
     * @since 1.1
     */
    public PerformanceDao(Database db) {
        this.db = db;
        this.idCounter = 1;
    }

    private int generateId() {
        return idCounter++;
    }

    @Override
    public void create(Performance p) throws SQLException {
        p.setId(generateId());

        Connection connex = db.getConnection();
        
        PreparedStatement stmt = connex.prepareStatement("INSERT INTO Performances"
                + " (id, playerid, exerciseid, count)"
                + " VALUES (?, ?, ?, ?)");
        stmt.setInt(1, p.getId());
        stmt.setString(2, p.getPlayer().getNickname());
        stmt.setInt(3, p.getExercise().getId());
        stmt.setInt(4, p.getCount());

        stmt.executeUpdate();
        stmt.close();
    }
    
    @Override
    public double getBetterThan(Performance p) {
        Double d = 0.0;
        return d;
    }
}
