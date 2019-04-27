package test.Dao;

import appen.dao.Database;
import appen.dao.*;
import appen.domain.*;
import java.sql.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerDaoTest {

    Database db;
    Connection connex;
    PlayerDao pd;

    @Before
    public void setUp() throws SQLException {
        db = new Database("jdbc:sqlite:test.db");
        db.init();
        pd = new PlayerDao(db);
    }

    @After
    public void tearDown() throws SQLException {
        db.reset();
    }

    @Test
    public void update() throws SQLException {
        Player p = new Player("Pete", "1234");
        pd.create(p);
        p.setPassword("0000");
        pd.update(p);
        assertEquals("0000", pd.read(p.getNickname()).getPassword());
    }

    @Test
    public void delete() throws SQLException {
        Player p = new Player("Pete", "1234");
        pd.create(p);
        assertEquals(p, pd.read(p.getNickname()));
        pd.delete(p.getNickname());
        assertEquals(null, pd.read(p.getNickname()));
    }
}
