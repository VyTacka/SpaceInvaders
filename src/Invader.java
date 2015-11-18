import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Random;

public abstract class Invader extends BoardObject implements MovableInvader, Shooter {
    private InvaderType model = null;
    private Random random;
    private ShootStrategy shootStrategy;
    private Player player = Player.getInstance(); // Subject

    protected Laser laser;
    protected int points;
    protected int directionX;
    protected int directionY;
    protected int row;


    public Invader(InvaderType model) {
        super();
        this.model = model;
        this.directionX = 1;
        this.directionY = 0;
        this.laser = new Laser();
        this.random = new Random();
        this.row = 0;
        this.shootStrategy = new ShootLaser();
        this.construct();
    }

    // Do subclass level processing in this method
    protected abstract void construct();

    // Called by Subject
    public boolean handleMissile() {
        if (this.isVisible() && player.getMissile().isVisible()) {
            if (player.getMissile().getCoordinates().getX() >= this.getCoordinates().getX() &&
                    player.getMissile().getCoordinates().getX() <= this.getCoordinates().getX() + this.getImageIcon().getIconWidth() &&
                    player.getMissile().getCoordinates().getY() >= this.getCoordinates().getY() &&
                    player.getMissile().getCoordinates().getY() <= this.getCoordinates().getY() + this.getImageIcon().getIconHeight()) {
                this.die();
                player.handleKill(this);

                return true;
            }
        }

        return false;
    }

    public InvaderType getModel() {
        return model;
    }

    public void setModel(InvaderType model) {
        this.model = model;
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
            moveRight();
        } else if (directionX == -1 && coordinates.getX() > Commons.BOARD_BORDER) {
            moveLeft();
        } else {
            moveDown();
        }

        if (laser.isVisible()) {
            laser.move();
        }
    }

    public void moveLeft() {
        coordinates.moveLeft();
    }

    public void moveRight() {
        coordinates.moveRight();
    }

    public void moveDown() {
        coordinates.moveDown(this.imageIcon.getIconHeight());
        incrementRow();
        directionX *= -1;
//        coordinates.setStep((int) (Math.floor(row / Commons.INVADER_GET_FASTER_AFTER_ROWS) + 1));
    }

    public void shoot() {
        shootStrategy.shoot(this, this.laser);
    }
}
