
package appen.dao;

import appen.domain.Exercise;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileExerciseDao implements ExerciseDao {
    public List<Exercise> exercises;
    
    public FileExerciseDao() throws SQLException {       
        this.exercises = new ArrayList<>();
        
        try {
            //Lue tietokanta
        } catch (Exception e) {
            //Luo tietokanta, koska ei olemassa!
        }
    }
    
    private void uploadNewExercise(Exercise exercise) {
        //lataa tietokantaan
    }
    
    @Override
    public void createNewExercise(String question, String answer) {
        Exercise exercise = new Exercise(question, answer);
        exercises.add(exercise);
        
        try {
            uploadNewExercise(exercise);
        } catch (Exception e) {
            //Error!
        }
        
    }
    
    @Override
    public List<Exercise> listAllExercises() {
        return this.exercises;
    }
}
