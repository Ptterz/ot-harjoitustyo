package test.Dao;

import appen.dao.*;
import appen.database.*;
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
        db.reset();
        db.init();
        pd = new PlayerDao(db);
    }
}
