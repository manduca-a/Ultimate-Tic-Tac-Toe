package it.unical.informatica.studenti.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameFrame {


    static void frameSettings(JFrame frame) {

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        int x = (screenDimension.width - frame.getWidth())/2;
        int y = (screenDimension.height - frame.getHeight())/2;
        frame.setLocation(x, y);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (GameView.getGameview() != null) {
                    GameView.getGameview().repaint();
                }
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
