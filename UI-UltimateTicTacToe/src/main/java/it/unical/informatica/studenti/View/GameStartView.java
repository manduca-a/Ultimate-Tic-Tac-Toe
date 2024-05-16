package it.unical.informatica.studenti.View;

import it.unical.informatica.studenti.Controller.GameStartController;
import it.unical.informatica.studenti.Settings;
import it.unical.informatica.studenti.WorldGame;


import javax.swing.*;
import java.awt.*;

public class GameStartView {

    public GameStartView() {
    }

    public static void launch(JFrame frame, JPanel oldView) {
        frame.remove(oldView);
        JPanel start = new JPanel();
        start.setFocusable(true);
        frame.add(start);
        start.requestFocus();
    }

    public static void launch() {
        JFrame frame = WorldGame.getInstance().getFrame();
        frame.setTitle(Settings.APP_NAME);
        frame.setSize(Settings.WINDOWS_GAMESTART_WIDTH, Settings.WINDOWS_GAMESTART_HEIGHT);

        JPanel start = new JPanel();

        JButton bIAvsIA = new JButton("IA VS IA");
        JButton bPvsIA = new JButton("Player VS IA");


        GameStartController gameStartController = new GameStartController(frame, start);

        start.addKeyListener(gameStartController);      // start game with Enter click
        start.setFocusable(true);
        start.requestFocus();

        bIAvsIA.addActionListener(gameStartController);

        bIAvsIA.setPreferredSize(Settings.BUTTON_DIMENSION);
        bIAvsIA.setSize(Settings.BUTTON_DIMENSION);

        bPvsIA.addActionListener(gameStartController);

        bPvsIA.setPreferredSize(Settings.BUTTON_DIMENSION);
        bPvsIA.setSize(Settings.BUTTON_DIMENSION);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        int x = (screenDimension.width - bIAvsIA.getWidth()) / 2;
        int y = (screenDimension.height - bIAvsIA.getHeight()) / 2;
        start.add(bIAvsIA);
        bIAvsIA.setLocation(x, y);
        start.add(bPvsIA);
        bPvsIA.setLocation(x*2, y*2);


        JRootPane rootPane = frame.getRootPane();
        rootPane.putClientProperty("apple.awt.fullWindowContent", false);
        rootPane.putClientProperty("apple.awt.transparentTitleBar", false);

        frame.add(start);
        GameFrame.frameSettings(frame);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
