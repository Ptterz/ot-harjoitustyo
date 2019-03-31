
package appen.domain;

import java.util.Objects;

public class Player{
    private String nickname;
    private String password;
    private boolean changed;

    public Player(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
        this.changed = false;
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

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        
        Player other = (Player) obj;
        return nickname.equals(other.nickname);
    }
    
}
