import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

public class Invaders implements MovableInvader {

    List<Invader> invaders = new ArrayList<>();
    Player player = Player.getInstance();

    public void add(Invader invader) {
        invaders.add(invader);
    }

    public void remove(Invader invader) {
        invaders.remove(invader);
    }

    public int size() {
        return invaders.size();
    }

    public List<Invader> getInvaders() {
        return invaders;
    }

    public Invader getInvader(int index) {
        return invaders.get(index);
    }

    @Override
    public void move() {
        for (Invader invader : invaders) {
            if (invader.isVisible()) {
                if (invader.directionX == 1 && invader.getCoordinates().getX() < Commons.BOARD_WIDTH - Commons.BOARD_BORDER - invader.getImageIcon().getIconWidth()) {
                    invader.moveRight();
                } else if (invader.directionX == -1 && invader.getCoordinates().getX() > Commons.BOARD_BORDER) {
                    invader.moveLeft();
                } else {
                    moveDown();
                    return;
                }
            }
            invader.laser.move();
        }
    }

    @Override
    public void moveDown() {
        for (Invader invader : invaders) {
            invader.getCoordinates().moveDown(invader.getImageIcon().getIconHeight());
            invader.incrementRow();
            invader.directionX *= -1;
        }
    }

    @Override
    public void moveLeft() {
        for (Invader invader : invaders) {
            invader.moveLeft();
        }
    }

    @Override
    public void moveRight() {
        for (Invader invader : invaders) {
            invader.moveRight();
        }
    }

    @Override
    public void paint(Graphics graphics, ImageObserver observer) {
        for (Invader invader : invaders) {
            invader.paint(graphics, observer);
        }
    }

    public boolean isInvasion() {
        for (Invader invader : invaders) {
            if (invader.isVisible() && invader.getCoordinates().getY() > Commons.BOARD_HEIGHT - Commons.BOARD_BORDER - invader.getImageIcon().getIconHeight() - player.getImageIcon().getIconHeight()) {
                return true;
            }
        }
        return false;
    }
}
