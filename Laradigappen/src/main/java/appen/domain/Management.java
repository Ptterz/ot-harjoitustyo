package appen.domain;

import appen.dao.*;
import java.sql.*;
import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

/**
 * A class to handle all the logic behind the application.
 *
 * @author Pete
 * @version 1.1
 * @since 1.0
 */
public class Management {

    private final Calculator calc;
    private final PlayerDao pd;
    private final ExerciseDao ed;
    private final PerformanceDao perfD;
    private final Random r;
    private final Database db;
    private Exercise lastExe;
    private Player playerIn;
    private Performance lastPerf;
    private int selectedPlayLevel;
    private int selectedCreateLevel;

    /**
     * A constructor for a variable of the management class.
     * 
     * @param b A boolean value to connect to different database for tests.
     * @since 1.0
     */
    public Management(boolean b) {
        this.calc = new Calculator();
        if (b) {
            db = new Database("jdbc:sqlite:laradigappen.db");
            db.init();
        } else {
            db = new Database("jdbc:sqlite:test.db");
            db.init();
        }
        this.pd = new PlayerDao(db);
        this.ed = new ExerciseDao(db);
        this.perfD = new PerformanceDao(db);
        this.r = new Random();
        this.selectedCreateLevel = 0;
        this.selectedPlayLevel = 0;
    }

    public Database getDb() {
        return db;
    }
    
    public Calculator getCalc() {
        return calc;
    }

    public PlayerDao getPd() {
        return pd;
    }

    public ExerciseDao getEd() {
        return ed;
    }

    public Performance getLatestPerformance() {
        return lastPerf;
    }

    public void setSelectedPlayLevel(int level) {
        this.selectedPlayLevel = level;
    }

    public void setSelectedCreateLevel(int level) {
        this.selectedCreateLevel = level;
    }

    public String getPlayerNick() {
        return playerIn.getNickname();
    }

    public String getPlayerPassword() {
        return playerIn.getPassword();
    }

