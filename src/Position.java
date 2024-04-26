public class Position {
    //Position unique x and y variables
    private int x;
    private int y;

    //Position constructor
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Accessor Methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //Mutator methods
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(Position position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    //toString method to return coordinates
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}