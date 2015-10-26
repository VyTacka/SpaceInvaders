import javax.swing.*;

public class LargeInvader extends Invader {

    public LargeInvader() {
        super(InvaderType.LARGE);

        this.imageIcon = new ImageIcon(this.getClass().getResource(Commons.LARGE_INVADER_SPRITE));
        this.points = Commons.LARGE_INVADER_POINTS;
        this.coordinates = new Coordinates();
    }

    public LargeInvader(Coordinates coordinates) {
        super(InvaderType.LARGE);

        this.imageIcon = new ImageIcon(this.getClass().getResource(Commons.LARGE_INVADER_SPRITE));
        this.points = Commons.LARGE_INVADER_POINTS;
        this.coordinates = coordinates;
    }

    @Override
    protected void construct() {
        System.out.println("Creating large invader");
    }
}