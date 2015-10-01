import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Missile extends BoardObject {

    public Missile() {
        super();

        this.imageIcon = new ImageIcon(this.getClass().getResource(Commons.MISSILE_SPRITE));
        this.coordinates = new Coordinates();
        this.visible = false;
    }

    public void move() {
        if (this.visible) {
            coordinates.moveUp();
        }
    }

    @Override
    public void paint(Graphics graphics, ImageObserver observer) {
        super.paint(graphics, observer);

        if (this.visible && this.coordinates.getY() == -this.imageIcon.getIconHeight()) {
            this.visible = false;
        }
    }

    @Override
    public void die() {
        this.visible = false;
    }
}
