package appen.dao;

import appen.domain.*;
import java.sql.*;

public class PerformanceDao implements PerfDao<Performance, Integer> {

    private Database db;
    private int idCounter;

    /**
     * A constructor for PerformanceDao.
     *
     * @param db Initialized database
     * @since 1.1
     */
    public PerformanceDao(Database db) {
        this.db = db;
        try {
            Connection connex = db.getConnection();
            PreparedStatement stmt = connex.prepareStatement("SELECT COUNT(id) AS total FROM Performances;");
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                idCounter = 1;
            } else {
                idCounter = rs.getInt("total") + 1;
            }

            stmt.close();
            rs.close();
        } catch (Exception e) {
        }
    }

    private int generateId() {
        return idCounter++;
    }

    @Override
    public void create(Performance p) throws SQLException {
        p.setId(generateId());

        Connection connex = db.getConnection();

        PreparedStatement stmt = connex.prepareStatement("INSERT INTO Performances"
                + " (id, playerid, exerciseid, score)"
                + " VALUES (?, ?, ?, ?);");
        stmt.setInt(1, p.getId());
        stmt.setString(2, p.getPlayer().getNickname());
        stmt.setInt(3, p.getExercise().getId());
        stmt.setInt(4, p.getScore());

        stmt.executeUpdate();
        stmt.close();
    }

    private int getAllRecordsOfTheExercise(Performance p) throws SQLException {
        int total;
        Connection connex = db.getConnection();
        PreparedStatement stmt = connex.prepareStatement("SELECT COUNT(id) AS total FROM Performances "
                + "WHERE exerciseid = ?;");
        stmt.setInt(1, p.getExercise().getId());
        ResultSet rs = stmt.executeQuery();

        total = rs.getInt("total");

        stmt.close();
        rs.close();

        return total;
    }

    private int getWorseRecords(Performance p) throws SQLException {
        int worse;
        Connection connex2 = db.getConnection();

        PreparedStatement stmt2 = connex2.prepareStatement("SELECT COUNT(id) AS worse FROM Performances "
                + "WHERE (exerciseid = ? AND score < ?);");
        stmt2.setInt(1, p.getExercise().getId());
        stmt2.setInt(2, p.getScore());
        ResultSet rs2 = stmt2.executeQuery();

        worse = rs2.getInt("worse");

        stmt2.close();
        rs2.close();

        return worse;
    }

    @Override
    public double getBetterThan(Performance p) throws SQLException {
        int total = getAllRecordsOfTheExercise(p);
        int worse = getWorseRecords(p);

        double d;
        d = (double) worse / total;
        d *= 10000;
        d = Math.round(d);
        d /= 100;

        return d;
    }
}
