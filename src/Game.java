import javax.swing.*;

public class Game extends JFrame {

    public Game() {
        add(new Board());

        setTitle(Commons.GAME_TITLE_TEXT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new Game();
    }
}