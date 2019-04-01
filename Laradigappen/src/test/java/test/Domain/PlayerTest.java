
package test.Domain;

import appen.domain.Exercise;
import appen.domain.Player;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PlayerTest {
    
    Player player;
    
    @Before
    public void setUp() {
        player = new Player("Test","1234");
    }
    
    @Test
    public void playerExist() {
        assertTrue(player!=null); 
    }
    
    @Test
    public void getNickname() {
        assertEquals("Test",player.getNickname());
    }
    
    @Test
    public void getPassword() {
        assertEquals("1234",player.getPassword());
    }
    
    @Test
    public void setPassword() {
        player.setPassword("0000");
        assertEquals("0000",player.getPassword());
    }
    
    @Test
    public void equalWhenSameNickname() {
        Player p1 = new Player("tester", "1234");
        Player p2 = new Player("tester", "0000");
        assertTrue(p1.equals(p2));
    }
    
    @Test
    public void equalWhenSameNicknamePassword() {
        Player p1 = new Player("tester", "1234");
        Player p2 = new Player("tester", "1234");
        assertTrue(p1.equals(p2));
    }
    
    @Test
    public void notEqualWhenDifferentNicknamePassword() {
        Player p1 = new Player("tester", "1234");
        Player p2 = new Player("Tester", "0000");
        assertFalse(p1.equals(p2));
    }
    
    @Test
    public void notEqualWhenSamePassword() {
        Player p1 = new Player("tester", "1234");
        Player p2 = new Player("Tester", "1234");
        assertFalse(p1.equals(p2));
    }
    
    @Test
    public void equalWhenSamePlayer() {
        Player p1 = new Player("tester", "1234");
        assertTrue(p1.equals(p1));
    }
    
    @Test
    public void notEqualWhenNull() {
        Player p1 = new Player("tester", "1234");
        assertFalse(p1.equals(null));
    }
    
    @Test
    public void notEqualWhenDifferentClass() {
        Player p1 = new Player("tester", "1234");
        Exercise e2 = new Exercise("tester", "0000");
        assertFalse(p1.equals(e2));
    }
    
    @Test
    public void equalHashWhenSamePlayer() {
        Player p1 = new Player("tester", "1234");
        assertTrue(p1.hashCode() == p1.hashCode());
    }
    
    @Test
    public void notEqualHashWhenDifferentPlayer() {
        Player p1 = new Player("tester", "1234");
        Player p2 = new Player("Tester", "1234");
        assertFalse(p1.hashCode() == p2.hashCode());
    }
}
