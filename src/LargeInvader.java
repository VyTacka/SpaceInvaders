import javax.swing.*;

public class LargeInvader extends Invader {

    public LargeInvader() {
        super();

        this.imageIcon = new ImageIcon(this.getClass().getResource(Commons.LARGE_INVADER_SPRITE));
        this.points = Commons.LARGE_INVADER_POINTS;
        this.coordinates = new Coordinates();
    }

    @Override
    protected void construct() {
        LoggerChain.getInstance().getChain().log(Logger.DEBUG, "creating LargeInvader");
    }
}