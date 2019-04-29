package test.Domain;

import appen.dao.*;
import appen.dao.Database;
import appen.domain.*;
import java.sql.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ManagementTest {

    Management mg;

    //Pystyy lisätä esim pd.create() mutta yhteys jää auki (?) -> constraint violation
    @Before
    public void setUp() throws SQLException {
        mg = new Management(false);
        mg.setSelectedPlayLevel(1);
        mg.setSelectedCreateLevel(1);
    }

    @After
    public void tearDown() throws SQLException {
        mg.getDb().reset();
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

//    @Test
//    public void createAccountIncorrectAddress() throws Exception {
//        mg.getDb().reset();
//        assertFalse(mg.createAccount("Pete", "123"));
//    }

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

//    @Test
//    public void checkNameAvailabilityFalse1() throws Exception {
//        mg.getDb().reset();mvn test jacoco:report
//        assertFalse(mg.checkNameAvailability("Pete"));
//    }

    @Test
    public void checkNameAvailabilityFalse2() {
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
    public void checkLoginEntryFalse4() throws Exception {
        mg.getDb().reset();
        assertFalse(mg.checkLoginEntry("Pete", "password"));
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

//    @Test
//    public void calculateFalse1() throws Exception {
//        mg.getDb().reset();
//        assertFalse(mg.calculate("2+2"));
//    }

    @Test
    public void calculateFalse2() {
        assertFalse(mg.calculate("(2+/2"));
    }

    @Test
    public void calculateFalse3() {
        assertFalse(mg.calculate("9223372036854775808"));
    }

    @Test
    public void getExerciseTrue1() {
        mg.calculate("2+2");
        assertEquals("2+2", mg.getExercise());
    }

    @Test
    public void getExerciseTrue2() {
        mg.setSelectedPlayLevel(0);
        mg.calculate("2+2");
        assertEquals("2+2", mg.getExercise());
    }

    @Test
    public void getExerciseFalse1() throws Exception {
        mg.getDb().reset();
        assertEquals("No exercises to solve! Create a few first.", mg.getExercise());
    }

    @Test
    public void getExerciseFalse2() {
        mg.setSelectedPlayLevel(2);
        assertEquals("No exercises to solve! Create a few first.", mg.getExercise());
    }

    @Test
    public void getAnswerTrue() {
        mg.calculate("2+2");
        mg.getExercise();
        assertEquals("4", mg.getAnswer());
    }

    @Test
    public void getAnswerFalse() throws Exception {
        mg.getDb().reset();
        assertEquals("No answer", mg.getAnswer());
    }

    @Test
    public void getNickname() {
        mg.createAccount("Pete", "1234");
        mg.checkLoginEntry("Pete", "1234");
        assertEquals("Pete", mg.getPlayerNick());
    }

    @Test
    public void getPassword() {
        mg.createAccount("Pete", "1234");
        mg.checkLoginEntry("Pete", "1234");
        assertEquals("1234", mg.getPlayerPassword());
    }

    @Test
    public void checkInvalidFunction1() {
        assertFalse(mg.checkSubmittedFunction("X+2", "X"));
    }

    @Test
    public void checkInvalidFunction2() {
        assertFalse(mg.checkSubmittedFunction("", " "));
    }

    @Test
    public void checkInvalidFunction3() {
        assertFalse(mg.checkSubmittedFunction(" ", ""));
    }

    @Test
    public void checkInvalidFunction4() {
        assertFalse(mg.checkSubmittedFunction("@", "1"));
    }

    @Test
    public void checkInvalidFunction5() {
        assertFalse(mg.checkSubmittedFunction("&", "1"));
    }

    @Test
    public void checkInvalidFunction6() {
        assertFalse(mg.checkSubmittedFunction("000001111100000111110000011111000001111100000111110", "3"));
    }

    @Test
    public void checkInvalidFunction7() {
        assertFalse(mg.checkSubmittedFunction("x-4", " "));
    }

    @Test
    public void checkValidFunction1() {
        assertTrue(mg.checkSubmittedFunction("2+x-2*x/2", "2"));
    }

    @Test
    public void calculateFunctionTrue() {
        assertTrue(mg.calculateFunction("(2+3)/x", "5"));
    }

    @Test
    public void calculateFunctionFalse() {
        assertFalse(mg.calculateFunction("9223372036854775808*x", "5"));
    }

    @Test
    public void changePasswordTrue() {
        mg.createAccount("Pete", "1234");
        mg.checkLoginEntry("Pete", "1234");
        assertTrue(mg.changePassword("0000"));
    }

    @Test
    public void changePasswordFalse() throws SQLException {
        mg.createAccount("Pete", "1234");
        mg.checkLoginEntry("Pete", "1234");
        mg.getPd().delete("Pete");
        assertFalse(mg.changePassword("0000"));
    }

    @Test
    public void timeSpent1() throws SQLException {
        assertEquals("Time spent: 0 min 50 sec", mg.timeSpent(50000, 100000));
    }

    @Test
    public void timeSpent2() throws SQLException {
        assertEquals("Time spent: 2 min 30 sec", mg.timeSpent(50000, 200000));
    }

    @Test
    public void newPerformance() throws SQLException {
        mg.createAccount("Pete", "1234");
        mg.checkLoginEntry("Pete", "1234");
        mg.calculate("2+2");
        mg.getExercise();
        mg.createNewPerformance(0, 5000);
    }
}
