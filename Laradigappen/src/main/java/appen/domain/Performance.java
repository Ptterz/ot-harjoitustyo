package appen.domain;

public class Performance {

    private int id;
    private Player player;
    private Exercise exercise;
    private int score;

    public Performance(Player p, Exercise e, int count, long time) {
        this.id = -1;
        this.player = p;
        this.exercise = e;
        int secs = (int) (time / 1000);
        this.score = Math.abs(1000000 - 10000*count - 10000*secs);
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
