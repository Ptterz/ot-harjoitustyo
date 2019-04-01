package appen.domain;

import appen.dao.*;
import appen.database.*;
import java.util.*;

public class Management {

    private Calculator calc;
    private PlayerDao pd;
    private ExerciseDao ed;

    public Management(PlayerDao pd, ExerciseDao ed) {
        this.calc = new Calculator();
        this.pd = pd;
        this.ed = ed;
    }

    private boolean checkChars(String pw) {
        for (int i = 0; i < pw.length(); i++) {
            int ascii = (int) pw.charAt(i);
            if (!((ascii >= 48 && ascii <= 57) || (ascii >= 65 && ascii <= 90)
                    || (ascii >= 97 && ascii <= 128))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkLength(String ps) {
        if (ps.length() == 0 || ps.length() > 20) {
            return false;
        }
        ps = ps.trim();

        if (ps.length() == 0 || ps.length() > 20) {
            return false;
        }
        return true;
    }
    
    private boolean checkEntry(String s) {
        return checkChars(s) && checkLength(s);
    }

    public boolean checkLoginEntry(String name, String password) {
        if (!(checkEntry(name) || checkEntry(password))) {
            return false;
        }
        
        Player p = new Player(name, password);
        
        try {
            Player o = pd.read(name);
            if (!p.equals(o)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkNameAvailability(String name) {
        if (!checkEntry(name)) {
            return false;
        }
        
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
        if (!(ps1.equals(ps2) || checkEntry(ps1) || checkEntry(ps2))) {
            return false;
        }
        return true;
    }

    public boolean createAccount(String name, String password) {
        Player p = new Player(name, password);
        try {
            pd.create(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void quit() {
        //Tallenna listoissa olevat tiedot tietokantoihin molemmissa Daoissa
    }
}
