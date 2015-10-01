import javax.swing.*;

public class LargeInvader extends Invader {

    public LargeInvader() {
        super();

        this.imageIcon = new ImageIcon(this.getClass().getResource(Commons.LARGE_INVADER_SPRITE));
        this.points = Commons.LARGE_INVADER_POINTS;
        this.coordinates = new Coordinates();
    }

    public LargeInvader(Coordinates coordinates) {
        super();

        this.imageIcon = new ImageIcon(this.getClass().getResource(Commons.LARGE_INVADER_SPRITE));
        this.points = Commons.LARGE_INVADER_POINTS;
        this.coordinates = coordinates;
    }
}