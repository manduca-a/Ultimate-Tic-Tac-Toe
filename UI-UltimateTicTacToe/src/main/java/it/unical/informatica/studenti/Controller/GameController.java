package it.unical.informatica.studenti.Controller;

import it.unical.informatica.studenti.Model.BigBoard;
import it.unical.informatica.studenti.View.GameStartView;
import it.unical.informatica.studenti.View.GameView;
import it.unical.informatica.studenti.WorldGame;

import javax.swing.*;
import java.awt.event.*;

public class GameController extends KeyAdapter implements ActionListener {

    private JFrame jFrame;
    private GameView gameView;
    private WorldGame worldGame = WorldGame.getInstance();

    public GameController(JFrame frame, GameView view) {
        this.jFrame = frame;
        this.gameView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //put a X or a O if we can choose

        JButton o = (JButton)e.getSource();
        System.out.println("metto x in " + o.getText());
        String[] indexes = o.getName().split("\\s+");
        int indBigBoard = Integer.parseInt(indexes[0]);
        int indSmallBoard = Integer.parseInt(indexes[1]);
        int rowSmallBoard = indSmallBoard / 3;
        int colSmallBoard = indSmallBoard % 3;

        // todo - al momento inserisce sempre X
        worldGame.getBigBoard().UpdateBoardStatus(rowSmallBoard, colSmallBoard, indBigBoard, 1);

        //set icon
        gameView.setIcon(o);
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
