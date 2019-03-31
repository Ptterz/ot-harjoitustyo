package appen.domain;

import appen.dao.FileExerciseDao;
import appen.dao.FilePlayerDao;
import appen.database.Database;

public class Management {

    private Calculator calc;
    private FilePlayerDao fpd;
    private FileExerciseDao fed;

    public Management(FilePlayerDao fpd, FileExerciseDao fed) {
        this.calc = new Calculator();
        this.fpd = fpd;
        this.fed = fed;
    }

    public boolean checkLoginEntry(String name, String password) {
        if (name.trim().length() == 0 || password.trim().length() == 0) {
            return false;
        }

        for (Player p : fpd.listAllPlayers()) {
            if (p.getNickname().equals(name) && p.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkNameAvailability(String name) {
        for (Player p : fpd.listAllPlayers()) {
            if (p.getNickname().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkPasswordLength(String ps1, String ps2) {
        if (ps1.length() == 0 || ps2.length() == 0 || ps1.length() > 20
                || ps2.length() > 20) {
            return false;
        }
        return true;
    }

    public boolean checkPasswordEntry(String ps1, String ps2) {
        if (!ps1.equals(ps2)) {
            return false;
        }
        if (!(checkPasswordChars(ps1) || checkPasswordChars(ps2))) {
            return false;
        }
        return true;
    }

    public boolean checkPasswordChars(String pw) {
        for (int i = 0; i < pw.length(); i++) {
            int ascii = (int) pw.charAt(i);
            if (!((ascii >= 48 && ascii <= 57) || (ascii >= 65 && ascii <= 90)
                    || (ascii >= 97 && ascii <= 128))) {
                return false;
            }
        }
        return true;
    }

    public boolean createAccount(String name, String password) {
        return fpd.createNewPlayer(name, password);
    }

    public void quit() {
        //Tallenna listoissa olevat tiedot tietokantoihin molemmissa Daoissa
        fpd.quit(); //true/false saatiinko tiedot tallennettua
    }
}
