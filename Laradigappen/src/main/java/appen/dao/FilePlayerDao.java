
package appen.dao;

import appen.domain.Player;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilePlayerDao implements PlayerDao {
    public List<Player> players;
    private Player newPlayer;
    
    public FilePlayerDao() throws SQLException {
        this.players = new ArrayList<>();
        
        try {
            //Lue tietokanta
        } catch (Exception e) {
            //Luo tietokanta, koska ei olemassa!
        }
    }
    
    private void uploadNewPlayer() throws SQLException {
        //lataa newPlayer tietokantaan
    }
    
    @Override
    public void createNewPlayer(String nickname, String password) {
        this.newPlayer = new Player(nickname, password);
        players.add(newPlayer);
        //Lisätään pelaaja listalle
    }
    
    @Override
    public List<Player> listAllPlayers() {
        return players;
    }
    
}
