import javax.swing.*;

public class SmallInvader extends Invader {

    public SmallInvader() {
        super(InvaderType.SMALL);

        this.imageIcon = new ImageIcon(this.getClass().getResource(Commons.SMALL_INVADER_SPRITE));
        this.points = Commons.SMALL_INVADER_POINTS;
        this.coordinates = new Coordinates();
    }

    public SmallInvader(Coordinates coordinates) {
        super(InvaderType.SMALL);

        this.imageIcon = new ImageIcon(this.getClass().getResource(Commons.SMALL_INVADER_SPRITE));
        this.points = Commons.SMALL_INVADER_POINTS;
        this.coordinates = coordinates;
    }

    @Override
    protected void construct() {
        System.out.println("Creating small invader");
    }
}