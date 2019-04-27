package appen.domain;

public class Performance {

    private int id;
    private Player player;
    private Exercise exercise;
    private int score;
    
    /**
     * A constructor to create a new performance variable.
     * 
     * @param p Player whose performance is in question
     * @param e Exercise related to the record
     * @param count Tries spent
     * @param time Time spent in milliseconds
     * @since 1.1
     */
    public Performance(Player p, Exercise e, int count, long time) {
        this.id = -1;
        this.player = p;
        this.exercise = e;
        int secs = (int) (time / 1000);
        this.score = Math.abs(1000000 - 10000 * count - 1000 * secs);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public int getScore() {
        return score;
    }
}
