
package appen.dao;

import appen.domain.Exercise;
import java.util.List;

public interface ExerciseDao {
    
    void createNewExercise(String question, String answer) throws Exception;
    
    List<Exercise> listAllExercises() throws Exception;
}
