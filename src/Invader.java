import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;

public abstract class Invader extends BoardObject {
    protected Laser laser;
    protected int points;
    protected int directionX;
    protected int row;
    private Random random;

    public Invader() {
        super();

        this.directionX = 1;
        this.laser = new Laser();
        this.random = new Random();
        this.row = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setDirectionX(int directionX) {
        this.directionX = directionX;
    }

    public int getRow() {
        return row;
    }

    public void incrementRow() {
        this.row++;
    }

    @Override
    public void die() {
        super.die();

        ImageIcon explosionImageIcon = new ImageIcon(this.getClass().getResource(Commons.EXPLOSION_SPRITE));
        this.coordinates = new Coordinates(
                this.coordinates.getX() + (this.imageIcon.getIconWidth() - explosionImageIcon.getIconWidth()) / 2,
                this.coordinates.getY() + (this.imageIcon.getIconHeight() - explosionImageIcon.getIconHeight()) / 2
        );
        this.imageIcon = explosionImageIcon;
        Sound.play(this.getClass().getResource(Commons.INVADER_EXPLOSION_SOUND));
    }

    @Override
    public void paint(Graphics graphics, ImageObserver observer) {
        super.paint(graphics, observer);

        if (this.laser.isVisible()) {
            this.laser.paint(graphics, observer);
        } else if (this.visible && this.random.nextInt(10000) < 10) {
            this.shoot();
        }
    }

    public void move() {
        if (directionX == 1 && coordinates.getX() < Commons.BOARD_WIDTH - Commons.BOARD_BORDER - this.imageIcon.getIconWidth()) {
            coordinates.moveRight();
        } else if (directionX == -1 && coordinates.getX() > Commons.BOARD_BORDER) {
            coordinates.moveLeft();
        }

        if (laser.isVisible()) {
            laser.move();
        }
    }

    public void shoot() {
        this.laser.setCoordinates(
                new Coordinates(
                        this.coordinates.getX() + this.imageIcon.getIconWidth() / 2,
                        this.coordinates.getY() + this.imageIcon.getIconHeight()
                )
        );
        this.laser.setVisible(true);
        Sound.play(this.getClass().getResource(Commons.LASER_SOUND));
    }

}
