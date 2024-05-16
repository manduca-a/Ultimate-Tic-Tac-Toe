package it.unical.informatica.studenti.Controller;

import it.unical.informatica.studenti.View.GameView;
import it.unical.informatica.studenti.WorldGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GameStartController extends KeyAdapter implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    public GameStartController(JFrame frame, JPanel panel) {
        this.frame = frame;
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                GameView.launch(frame, panel);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JButton o = (JButton)e.getSource();
            if(o.getText().equals("IA VS IA")){
                //WorldGame.getInstance().setPlayerVsIA(false);
                WorldGame.getInstance().avviaIAvsIA();
            }else if (o.getText().equals("Player VS IA")){
                //WorldGame.getInstance().setPlayerVsIA(true);
            }
            GameView.launch(frame, panel);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
