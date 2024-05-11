package it.unical.informatica.studenti.Controller;

import it.unical.informatica.studenti.View.GameStartView;
import it.unical.informatica.studenti.View.GameView;

import javax.swing.*;
import java.awt.event.*;

public class GameController extends KeyAdapter implements ActionListener {

    private JFrame jFrame;
    private GameView gameView;

    public GameController(JFrame frame, GameView view) {
        this.jFrame = frame;
        this.gameView = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //put a X or a O if we can choose

        JButton o = (JButton)e.getSource();
        String name = o.getName();

        System.out.println("metto X in " + name);

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
