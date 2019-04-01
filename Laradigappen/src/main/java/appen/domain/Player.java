
package appen.domain;

import java.util.Objects;

public class Player{
    private String nickname;
    private String password;

    public Player(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        
        Player other = (Player) obj;
        return nickname.equals(other.nickname) && password.equals(other.password);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.nickname);
        hash = 67 * hash + Objects.hashCode(this.password);
        return hash;
    }
    
}
