package appen.domain;

public class Performance {

    private int id;
    private Player p;
    private Exercise e;
    private int count;

    public Performance(Player p, Exercise e, int count) {
        this.id = -1;
        this.p = p;
        this.e = e;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return p;
    }

    public Exercise getExercise() {
        return e;
    }

    public int getCount() {
        return count;
    }
}
