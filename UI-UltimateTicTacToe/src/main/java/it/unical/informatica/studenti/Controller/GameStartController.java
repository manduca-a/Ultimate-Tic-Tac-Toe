package it.unical.informatica.studenti.Controller;

import it.unical.informatica.studenti.View.GameView;
import it.unical.informatica.studenti.WorldGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameStartController implements ActionListener {

    private JFrame frame;
    private JPanel panel;

    public GameStartController(JFrame frame, JPanel panel) {
        this.frame = frame;
        this.panel = panel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            GameView.launch(frame, panel);

            JButton o = (JButton)e.getSource();
            if(o.getText().equals("IA VS IA")){
                WorldGame.getInstance().avviaIAvsIA();
            }else if (o.getText().equals("Player VS IA")){
                WorldGame.getInstance().avviaPlayervsIA();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
