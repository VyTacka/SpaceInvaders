import javax.swing.*;

public class MediumInvader extends Invader {

    public MediumInvader() {
        super();

        this.imageIcon = new ImageIcon(this.getClass().getResource(Commons.MEDIUM_INVADER_SPRITE));
        this.points = Commons.MEDIUM_INVADER_POINTS;
        this.coordinates = new Coordinates();
    }

    @Override
    protected void construct() {
        LoggerChain.getInstance().getChain().log(Logger.DEBUG, "creating MediumInvader");
    }
}