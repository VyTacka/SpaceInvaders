import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.*;

public class Player extends BoardObject implements Shooter {
    private static Player ourInstance = new Player();
    private ShootStrategy shootStrategy;
    private java.util.List<Invader> invaders = new ArrayList<>();

    protected Missile missile;
    protected int lives;
    protected int score;
    protected int kills;
    protected int directionX;
    protected int blink;
    protected int blink_delay;
    protected boolean blinking;

    private Player() {
        super();
        this.imageIcon = new ImageIcon(this.getClass().getResource(Commons.PLAYER_SPRITE));
        this.lives = Commons.PLAYER_LIVES;
        this.score = 0;
        this.kills = 0;
        this.blink = Commons.PLAYER_BLINK;
        this.blink_delay = Commons.PLAYER_BLINK_DELAY;
        this.blinking = true;
        this.coordinates = new Coordinates(
                (Commons.BOARD_WIDTH + this.imageIcon.getIconWidth()) / 2,
                Commons.BOARD_HEIGHT - Commons.BOARD_BORDER - this.getImageIcon().getIconHeight(),
                Commons.PLAYER_STEP_SIZE
        );
        this.missile = new Missile();
        this.shootStrategy = new ShootMissile();
    }

    public static Player getInstance() {
        return ourInstance;
    }

    public Missile getMissile() {
        return missile;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {

        this.score += score;
    }

    public int getKills() {
        return kills;
    }

    public void addKill() {
        this.kills++;
    }

    public boolean isBlinking() {
        return blinking;
    }

    public void setBlinking(boolean blinking) {
        this.blinking = blinking;
    }

    // Add observer
    public void addInvader(Invader invader) {
        invaders.add(invader);
    }

    public void addAllInvaders(List<Invader> invaders) {
        this.invaders.addAll(invaders);
    }

    // Remove observer
    public void removeInvader(Invader invader) {
        invaders.remove(invader);
    }

    // Notify observers
    public void notifyInvaders() {
        for (Invader invader : invaders) {
            invader.handleMissile();
        }
    }

    public void handleKill(Invader invader) {
        this.missile.die();
        this.addKill();
        this.addScore(invader.getPoints());

        // If first kill
        if (this.kills == 1) {
            Sound.play(this.getClass().getResource(Commons.FIRSTBLOOD_SOUND));
        } else {
            // If missile is above invader middle
            if (this.missile.getCoordinates().getY() <= invader.coordinates.getY() + invader.imageIcon.getIconHeight() / 2) {
                Sound.play(this.getClass().getResource(Commons.HEADSHOT_SOUND));
            }
        }
    }

    @Override
    public void paint(Graphics graphics, ImageObserver observer) {
        if (this.isVisible()) {
            graphics.drawImage(this.imageIcon.getImage(), this.coordinates.getX(), this.coordinates.getY(), observer);
        }

        if (this.missile.isVisible()) {
            this.missile.paint(graphics, observer);
            this.notifyInvaders();
        }

        if (this.isDying()) {
            if (--this.lives == 0) {
                this.visible = false;
            } else {
                this.dying = false;
                this.blinking = true;
            }
        }

        if (this.isBlinking()) {
            this.blink();
        }
    }

    @Override
    public void die() {
        super.die();

        if (this.lives == 1) {
            ImageIcon explosionImageIcon = new ImageIcon(this.getClass().getResource(Commons.EXPLOSION_SPRITE));
            this.coordinates = new Coordinates(
                    this.coordinates.getX() + (this.imageIcon.getIconWidth() - explosionImageIcon.getIconWidth()) / 2,
                    this.coordinates.getY() + (this.imageIcon.getIconHeight() - explosionImageIcon.getIconHeight()) / 2,
                    this.coordinates.getStep()
            );
            this.imageIcon = explosionImageIcon;
            Sound.play(this.getClass().getResource(Commons.PLAYER_EXPLOSION_SOUND));
        }
    }

    public void move() {
        if (directionX == 1 && coordinates.getX() < Commons.BOARD_WIDTH - Commons.BOARD_BORDER - this.imageIcon.getIconWidth()) {
            coordinates.moveRight();
        } else if (directionX == -1 && coordinates.getX() > Commons.BOARD_BORDER) {
            coordinates.moveLeft();
        }

        if (missile.isVisible()) {
            missile.move();
        }
    }

    public void shoot() {
        shootStrategy.shoot(this, missile);
    }


    public void blink() {

        if (blink > 0) {
            if (blink % 2 == 0) {
                imageIcon = new ImageIcon(this.getClass().getResource(Commons.PLAYER_BLINK_SPRITE));
            } else {
                imageIcon = new ImageIcon(this.getClass().getResource(Commons.PLAYER_SPRITE));
            }

            if (blink_delay-- == 0) {
                blink--;
                blink_delay = Commons.PLAYER_BLINK_DELAY;
            }
        } else {
            // Reset blinking
            blinking = false;
            blink = Commons.PLAYER_BLINK;
            blink_delay = Commons.PLAYER_BLINK_DELAY;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT && coordinates.getX() < Commons.BOARD_WIDTH - Commons.BOARD_BORDER - this.imageIcon.getIconWidth()) {
            directionX = 1;
        }

        if (key == KeyEvent.VK_LEFT && coordinates.getX() > Commons.BOARD_BORDER) {
            directionX = -1;
        }

        if (key == KeyEvent.VK_SPACE) {
            this.shoot();
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            directionX = 0;
        }
    }
}
