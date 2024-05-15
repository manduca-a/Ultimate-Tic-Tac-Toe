package it.unical.informatica.studenti.View;

import it.unical.informatica.studenti.Controller.GameController;
import it.unical.informatica.studenti.Controller.GameStartController;
import it.unical.informatica.studenti.Settings;


import javax.swing.*;
import java.awt.*;

public class GameStartView {

    public GameStartView() {
    }

    public static void launch(JFrame frame, JPanel oldView) {
        frame.remove(oldView);
        frame.setSize(Settings.WINDOWS_GAMESTART_WIDTH, Settings.WINDOWS_GAMESTART_HEIGHT);

        JPanel start = new JPanel();


        GameStartController gameStartController = new GameStartController(frame, start);

        start.addKeyListener(gameStartController);
        start.setFocusable(true);


        JRootPane rootPane = frame.getRootPane();
        rootPane.putClientProperty("apple.awt.fullWindowContent", false);
        rootPane.putClientProperty("apple.awt.transparentTitleBar", false);

        frame.add(start);
        GameFrame.frameSettings(frame);
        frame.setVisible(true);
        start.requestFocus();
    }

    public static void launch() {
        JFrame frame = new JFrame();
        frame.setTitle(Settings.APP_NAME);
        frame.setSize(Settings.WINDOWS_GAMESTART_WIDTH, Settings.WINDOWS_GAMESTART_HEIGHT);

        JPanel start = new JPanel();

        JButton bStart = new JButton("Start");

        GameStartController gameStartController = new GameStartController(frame, start);

        start.addKeyListener(gameStartController);      // start game with Enter click
        start.setFocusable(true);
        start.requestFocus();

        bStart.addActionListener(gameStartController);

        bStart.setPreferredSize(Settings.BUTTON_DIMENSION);
        bStart.setSize(Settings.BUTTON_DIMENSION);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        int x = (screenDimension.width - bStart.getWidth()) / 2;
        int y = (screenDimension.height - bStart.getHeight()) / 2;
        start.add(bStart);
        bStart.setLocation(x, y);


        JRootPane rootPane = frame.getRootPane();
        rootPane.putClientProperty("apple.awt.fullWindowContent", false);
        rootPane.putClientProperty("apple.awt.transparentTitleBar", false);

        frame.add(start);
        GameFrame.frameSettings(frame);
        frame.setVisible(true);
    }
}
