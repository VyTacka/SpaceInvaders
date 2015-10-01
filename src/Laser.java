import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Laser extends BoardObject {
    private Player player = Player.getInstance();

    public Laser() {
        super();

        this.imageIcon = new ImageIcon(this.getClass().getResource(Commons.LASER_SPRITE));
        this.coordinates = new Coordinates();
        this.visible = false;
    }

    public void move() {
        if (this.visible) {
            coordinates.moveDown();
        }
    }

    @Override
    public void paint(Graphics graphics, ImageObserver observer) {
        super.paint(graphics, observer);

        if (this.isVisible() && this.player.isVisible() && !this.player.isBlinking() &&
                this.coordinates.getX() >= this.player.getCoordinates().getX() && this.coordinates.getX() <= this.player.getCoordinates().getX() + this.player.getImageIcon().getIconWidth() &&
                this.coordinates.getY() >= this.player.getCoordinates().getY() && this.coordinates.getY() <= this.player.getCoordinates().getY() + this.player.getImageIcon().getIconHeight()) {
            this.player.die();
            this.visible = false;
        }

        if (this.visible && this.coordinates.getY() == Commons.BOARD_HEIGHT - Commons.BOARD_BORDER - this.imageIcon.getIconHeight()) {
            this.visible = false;
        }
    }
}
