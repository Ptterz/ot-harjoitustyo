
package appen.dao;

import appen.domain.Player;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilePlayerDao implements PlayerDao {
    public List<Player> players;
    
    public FilePlayerDao() throws SQLException {
        this.players = new ArrayList<>();
    }
    
    private void uploadNewPlayer(Player player) throws SQLException {
        //Lisää pelaaja tietokantaan
    }
    
    @Override
    public void createNewPlayer(String nickname, String password) {
        Player player = new Player(nickname, password);
        players.add(player);
        
        try {
            uploadNewPlayer(player);
        } catch (Exception e) {
            //Error!
        }
    }
    
    @Override
    public List<Player> listAllPlayers() {
        return players;
    }
    
}
