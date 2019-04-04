package test.Domain;

import appen.dao.*;
import appen.database.Database;
import appen.domain.*;
import java.sql.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ManagementTest {

    PlayerDao pd;
    PlayerDao pd2;
    ExerciseDao ed;
    ExerciseDao ed2;
    Database db;
    Database db2;
    Management mg;
    Management mg2;

    //Pystyy lisätä esim pd.create() mutta yhteys jää auki (?) -> constraint violation
    @Before
    public void setUp() throws SQLException {
        db = new Database("jdbc:sqlite:test.db");
        db.init();
        db.reset();
        pd = new PlayerDao(db);
        ed = new ExerciseDao(db);
        mg = new Management(pd, ed);

        db2 = new Database("");
        pd2 = new PlayerDao(db2);
        ed2 = new ExerciseDao(db2);
        mg2 = new Management(pd2, ed2);
    }

    @Test
    public void calculatorExists() {
        assertTrue(mg.getCalc() != null);
    }

    @Test
    public void playerDaoExists() {
        assertTrue(mg.getPd() != null);
    }

    @Test
    public void exerciseDaoExists() {
        assertTrue(mg.getEd() != null);
    }   

    @Test
    public void createAccount() {
        assertTrue(mg.createAccount("Pete", "salasana"));
    }

    @Test
    public void createAccountIncorrectAddress() {
        assertFalse(mg2.createAccount("Pete", "123"));
    }

    @Test
    public void checkLoginEntry() {
        assertFalse(mg.checkLoginEntry(" ", "/"));
        assertFalse(mg.checkLoginEntry("/", "&"));
        assertFalse(mg.checkLoginEntry("@", "{}"));
        assertFalse(mg.checkLoginEntry("", "000011110000111100001"));
    }

    @Test
    public void checkNameAvailability() {
        assertTrue(mg.checkNameAvailability("Pete"));
    }

    @Test
    public void checkPasswordsEquals() {
        assertFalse(mg.checkPasswordEntry("aaa", "vb"));
        assertFalse(mg.checkPasswordEntry("1234@", "123"));
        assertFalse(mg.checkPasswordEntry("1234@", "1234@"));
        assertFalse(mg.checkPasswordEntry("1234", "123+"));
        assertTrue(mg.checkPasswordEntry("salasana", "salasana"));
    }
}
