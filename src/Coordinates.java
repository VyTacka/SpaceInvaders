public class Coordinates {
    private int x;
    private int y;
    private int step;

    public Coordinates() {
        this(0, 0, 1);
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
        this.step = 1;
    }

    public Coordinates(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step = step;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void moveUp() {
        this.y -= this.step;
    }

    public void moveUp(int step) {
        this.y -= step;
    }

    public void moveRight() {
        this.x += this.step;
    }

    public void moveRight(int step) {
        this.x += step;
    }

    public void moveDown() {
        this.y += this.step;
    }

    public void moveDown(int step) {
        this.y += step;
    }

    public void moveLeft() {
        this.x -= this.step;
    }

    public void moveLeft(int step) {
        this.x -= step;
    }

}
