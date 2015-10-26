import java.awt.*;
import java.awt.image.ImageObserver;

public interface MovableInvader {
    void move();

    void moveDown();

    void moveLeft();

    void moveRight();

    void paint(Graphics graphics, ImageObserver observer);
}
