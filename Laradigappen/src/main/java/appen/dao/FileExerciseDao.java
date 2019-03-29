
package appen.dao;

import appen.domain.Exercise;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileExerciseDao implements ExerciseDao {
    public List<Exercise> exercises;
    
    public FileExerciseDao() throws SQLException {
        
    }
}
