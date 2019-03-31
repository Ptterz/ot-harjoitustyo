
package appen.dao;

import appen.database.Database;
import appen.domain.Exercise;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileExerciseDao implements ExerciseDao {
    public List<Exercise> exercises;
    private Database db;
    
    public FileExerciseDao(Database db) throws SQLException {       
        this.exercises = new ArrayList<>();
        this.db = db;
    }
    
    private void saveExercises(Exercise exercise) {
        //lataa tietokantaan
    }
    
    private int generateId() {
        return exercises.size() + 1;
    }
    
    @Override
    public void createNewExercise(String question, String answer) {
        Exercise exercise = new Exercise(question, answer);
        exercises.add(exercise);
    }
    
    @Override
    public List<Exercise> listAllExercises() {
        return this.exercises;
    }
}
