import javax.swing.*;

public class SmallInvader extends Invader {

    public SmallInvader() {
        super();

        this.imageIcon = new ImageIcon(this.getClass().getResource(Commons.SMALL_INVADER_SPRITE));
        this.points = Commons.SMALL_INVADER_POINTS;
        this.coordinates = new Coordinates();
    }

    @Override
    protected void construct() {
        LoggerChain.getInstance().getChain().log(Logger.DEBUG, "creating SmallInvader");
    }
}