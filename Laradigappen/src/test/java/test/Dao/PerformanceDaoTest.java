package test.Dao;

import appen.dao.*;
import appen.domain.*;
import java.sql.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PerformanceDaoTest {

    Database db;
    Connection connex;
    PerformanceDao pd;

    @Before
    public void setUp() throws SQLException {
        db = new Database("jdbc:sqlite:test.db");
        db.init();
        pd = new PerformanceDao(db);
    }

    @After
    public void tearDown() throws SQLException {
        db.reset();
    }
    
    @Test
    public void create() throws SQLException {
        pd.create(new Performance(new Player("Pete", "1234"), new Exercise(1, "kysymys", "vastaus", 1), 2, 5000));
    }
    
    @Test
    public void comparisonTest() throws SQLException {
        pd.create(new Performance(new Player("Pete", "1234"), new Exercise(1, "kysymys", "vastaus", 1), 2, 5000));
        pd.create(new Performance(new Player("Jani", "1234"), new Exercise(1, "kysymys", "vastaus", 1), 1, 10000));
        pd.create(new Performance(new Player("Pekka", "1234"), new Exercise(1, "kysymys", "vastaus", 1), 4, 2000));
        double d = pd.getBetterThan(new Performance(new Player("Joni", "1234"), new Exercise(1, "kysymys", "vastaus", 1), 1, 8000));
        assertEquals(100.0, d, 0.1);
    }
}
