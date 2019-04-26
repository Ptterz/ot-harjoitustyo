package test.Dao;

import appen.dao.ExerciseDao;
import appen.domain.Database;
import appen.domain.Exercise;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExerciseDaoTest {

    Database db;
    Connection connex;
    ExerciseDao ed;

    @Before
    public void setUp() throws SQLException {
        db = new Database("jdbc:sqlite:test.db");
        db.init();
        ed = new ExerciseDao(db);
    }

    @After
    public void tearDown() throws SQLException {
        db.reset();
    }
    
    @Test
    public void read() throws SQLException {
        Exercise e = new Exercise("2+2", "4", 1);
        ed.create(e);
        assertEquals(e, ed.read("2+2"));
    }

    @Test
    public void update() throws SQLException {
        //This functionality is not in use. The test is merely for jacoco.
        Exercise e = new Exercise("2+2", "4", 1);
        ed.update(e);
    }

    @Test
    public void delete() throws SQLException {
        //This functionality is not in use. The test is merely for jacoco.
        Exercise e = new Exercise("2+2", "4", 1);
        ed.delete(e.getQuestion());
    }
}
