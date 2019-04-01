
package test.Domain;

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
        player = new Player("Testington","password");
    }
    
    @Test
    public void playerExist() {
        assertTrue(player!=null); 
    }
    
    @Test
    public void nicknameCorrect() {
        assertEquals(player.getNickname(),"Testington");
    }
    
    @Test
    public void passwordCorrect() {
        assertEquals(player.getPassword(),"password");
    }
    
    @Test
    public void changedCorrect() {
        assertFalse(player.isChanged());
    }
}
