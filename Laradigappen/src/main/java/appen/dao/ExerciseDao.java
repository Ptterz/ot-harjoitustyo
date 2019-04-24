package appen.dao;

import appen.database.Database;
import appen.domain.Exercise;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDao implements Dao<Exercise, String> {

    private Database db;
    
    /**
     * Contructor for ExerciseDao.
     * @param db Initalized database
     * @since 1.0
     */
    public ExerciseDao(Database db) {
        this.db = db;
    }

    @Override
    public void create(Exercise e) throws SQLException {
        Connection connex = db.getConnection();

        PreparedStatement stmt = connex.prepareStatement("INSERT INTO Exercises"
                + " (question, answer, level)"
                + " VALUES (?, ?, ?)");
        stmt.setString(1, e.getQuestion());
        stmt.setString(2, e.getAnswer());
        stmt.setInt(3, e.getLevel());

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public Exercise read(String key) throws SQLException {
        Connection connex = db.getConnection();
        PreparedStatement stmt = connex.prepareStatement("SELECT * FROM Exercises WHERE question = ?");
        stmt.setString(1, key);
        ResultSet rs = stmt.executeQuery();
        
        if (!rs.next()) {
            return null;
        }

        Exercise e = new Exercise(rs.getString("question"), rs.getString("answer"), rs.getInt("level"));

        stmt.close();
        rs.close();

        return e;
    }

    @Override
    public Exercise update(Exercise e) throws SQLException {
        return e;
    }

    @Override
    public void delete(String key) throws SQLException {
        Connection connex = db.getConnection();
        PreparedStatement stmt = connex.prepareStatement("DELETE FROM Exercises WHERE question = ?");
        stmt.setString(1, key);
        stmt.executeUpdate();

        stmt.close();
    }

    @Override
    public List<Exercise> list() throws SQLException {
        List<Exercise> exes = new ArrayList<>();

        Connection connex = db.getConnection();
        PreparedStatement stmt = connex.prepareStatement("SELECT * FROM Exercises");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String question = rs.getString("question");
            String answer = rs.getString("answer");
            int level = rs.getInt("level");
            Exercise e = new Exercise(question, answer, level);
            exes.add(e);
        }

        return exes;
    }
}
