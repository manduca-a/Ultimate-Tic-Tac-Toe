package it.unical.informatica.studenti.Controller;

import it.unical.informatica.studenti.Settings;
import it.unical.informatica.studenti.View.GameStartView;
import it.unical.informatica.studenti.View.GameView;
import it.unical.informatica.studenti.WorldGame;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class GameController extends KeyAdapter implements ActionListener {

    private final JFrame jFrame;
    private final GameView gameView;
    private final WorldGame worldGame = WorldGame.getInstance();
    private int value = 1; // X = 1, O = -1
    public GameController(JFrame frame, GameView view) {
        this.jFrame = frame;
        this.gameView = view;
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

            if (worldGame.getBigBoard().getNextBoard() == i || worldGame.getBigBoard().getNextBoard() == -1) {
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
}
