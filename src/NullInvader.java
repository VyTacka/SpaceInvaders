import javax.swing.*;

public class NullInvader extends Invader {

    public NullInvader() {
        this.visible = false;
        this.imageIcon = new ImageIcon();
        this.points = 0;
        this.coordinates = new Coordinates();
    }

    @Override
    protected void construct() {
        LoggerChain.getInstance().getChain().log(Logger.DEBUG, "creating NullInvader");
    }
}
