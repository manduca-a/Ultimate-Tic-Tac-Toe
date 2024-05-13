package it.unical.informatica.studenti.Controller;

import it.unical.informatica.studenti.Model.BigBoard;
import it.unical.informatica.studenti.View.GameStartView;
import it.unical.informatica.studenti.View.GameView;
import it.unical.informatica.studenti.WorldGame;

import javax.swing.*;
import java.awt.event.*;

public class GameController extends KeyAdapter implements ActionListener {

    private final JFrame jFrame;
    private final GameView gameView;
    private final WorldGame worldGame = WorldGame.getInstance();
    private int value = -1; // X = 1, O = -1

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

        worldGame.getBigBoard().UpdateBoardStatus(rowSmallBoard, colSmallBoard, indBigBoard, value *= -1);

        //! Runtime exception occurs when the player tries to insert into the wrong board

        //set icon
        if (value == 1) gameView.setIconX(o); else if (value == -1) gameView.setIconO(o);
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
