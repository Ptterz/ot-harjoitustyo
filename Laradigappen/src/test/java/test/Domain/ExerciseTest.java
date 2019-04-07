
package test.Domain;

import appen.domain.Exercise;
import appen.domain.Player;
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
        exercise = new Exercise("2+2","4", 1);
    }
    
    @Test
    public void exerciseExist() {
        assertTrue(exercise!=null); 
    }
    
    @Test
    public void getQuestion() {
        assertEquals("2+2",exercise.getQuestion());
    }
    
    @Test
    public void setQuestion() {
        exercise.setQuestion("1+1");
        assertEquals("1+1",exercise.getQuestion());
    }
    
    @Test
    public void getPassword() {
        assertEquals("4",exercise.getAnswer());
    }
    
    @Test
    public void setAnswer() {
        exercise.setAnswer("2");
        assertEquals("2", exercise.getAnswer());
    }
    
    @Test
    public void getLevel() {
        assertEquals(1, exercise.getLevel());
    }
    
    @Test
    public void setLevel() {
        exercise.setLevel(0);
        assertEquals(0, exercise.getLevel());
    }
    
    @Test
    public void equalWhenSameQuestion() {
        Exercise e1 = new Exercise("tester", "1234");
        Exercise e2 = new Exercise("tester", "0000");
        assertTrue(e1.equals(e2));
    }
    
    @Test
    public void equalWhenSameQuestionAnswer() {
        Exercise e1 = new Exercise("tester", "1234");
        Exercise e2 = new Exercise("tester", "1234");
        assertTrue(e1.equals(e2));
    }
    
    @Test
    public void notEqualWhenDifferentQuestionAnswer() {
        Exercise e1 = new Exercise("tester", "1234");
        Exercise e2 = new Exercise("Tester", "0000");
        assertFalse(e1.equals(e2));
    }
    
    @Test
    public void notEqualWhenSameAnswer() {
        Exercise e1 = new Exercise("tester", "1234");
        Exercise e2 = new Exercise("Tester", "1234");
        assertFalse(e1.equals(e2));
    }
    
    @Test
    public void equalWhenSamePlayer() {
        Exercise e1 = new Exercise("tester", "1234");
        assertTrue(e1.equals(e1));
    }
    
    @Test
    public void notEqualWhenNull() {
        Exercise e1 = new Exercise("tester", "1234");
        assertFalse(e1.equals(null));
    }
    
    @Test
    public void notEqualWhenDifferentClass() {
        Player p1 = new Player("tester", "1234");
        Exercise e2 = new Exercise("tester", "0000");
        assertFalse(e2.equals(p1));
    }
    
    @Test
    public void equalHashWhenSameExercise() {
        Exercise e1 = new Exercise("tester", "1234");
        assertTrue(e1.hashCode() == e1.hashCode());
    }
    
    @Test
    public void notEqualHashWhenDifferentExercise() {
        Exercise e1 = new Exercise("tester", "1234");
        Exercise e2 = new Exercise("Tester", "1234");
        assertFalse(e1.hashCode() == e2.hashCode());
    }
}
