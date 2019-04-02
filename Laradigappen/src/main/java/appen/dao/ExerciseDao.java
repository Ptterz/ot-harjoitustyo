package appen.dao;

import appen.database.Database;
import appen.domain.Exercise;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDao implements Dao<Exercise, Integer> {

    private Database db;
    private int id;
    private List<Exercise> exes;

    public ExerciseDao(Database db) throws SQLException {
        this.db = db;
        this.id = 1;
        this.exes = new ArrayList<>();
    }

    private int generateId() {
        return id++;
    }

    @Override
    public void create(Exercise e) throws SQLException {
        this.exes.add(e);
        
        Connection connex = db.getConnection();

        PreparedStatement stmt = connex.prepareStatement("INSERT INTO Exercises"
                + " (id, question, answer)"
                + " VALUES (?, ?, ?)");
        stmt.setInt(1, generateId());
        stmt.setString(2, e.getQuestion());
        stmt.setString(3, e.getAnswer());

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public Exercise read(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Exercise update(Exercise object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Exercise> list() throws SQLException {
        if (this.exes.isEmpty()) {
            Connection connex = db.getConnection();
            PreparedStatement stmt = connex.prepareStatement("SELECT * FROM Exercises");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                Exercise e = new Exercise(question, answer);
                exes.add(e);
            }
        }
        return exes;
    }
}
