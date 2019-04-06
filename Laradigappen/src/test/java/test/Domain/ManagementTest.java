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
    public void checkInvalidCharsNickname1() {
        assertFalse(mg.checkEntryNickname(" "));
    }
    
    @Test
    public void checkInvalidCharsNickname2() {
        assertFalse(mg.checkEntryNickname("/"));
    }
    
    @Test
    public void checkInvalidCharsNickname3() {
        assertFalse(mg.checkEntryNickname("&"));
    }
    
    @Test
    public void checkInvalidCharsNickname4() {
        assertFalse(mg.checkEntryNickname("@"));
    }
    
    @Test
    public void checkInvalidCharsNickname5() {
        assertFalse(mg.checkEntryNickname("|"));
    }
    
    @Test
    public void checkInvalidCharsNickname6() {
        assertFalse(mg.checkEntryNickname(""));
    }
    
    @Test
    public void checkInvalidCharsNickname7() {
        assertFalse(mg.checkEntryNickname("000011110000111100001"));
    }
    
    @Test
    public void checkValidCharsNickname1() {
        assertTrue(mg.checkEntryNickname("00001111000011110000"));
    }
    
    @Test
    public void checkValidCharsNickname2() {
        assertTrue(mg.checkEntryNickname("Test9"));
    }
    
    @Test
    public void checkNameAvailabilityTrue() {
        assertTrue(mg.checkNameAvailability("Pete"));
    }
    
    @Test
    public void checkNameAvailabilityFalse1() {
        assertFalse(mg2.checkNameAvailability("Pete"));
    }
    
    @Test
    public void checkNameAvailabilityFalse2() {
        mg.createAccount("Pette", "password");
        mg.createAccount("Pete", "password");
        assertFalse(mg.checkNameAvailability("Pete"));
    }
    
    @Test
    public void checkNameAvailabilityFalse() {
        mg.createAccount("Pete", "password");
        assertFalse(mg.checkNameAvailability("Pete"));
    }

    @Test
    public void checkPasswordsNotEquals() {
        assertFalse(mg.checkPasswordEntry("aaa", "vb"));
    }
    
    @Test
    public void checkPasswordsEquals() {
        assertTrue(mg.checkPasswordEntry("salasana", "salasana"));
    }
    
    @Test
    public void checkLoginEntryFalse1() {
        assertFalse(mg.checkLoginEntry("Pete", "password"));
    }
    
    @Test
    public void checkLoginEntryFalse2() {
        mg.createAccount("Pete", "password");
        assertFalse(mg.checkLoginEntry("Pete", "pass"));
    }
    
    @Test
    public void checkLoginEntryFalse3() {
        mg.createAccount("Pete", "password");
        assertFalse(mg.checkLoginEntry("Petter", "password"));
    }
    
    @Test
    public void checkLoginEntryFalse4() {
        assertFalse(mg2.checkLoginEntry("Pete", "password"));
    }
    
    @Test
    public void checkLoginEntryTrue() {
        mg.createAccount("Pete", "password");
        assertTrue(mg.checkLoginEntry("Pete", "password"));
    }
    
    @Test
    public void checkInvalidFormula1() {
        assertFalse(mg.checkSubmittedFormula(""));
    }
    
    @Test
    public void checkInvalidFormula2() {
        assertFalse(mg.checkSubmittedFormula(" "));
    }
    
    @Test
    public void checkInvalidFormula3() {
        assertFalse(mg.checkSubmittedFormula("@"));
    }
    
    @Test
    public void checkInvalidFormula4() {
        assertFalse(mg.checkSubmittedFormula("&"));
    }
    
    @Test
    public void checkInvalidFormula5() {
        assertFalse(mg.checkSubmittedFormula("000001111100000111110000011111000001111100000111110"));
    }
    
    @Test
    public void checkValidFormula1() {
        assertTrue(mg.checkSubmittedFormula("2+2-2*2/2"));
    }
    
    @Test
    public void calculateTrue() {
        assertTrue(mg.calculate("(2+3)/5"));
    }
    
    @Test
    public void calculateFalse1() {
        assertFalse(mg2.calculate("2+2"));
    }
    
    @Test
    public void calculateFalse2() {
        assertFalse(mg.calculate("(2+/2"));
    }
    
    @Test
    public void calculateFalse3() {
        assertFalse(mg.calculate("9223372036854775808"));
    }
    
    @Test
    public void getExerciseTrue() {
        mg.calculate("2+2");
        assertEquals("2+2", mg.getExercise());
    }
    
    @Test
    public void getExerciseFalse() {
        assertEquals("Something went wrong.", mg2.getExercise());
    }
    
    @Test
    public void getAnswer() {
        mg.calculate("2+2");
        mg.getExercise();
        assertEquals("4", mg.getAnswer());
    }
    
    @Test
    public void test() {
        mg.quit();
    }
}