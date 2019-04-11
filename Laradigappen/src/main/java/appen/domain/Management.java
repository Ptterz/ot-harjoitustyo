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
    private int selectedPlayLevel;
    private int selectedCreateLevel;
    private Map<Integer, List<Exercise>> exesMap;

    public Management(PlayerDao pd, ExerciseDao ed) {
        this.calc = new Calculator();
        this.pd = pd;
        this.ed = ed;
        this.r = new Random();
        this.selectedCreateLevel = 0;
        this.selectedPlayLevel = 0;
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

    public void setSelectedPlayLevel(int level) {
        this.selectedPlayLevel = level;
    }

    public void setSelectedCreateLevel(int level) {
        this.selectedCreateLevel = level;
    }

    public String getPlayerNick() {
        return playerIn.getNickname();
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

    private boolean checkLengthNickname(String s) {
        if (s.length() == 0 || s.length() > 20) {
            return false;
        }
        return true;
    }

    private boolean checkLengthExercise(String s) {
        if (s.length() == 0 || s.length() > 50) {
            return false;
        }
        return true;
    }

    public boolean checkEntryNickname(String s) {
        return checkCharsNickname(s) && checkLengthNickname(s);
    }

    private boolean checkEntryFormula(String s) {
        return checkCharsFormula(s) && checkLengthExercise(s);
    }

    private boolean checkEntryFunction(String s) {
        return checkCharsFunction(s) && checkLengthExercise(s);
    }

    private boolean checkEntryValue(String s) {
        try {
            long i = Long.parseLong(s);
        } catch (Exception ex) {
            return false;
        }
        return checkLengthExercise(s);
    }

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

    public boolean checkSubmittedFunction(String f, String v) {
        return checkEntryFunction(f) && checkEntryValue(v);
    }

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
            ed.create(exercise);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

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
            return "No exercises to solve!";
        }

        int index = r.nextInt(list.size());
        lastExe = list.get(index);
        return lastExe.getQuestion();
    }

    public String getAnswer() {
        return lastExe.getAnswer();
    }
}
