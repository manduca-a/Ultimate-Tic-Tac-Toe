package it.unical.informatica.studenti.Controller;

import it.unical.informatica.studenti.View.GameView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameStartController extends KeyAdapter {

    private JFrame frame;
    private JPanel panel;

    public GameStartController(JFrame frame, JPanel panel) {
        this.frame = frame;
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            GameView.launch(frame, panel);
        }
    }
}
