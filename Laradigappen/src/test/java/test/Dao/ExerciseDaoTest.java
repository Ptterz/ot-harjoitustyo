
package test.Dao;

import appen.dao.ExerciseDao;
import appen.database.Database;
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
        db.reset();
        db.init();
        ed = new ExerciseDao(db);
    }
    
    @Test
    public void read() throws SQLException {
        
    }
    
    @Test
    public void update() throws SQLException {
        
    }
    
    @Test
    public void delete() throws SQLException {
        
    }
}
