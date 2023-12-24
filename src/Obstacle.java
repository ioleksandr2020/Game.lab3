public class Obstacle {
    private int x;
    private int y;
    private char type;

    public Obstacle(int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getType() {
        return type;
    }
}