    private boolean checkCharsNickname(String s) {
        for (int i = 0; i < s.length(); i++) {
            int ascii = (int) s.charAt(i);
            if (!((ascii >= 48 && ascii <= 57) || (ascii >= 65 && ascii <= 90)
                    || (ascii >= 97 && ascii <= 122))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCharsFormula(String s) {
        for (int i = 0; i < s.length(); i++) {
            int ascii = (int) s.charAt(i);
            if (!((ascii >= 47 && ascii <= 57) || (ascii >= 40 && ascii <= 43)
                    || ascii == 45)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCharsFunction(String s) {
        for (int i = 0; i < s.length(); i++) {
            int ascii = (int) s.charAt(i);
            if (!((ascii >= 47 && ascii <= 57) || (ascii >= 40 && ascii <= 43)
                    || ascii == 45 || ascii == 120)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkLength(String s) {
        if (s.length() == 0 || s.length() > 20) {
            return false;
        }
        return true;
    }

    /**
     * Validates whether or not the given string is acceptable.
     * <p>
     * Method uses ASCII values of characters to validate the string. Accepted
     * characters are a-z, A-Z and 0-9. The lenght of the string is supposed to
     * be equal or greater than 1 and equal or less than 20.
     * </p>
     *
     * @param s String to be validated.
     * @return Returns true if string does not contain invalid characters and
     * meets the length limits.
     * @since 1.0
     */
    public boolean checkEntryNickname(String s) {
        return checkCharsNickname(s) && checkLength(s);
    }

    private boolean checkEntryFormula(String s) {
        return checkCharsFormula(s) && checkLength(s);
    }

    private boolean checkEntryFunction(String s) {
        return checkCharsFunction(s) && checkLength(s);
    }

    private boolean checkEntryValue(String s) {
        try {
            long i = Long.parseLong(s);
        } catch (Exception ex) {
            return false;
        }
        return checkLength(s);
    }

    /**
     * Validates whether or not the given nickname and password match up any
     * existing user.
     * <p>
     * Method fetch a player from the database that matches the given name and
     * then compares its password to the given one.
     * </p>
     *
     * @param name Name to be checked.
     * @param password Password to be compared.
     * @return Returns true if a player of the given name exists and passwords
     * match.
     * @since 1.0
     */
    public boolean checkLoginEntry(String name, String password) {
        Player p = new Player(name, password);

        try {
            Player o = pd.read(name);
            if (p.getPassword().equals(o.getPassword())) {
                playerIn = p;
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates whether or not the given nickname is already in use.
     * <p>
     * Method fetch all players from the database and compares the given name to
     * each player.
     * </p>
     *
     * @param name Name to be checked.
     * @return Returns true if players were fetched successfully and there was
     * no match between the given name and any existing player.
     * @since 1.0
     */
    public boolean checkNameAvailability(String name) {
        List<Player> players;

        try {
            players = pd.list();
        } catch (Exception e) {
            return false;
        }

        for (Player p : players) {
            if (p.getNickname().equals(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates whether or not the two strings match.
     * <p>
     * First, the method compares if the two given strings match. It then checks
     * the general requirements with the method
     * {@link #checkEntryNickname(java.lang.String)}.
     * </p>
     *
     * @param ps1 A given string to be compared.
     * @param ps2 Another given string to be compared.
     * @return Returns true if the two strings are a match that it meets general
     * requirements.
     * @since 1.0
     */
    public boolean checkPasswordEntry(String ps1, String ps2) {
        if (!ps1.equals(ps2)) {
            return false;
        }
        return checkEntryNickname(ps1);
    }

    /**
     * Use to create a new player.
     * <p>
     * Method calls for Create method of PlayerDao class.
     * </p>
     *
     * @param name The nickname of the new player.
     * @param password The password for given nickname.
     * @return Returns true if the player is successfully added to the database.
     * @since 1.0
     */
    public boolean createAccount(String name, String password) {
        Player p = new Player(name, password);
        try {
            pd.create(p);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Changes player's password.
     *
     * @param password Player's new password.
     * @return Return true if password is updated successfully to the database.
     * @since 1.0
     */
    public boolean changePassword(String password) {
        playerIn.setPassword(password);
        try {
            pd.update(playerIn);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates a given string.
     *
     * @param s A string to be validated.
     * @return true if the string given is valid.
     * @since 1.0
     */
    public boolean checkSubmittedFormula(String s) {
        return checkEntryFormula(s);
    }

    /**
     * Validates given strings.
     *
     * @param f A formula to be validated.
     * @param v A value of variable x to be validated.
     * @return Returns true if both parameters are acceptable.
     * @since 1.0
     */
    public boolean checkSubmittedFunction(String f, String v) {
        return checkEntryFunction(f) && checkEntryValue(v);
    }

    /**
     * Calculates the answer for the given formula.
     *
     * @param s A formula to be calculated.
     * @return Return true only if the answer is a acceptable value and
     * {@link #createExercise(java.lang.String, java.lang.String, int)} returns
     * true.
     * @since 1.0
     */
    public boolean calculate(String s) {
        long answer = 0;
        //If the calculated answer is over or under Max/Min values (19-20 chars), calculator throws an exception
        try {
            answer = calc.calculate(s);
        } catch (Exception e) {
            return false;
        }
        return createExercise(s, String.valueOf(answer), selectedCreateLevel);
    }

    /**
     * Calculates the answer for the given function.
     *
     * <p>
     * First, the variable x in the given function is substituted with the given
     * value of x. Then the calculation method of {@link #calc} is called to
     * calculate the value.
     * </p>
     *
     * @param f A function to be calculated.
     * @param v The value of x for given function.
     * @return Return true only if the answer is a acceptable value and
     * {@link #createExercise(java.lang.String, java.lang.String, int)} returns
     * true.
     * @since 1.0
     */
    public boolean calculateFunction(String f, String v) {
        String newF = "";
        for (int i = 0; i < f.length(); i++) {
            int ascii = (int) f.charAt(i);
            if (ascii == 120) {
                newF += v;
            } else {
                newF += f.charAt(i);
            }
        }
        long answer = 0;
        try {
            answer = calc.calculate(newF);
        } catch (Exception e) {
            return false;
        }
        String exercise = "f(x) = " + f + ", x = " + v;
        return createExercise(exercise, String.valueOf(answer), selectedCreateLevel);
    }

    private boolean createExercise(String question, String answer, int lvl) {
        Exercise exercise = new Exercise(question, answer, lvl);
        try {
            for (Exercise exe : ed.list()) {
                if (exe.equals(exercise)) {
                    return false;
                }
            }
            ed.create(exercise);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Creates a list of exercises suited for selected level and randomly picks
     * one of them.
     *
     * @return Returns a exercise randomly selected from the created list.
     * @since 1.0
     */
    public String getExercise() {
        List<Exercise> list = new ArrayList<>();
        try {
            for (Exercise ex : ed.list()) {
                if (ex.getLevel() == selectedPlayLevel || selectedPlayLevel == 0) {
                    list.add(ex);
                }
            }
        } catch (Exception e) {
            return "Something went wrong!";
        }
        if (list.isEmpty()) {
            lastExe = null;
            return "No exercises to solve! Create a few first.";
        }

        int index = r.nextInt(list.size());
        lastExe = list.get(index);
        return lastExe.getQuestion();
    }

    /**
     * Returns the answer of the current exercise.
     *
     * @return Returns the answer of the current exercise.
     * @since 1.0
     */
    public String getAnswer() {
        if (lastExe == null) {
            return "No answer";
        }
        return lastExe.getAnswer();
    }

    /**
     * Returns a string that tells how much time got spent.
     *
     * @param a Time when a game started.
     * @param b Time when a game ended.
     * @return Returns time spent in minutes and seconds.
     * @since 1.0
     */
    public String timeSpent(long a, long b) {
        long deltaSec = (b - a) / 1000;
        String minutes = "";
        String secs = "";

        if (deltaSec / 60 == 0) {
            minutes += 0;
            secs += deltaSec;
        } else {
            minutes += deltaSec / 60;
            deltaSec -= (deltaSec / 60) * 60;
            secs += deltaSec;
        }
        return "Time spent: " + minutes + " min " + secs + " sec";
    }

    /**
     * Creates a new performance record.
     *
     * @param count Tries used to solve the exercise
     * @param time Time spent solving the exercise
     * @since 1.1
     */
    public void createNewPerformance(int count, long time) {
        Performance p = new Performance(playerIn, lastExe, count, time);
        lastPerf = p;
        try {
            perfD.create(p);
        } catch (Exception e) {
            System.out.println("");
        }
    }

    /**
     * Returns a string telling how many performed worse.
     *
     * @return A string containing the result.
     * @since 1.1
     */
    public String getResult() {
        try {
            double result = perfD.getBetterThan(lastPerf);
            return "You scored better than " + result + "%.";
        } catch (Exception e) {
            return "Failed to get results.";
        }
    }
    
    public AnimationTimer getTimer(Stage window) {
        AnimationTimer timer = new AnimationTimer() {
            long edellinen = 0;
            long counter = 0;

            @Override
            public void handle(long nykyhetki) {
                if (nykyhetki - edellinen < 1000000000) {
                    if (counter < 400) {
                        counter++;
                        return;
                    }
                    window.close();
                }

                this.edellinen = nykyhetki;
            }
        };
        return timer;
    }
}
