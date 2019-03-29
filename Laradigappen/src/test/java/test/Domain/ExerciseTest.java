
package test.Domain;

import appen.domain.Exercise;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExerciseTest {
    
    Exercise exercise;
    
    @Before
    public void setUp() {
        exercise = new Exercise("2+2","4");
    }
    
    @Test
    public void exerciseExist() {
        assertTrue(exercise!=null); 
    }
    
    @Test
    public void nicknameCorrect() {
        assertEquals(exercise.getQuestion(),"2+2");
    }
    
    @Test
    public void passwordCorrect() {
        assertEquals(exercise.getAnswer(),"4");
    }
}
