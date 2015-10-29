import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board extends JPanel implements Runnable {
    private Player player = Player.getInstance();
    private Invaders invaders;

    private Thread animationThread;
    private Dimension dimensions;
    private boolean isRunning = false;

    public Board() {
        addKeyListener(new ControlsAdapter());
        invaders = new Invaders();

        dimensions = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setBackground(Color.black);
        setFocusable(true);
        setDoubleBuffered(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, dimensions.width, dimensions.height);

        printPlayerStatus(graphics);

        if (isRunning) {
            // if player is dead
            if (!player.isVisible()) {
                isRunning = false;
                printMessage(Commons.GAME_OVER_TEXT, graphics);
                Sound.play(this.getClass().getResource(Commons.GAME_OVER_SOUND));
                return;
            }

            // if player killed all invaders
            if (player.getKills() == invaders.size()) {
                isRunning = false;
                printMessage(Commons.VICTORY_TEXT, graphics);
                Sound.play(this.getClass().getResource(Commons.FLAWLESS_VICTORY_SOUND));
                return;
            }

            // if invasion
            if (invaders.isInvasion()) {
                isRunning = false;
                printMessage(Commons.INVASION_TEXT, graphics);
                Sound.play(this.getClass().getResource(Commons.GAME_OVER_SOUND));
            }

            // Green ground line
            graphics.setColor(Color.green);
            graphics.drawLine(0, Commons.BOARD_HEIGHT - Commons.BOARD_BORDER, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT - Commons.BOARD_BORDER);

            // Moves the player
            player.move();
            player.paint(graphics, this);

            // Move invaders
            invaders.move();
            invaders.paint(graphics, this);

            // Handle player missile killing invaders
            handleMissile();
        } else {
            printMessage(Commons.START_GAME_TEXT, graphics);
        }

        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }

    @Override
    public void run() {
        while (isRunning) {
            long time = System.currentTimeMillis();
            repaint();
            time = (1000 / Commons.FPS_CAP) - (System.currentTimeMillis() - time);

            if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch (Exception ignored) {
                }
            }
        }
    }

    public void initialize() {
        int row = 0;
        for (int i = 0; i < Commons.SMALL_INVADER_ROWS; i++) {
            for (int j = 0; j < Commons.SMALL_INVADER_COLUMNS; j++) {
                Invader invader = InvaderFactory.createInvader(InvaderType.SMALL);
                invader.setCoordinates(
                        new Coordinates(
                                (Commons.SMALL_INVADER_PADDING + invader.getImageIcon().getIconWidth()) * j + Commons.SMALL_INVADER_PADDING / 4 + Commons.BOARD_BORDER,
                                (Commons.SMALL_INVADER_PADDING + invader.getImageIcon().getIconHeight()) * row + Commons.BOARD_BORDER * 2
                        )
                );
                invaders.add(invader);
            }
            row++;
        }
        for (int i = 0; i < Commons.MEDIUM_INVADER_ROWS; i++) {
            for (int j = 0; j < Commons.MEDIUM_INVADER_COLUMNS; j++) {
                Invader invader = InvaderFactory.createInvader(InvaderType.MEDIUM);
                invader.setCoordinates(
                        new Coordinates(
                                (Commons.MEDIUM_INVADER_PADDING + invader.getImageIcon().getIconWidth()) * j + Commons.BOARD_BORDER,
                                (Commons.MEDIUM_INVADER_PADDING + invader.getImageIcon().getIconHeight()) * row + Commons.BOARD_BORDER * 2
                        )
                );
                invaders.add(invader);
            }
            row++;
        }
        for (int i = 0; i < Commons.LARGE_INVADER_ROWS; i++) {
            for (int j = 0; j < Commons.LARGE_INVADER_COLUMNS; j++) {
                Invader invader = InvaderFactory.createInvader(InvaderType.LARGE);
                invader.setCoordinates(
                        new Coordinates(
                                (Commons.LARGE_INVADER_PADDING + invader.getImageIcon().getIconWidth()) * j + Commons.BOARD_BORDER,
                                (Commons.LARGE_INVADER_PADDING + invader.getImageIcon().getIconHeight()) * row + Commons.BOARD_BORDER * 2
                        )
                );
                invaders.add(invader);
            }
            row++;
        }

        if (!isRunning || animationThread == null) {
            animationThread = new Thread(this);
            animationThread.start();
            Sound.play(this.getClass().getResource(Commons.PLAY_SOUND));
        }
    }

    public void printMessage(String message, Graphics graphics) {
        Font font = new Font("Consolas", Font.BOLD, 14);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        graphics.setColor(Color.darkGray);
        graphics.fillRect(Commons.BOARD_BORDER, Commons.BOARD_HEIGHT / 2 - fontMetrics.getHeight() * 2, Commons.BOARD_WIDTH - Commons.BOARD_BORDER * 2, fontMetrics.getHeight() * 4);
        graphics.setColor(Color.white);
        graphics.drawRect(Commons.BOARD_BORDER, Commons.BOARD_HEIGHT / 2 - fontMetrics.getHeight() * 2, Commons.BOARD_WIDTH - Commons.BOARD_BORDER * 2, fontMetrics.getHeight() * 4);

        graphics.setColor(Color.white);
        graphics.setFont(font);
        graphics.drawString(message, (Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, Commons.BOARD_HEIGHT / 2 + 5);
    }

    public void printPlayerStatus(Graphics graphics) {
        Font font = new Font("Consolas", Font.BOLD, 14);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        graphics.setColor(Color.white);
        graphics.setFont(font);

        // Score
        graphics.drawString(Commons.SCORE_TEXT, Commons.BOARD_BORDER, Commons.BOARD_BORDER);
        graphics.setColor(Color.green);
        graphics.drawString(String.format("%06d", player.getScore()), fontMetrics.stringWidth(Commons.SCORE_TEXT) + Commons.BOARD_BORDER, Commons.BOARD_BORDER);

        // Lives
        if (player.getLives() > 0) {
            graphics.setColor(Color.white);
            graphics.drawString(Commons.LIVES_TEXT, Commons.BOARD_WIDTH - Commons.BOARD_BORDER - (Commons.PLAYER_LIVES - 1) * (player.getImageIcon().getIconWidth() + Commons.PLAYER_PADDING) - fontMetrics.stringWidth(Commons.LIVES_TEXT), Commons.BOARD_BORDER);

            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(Commons.PLAYER_SPRITE));
            for (int i = 0; i < player.getLives() - 1; i++) {
                graphics.drawImage(imageIcon.getImage(), Commons.BOARD_WIDTH - Commons.BOARD_BORDER - (i + 1) * (imageIcon.getIconWidth() + Commons.PLAYER_PADDING), Commons.BOARD_BORDER - imageIcon.getIconHeight(), this);
            }
        }
    }

    public void handleMissile() {
        if (player.getMissile().isVisible()) {
            invaders.handleMissiles();
        }
    }

    public class ControlsAdapter extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            if (isRunning) {
                player.keyPressed(e);
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                isRunning = true;
                initialize();
            }
        }
    }
}
