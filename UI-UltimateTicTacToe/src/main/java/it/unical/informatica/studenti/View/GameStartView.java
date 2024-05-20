package it.unical.informatica.studenti.View;

import it.unical.informatica.studenti.Controller.GameStartController;
import it.unical.informatica.studenti.Settings;
import it.unical.informatica.studenti.Teams;
import it.unical.informatica.studenti.WorldGame;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameStartView {

    private static JFrame frame;

    public GameStartView() {
    }

    public static void launch(JFrame frame, JPanel oldPanel) {

        if (oldPanel != null) {
            frame.remove(oldPanel);
            System.out.println("oldPanel removed!");
        }

        JPanel startPanel = new JPanel();

        JButton bIAvsIA = new JButton("IA VS IA");
        JButton bPvsIA = new JButton("Player VS IA");

        //JLabel TeamsPlaying = new JLabel(Settings.TeamsPlaying[0] +" VS "+Settings.TeamsPlaying[1]);

        GameStartController gameStartController = new GameStartController(frame, startPanel);

        startPanel.setFocusable(true);
        startPanel.requestFocus();

        bIAvsIA.addActionListener(gameStartController);

        bIAvsIA.setPreferredSize(Settings.BUTTON_DIMENSION);
        bIAvsIA.setSize(Settings.BUTTON_DIMENSION);

        //TeamsPlaying.setSize(Settings.BUTTON_DIMENSION);

        bPvsIA.addActionListener(gameStartController);

        bPvsIA.setPreferredSize(Settings.BUTTON_DIMENSION);
        bPvsIA.setSize(Settings.BUTTON_DIMENSION);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenDimension = toolkit.getScreenSize();
        //int x = (screenDimension.width - bIAvsIA.getWidth()) / 2;
        //int y = (screenDimension.height - bIAvsIA.getHeight()) / 2;

        startPanel.setLayout(new FlowLayout(FlowLayout.CENTER,screenDimension.width / 2,10));

        //startPanel.add(TeamsPlaying);
        startPanel.add(bIAvsIA);
        startPanel.add(bPvsIA);

        frame.add(startPanel);
        frame.setVisible(true);
        startPanel.setFocusable(true);
        startPanel.requestFocus();
    }

    public static void launch() {
        frame = new JFrame();
        frame.setTitle(Settings.APP_NAME);
        frame.setSize(Settings.WINDOWS_GAMESTART_WIDTH, Settings.WINDOWS_GAMESTART_HEIGHT);

        JRootPane rootPane = frame.getRootPane();
        rootPane.putClientProperty("apple.awt.fullWindowContent", false);
        rootPane.putClientProperty("apple.awt.transparentTitleBar", false);

        launch(frame, null);

        GameFrame.frameSettings(frame);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
