public class ShootMissile implements ShootStrategy {

    @Override
    public void shoot(BoardObject shooter, BoardObject shootable) {
        if (shooter.isVisible() && !shootable.isVisible()) {
            shootable.setCoordinates(
                    new Coordinates(
                            shooter.getCoordinates().getX() + shooter.getImageIcon().getIconWidth() / 2,
                            shooter.getCoordinates().getY() - shootable.getImageIcon().getIconHeight(),
                            Commons.MISSILE_STEP_SIZE
                    )
            );
            shootable.setVisible(true);
            Sound.play(this.getClass().getResource(Commons.MISSILE_SOUND));
        }
    }
}
