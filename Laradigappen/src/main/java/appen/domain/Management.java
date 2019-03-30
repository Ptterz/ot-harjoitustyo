
package appen.domain;

import appen.dao.FileExerciseDao;
import appen.dao.FilePlayerDao;

public class Management {
    private Calculator calc;
    private FilePlayerDao fpd;
    private FileExerciseDao fed;

    public Management() {
        this.calc = new Calculator();
    }

    public boolean checkLoginEntry(String name, String password) {
        
        if (name.trim().length() == 0 || password.trim().length() == 0) {
            return false;
        }

        //hae tiedoista pelaajan tiedot, tee niistä olio
        //tsekkaa täsmäävätkö olion nimi ja salasana annettuihin
        return true;
    }

    public boolean checkNameAvailability(String name) {

        //Tarkista onko nimi käytössä
        return true;
    }

    public boolean checkPasswordEntry(String ps1, String ps2) {

        if (!ps1.equals(ps2)) {
            return false;
        }

        return true;
    }
    
    public void createAccount(String name, String password) {
        
    }
    
    public void quit() {
        //Tallenna listoissa olevat tiedot tietokantoihin molemmissa Daoissa
    }
}
