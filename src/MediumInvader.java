import javax.swing.*;

public class MediumInvader extends Invader {

    public MediumInvader() {
        super(InvaderType.MEDIUM);

        this.imageIcon = new ImageIcon(this.getClass().getResource(Commons.MEDIUM_INVADER_SPRITE));
        this.points = Commons.MEDIUM_INVADER_POINTS;
        this.coordinates = new Coordinates();
    }

    public MediumInvader(Coordinates coordinates) {
        super(InvaderType.MEDIUM);

        this.imageIcon = new ImageIcon(this.getClass().getResource(Commons.MEDIUM_INVADER_SPRITE));
        this.points = Commons.MEDIUM_INVADER_POINTS;
        this.coordinates = coordinates;
    }

    @Override
    protected void construct() {
        System.out.println("Creating medium invader");
    }
}