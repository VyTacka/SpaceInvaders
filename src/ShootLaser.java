public class ShootLaser implements ShootStrategy {

    @Override
    public void shoot(BoardObject shooter, BoardObject shootable) {
        shootable.setCoordinates(
                new Coordinates(
                        shooter.getCoordinates().getX() + shooter.getImageIcon().getIconWidth() / 2,
                        shooter.getCoordinates().getY() + shooter.getImageIcon().getIconHeight()
                )
        );
        shootable.setVisible(true);
        Sound.play(this.getClass().getResource(Commons.LASER_SOUND));
    }
}
