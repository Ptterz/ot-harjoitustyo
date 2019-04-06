package appen.domain;

import appen.dao.*;
import appen.database.*;
import java.util.*;

public class Management {

    private Calculator calc;
    private PlayerDao pd;
    private ExerciseDao ed;
    private Random r;
    private Exercise lastExe;
    private Player playerIn;
    private int selectedLevel;
    private Map<Integer, List<Exercise>> exes;

    public Management(PlayerDao pd, ExerciseDao ed) {
        this.calc = new Calculator();
        this.pd = pd;
        this.ed = ed;
        this.r = new Random();
        exes = new HashMap<>();
        exes.put(0, new ArrayList<>());
        exes.put(1, new ArrayList<>());
        exes.put(2, new ArrayList<>());
        exes.put(3, new ArrayList<>());
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
    
    public void setSelectedLevel(int level) {
        this.selectedLevel = level;
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

    private boolean checkLengthNickname(String s) {
        if (s.length() == 0 || s.length() > 20) {
            return false;
        }

//        if (s.trim().length() == 0) {
//            return false;
//        }
        return true;
    }

    private boolean checkLengthFormula(String s) {
        if (s.length() == 0 || s.length() > 50) {
            return false;
        }

//        if (s.trim().length() == 0) {
//            return false;
//        }
        return true;
    }

    public boolean checkEntryNickname(String s) {
        return checkCharsNickname(s) && checkLengthNickname(s);
    }

    private boolean checkEntryFormula(String s) {
        return checkCharsFormula(s) && checkLengthFormula(s);
    }

    public boolean checkLoginEntry(String name, String password) {
        Player p = new Player(name, password);

        try {
            Player o = pd.read(name);
            if (p.getNickname().equals(o.getNickname())
                    && p.getPassword().equals(o.getPassword())) {
                playerIn = p;
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkNameAvailability(String name) {
        List<Player> players = new ArrayList<>();

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

    public boolean checkPasswordEntry(String ps1, String ps2) {
        if (!ps1.equals(ps2)) {
            return false;
        }
        return checkEntryNickname(ps1);
    }

    public boolean createAccount(String name, String password) {
        Player p = new Player(name, password);
        try {
            pd.create(p);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean checkSubmittedFormula(String s) {
        return checkEntryFormula(s);
    }

    public boolean calculate(String s) {
        long answer = 0;
        //If the calculated answer is over or under Max/Min values (19-20 chars), calculator throws an exception
        try {
            answer = calc.calculate(s);
        } catch (Exception e) {
            return false;
        }
        return createExercise(s, String.valueOf(answer));
    }

    private boolean createExercise(String question, String answer, int lvl) {
        Exercise exercise = new Exercise(question, answer, -1);
        exes.get(lvl).add(exercise);
        try {
            ed.create(exercise);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    private boolean getExercises() {
        try {
            exes.put(0, ed.list());
            if (selectedLevel != 0) {
                for (Exercise e : exes.get(0)) {
                    if (e.getLevel() == selectedLevel) {
                        exes.get(selectedLevel).add(e);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getExercise() {
        if (exes.get(selectedLevel).isEmpty()) {
            if (!getExercises()) {
                return "Something went wrong!";
            }
        }
        int index = r.nextInt(exes.size());
        lastExe = exes.get(selectedLevel).get(index);
        return lastExe.getQuestion();
    }

    public String getAnswer() {
        return lastExe.getAnswer();
    }
}
