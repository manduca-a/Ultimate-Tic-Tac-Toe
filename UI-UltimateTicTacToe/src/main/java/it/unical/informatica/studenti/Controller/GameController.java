package it.unical.informatica.studenti.Controller;


import it.unical.informatica.studenti.Settings;
import it.unical.informatica.studenti.Model.InfoGame;
import it.unical.informatica.studenti.View.GameStartView;
import it.unical.informatica.studenti.View.GameView;
import it.unical.informatica.studenti.WorldGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GameController extends KeyAdapter implements ActionListener, WinnerListener {

    private final JFrame jFrame;
    private final GameView gameView;
    private final WorldGame worldGame = WorldGame.getInstance();
    private int value = 1; // X = 1, O = -1
    public GameController(JFrame frame, GameView view) {
        this.jFrame = frame;
        this.gameView = view;
        worldGame.addWinnerListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton o = (JButton)e.getSource();
        String[] indexes = o.getName().split("\\s+");
        int indBigBoard = Integer.parseInt(indexes[0]);
        int indSmallBoard = Integer.parseInt(indexes[1]);
        int rowSmallBoard = indSmallBoard / 3;
        int colSmallBoard = indSmallBoard % 3;

        if (worldGame.getBigBoard().UpdateBoardStatus(rowSmallBoard, colSmallBoard, indBigBoard, value)) {
            if (value == 1) gameView.setIconX(o); else if (value == -1) gameView.setIconO(o);
            value *= -1;
        }

        List<JPanel> jpanels = GameView.getjPanels();
        for(int i = 0; i < 9; i++) {

            // Sets the border green if it is the next board or if any board can be played when the game has no winner
            if ((worldGame.getBigBoard().getBigBoardWinner() == InfoGame.Winner.NOWINNER
                    && (worldGame.getBigBoard().getNextBoard() == i || worldGame.getBigBoard().getNextBoard() == -1))) {
                jpanels.get(i).setBorder(BorderFactory.createLineBorder(Settings.State.CURRENT_PLAYING.getColor(), 5));
            } else {
                jpanels.get(i).setBorder(BorderFactory.createLineBorder(Settings.State.BIG_LINES_COLOR.getColor(), 5));
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            GameStartView.launch(jFrame, gameView);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void onNewWinner(InfoGame.Winner winner, int id) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,1));
        JButton button = new JButton();
        button.setEnabled(false);

        ImageIcon icon = switch (winner) {
            case CROSS -> new ImageIcon(
                    gameView.getIconX().getImage().getScaledInstance(
                    gameView.getIconX().getIconWidth() * 2, gameView.getIconX().getIconHeight() * 2, Image.SCALE_DEFAULT));
            case CIRCLE -> new ImageIcon(
                    gameView.getIconO().getImage().getScaledInstance(
                    gameView.getIconO().getIconWidth() * 2, gameView.getIconO().getIconHeight() * 2, Image.SCALE_DEFAULT));
            case DRAW -> new ImageIcon(
                    gameView.getIconDraw().getImage().getScaledInstance(
                    gameView.getIconDraw().getIconWidth() * 2, gameView.getIconDraw().getIconHeight() * 2, Image.SCALE_DEFAULT));
            default -> null;
        };
        button.setIcon(icon);
        button.setDisabledIcon(icon);

        panel.add(button);
        GameView.setJPanel(panel, id);
    }

    @Override
    public void onGameWinner(InfoGame.Winner winner) {
        gameView.disableAll();
    }
}
