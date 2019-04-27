package appen.dao;

import appen.domain.Exercise;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDao implements Dao<Exercise, String> {

    private Database db;
    private List<Exercise> exes;
    private int idCounter;

    /**
     * Contructor for ExerciseDao.
     *
     * @param db Initalized database
     * @since 1.0
     */
    public ExerciseDao(Database db) {
        this.db = db;
        this.exes = new ArrayList<>();
        try {
            Connection connex = db.getConnection();
            PreparedStatement stmt = connex.prepareStatement("SELECT * FROM Exercises");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Exercise e = new Exercise(rs.getInt("id"), rs.getString("question"),
                        rs.getString("answer"), rs.getInt("level"));
                exes.add(e);
            }

            stmt.close();
            rs.close();
        } catch (Exception e) {
        }
        this.idCounter = exes.size() + 1;
    }

    private int generateId() {
        return idCounter++;
    }

    @Override
    public void create(Exercise e) throws SQLException {
        e.setId(generateId());
        exes.add(e);

        Connection connex = db.getConnection();

        PreparedStatement stmt = connex.prepareStatement("INSERT INTO Exercises"
                + " (id, question, answer, level)"
                + " VALUES (?, ?, ?, ?)");
        stmt.setInt(1, e.getId());
        stmt.setString(2, e.getQuestion());
        stmt.setString(3, e.getAnswer());
        stmt.setInt(4, e.getLevel());

        stmt.executeUpdate();
        stmt.close();
    }

    /* This method is not used in application */
    @Override
    public Exercise read(String question) throws SQLException {
        Connection connex = db.getConnection();
        PreparedStatement stmt = connex.prepareStatement("SELECT * FROM Exercises WHERE question = ?");
        stmt.setString(1, question);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        Exercise e = new Exercise(rs.getInt("id"), rs.getString("question"),
                rs.getString("answer"), rs.getInt("level"));

        stmt.close();
        rs.close();

        return e;
    }

    /* This method is not used in application */
    @Override
    public Exercise update(Exercise e) throws SQLException {
        return e;
    }

    /* This method is not used in application */
    @Override
    public void delete(String question) throws SQLException {
        for (Exercise e : exes) {
            if (e.getQuestion().equals(question)) {
                exes.remove(e);
            }
        }

        Connection connex = db.getConnection();
        PreparedStatement stmt = connex.prepareStatement("DELETE FROM Exercises WHERE question = ?");
        stmt.setString(1, question);
        stmt.executeUpdate();

        stmt.close();
    }

    @Override
    public List<Exercise> list() throws SQLException {
        return exes;
    }
}
