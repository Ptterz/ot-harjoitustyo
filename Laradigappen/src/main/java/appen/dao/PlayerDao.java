
package appen.dao;

import appen.domain.Player;
import java.util.List;

public interface PlayerDao {
    
    boolean createNewPlayer(String nickname, String password) throws Exception;
    
    List<Player> listAllPlayers() throws Exception;
    
    boolean quit() throws Exception;
    
}
