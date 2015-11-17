import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class BoardObject implements Dieable {
    protected ImageIcon imageIcon;
    protected Coordinates coordinates;
    protected boolean visible;
    protected boolean dying;

    public BoardObject() {
        this.visible = true;
        this.dying = false;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDying() {
        return dying;
    }

    public void die() {
        this.dying = true;
    }

    public void paint(Graphics graphics, ImageObserver observer) {
        if (this.isVisible()) {
            graphics.drawImage(imageIcon.getImage(), coordinates.getX(), coordinates.getY(), observer);
        }

        if (this.isDying()) {
            this.setVisible(false);
        }
    }
}
