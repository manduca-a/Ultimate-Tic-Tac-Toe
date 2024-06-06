package it.unical.informatica.studenti.View;

import it.unical.informatica.studenti.Controller.GameStartController;
import it.unical.informatica.studenti.Settings;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class GameStartView {

    public GameStartView() {
    }

    public static void launch(JFrame frame, JPanel oldPanel) throws IOException, FontFormatException {

        if (oldPanel != null) {
            frame.remove(oldPanel);
//            System.out.println("oldPanel removed!");
        }

        frame.setLayout(new BorderLayout());

        JPanel startPanel = new JPanel(){
            // Sovrascrive il metodo paintComponent per disegnare l'immagine di sfondo
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carica e disegna l'immagine di sfondo
                ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(GameStartView.class.getResource(Settings.Img.Home.getPath())));
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        Font font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(GameStartView.class.getResourceAsStream("/fonts/Strian-regular.ttf")));

        JButton bIAvsIA = new JButton("IA VS IA");
        JButton bPvsIA = new JButton("Player VS IA");

        bIAvsIA.setFont(font.deriveFont(Font.BOLD, 14));
        bPvsIA.setFont(font.deriveFont(Font.BOLD, 14));

        JLabel TeamsPlaying = new JLabel(Settings.TeamsPlaying[0] +" VS "+Settings.TeamsPlaying[1]);
        JLabel TeamPlaying = new JLabel(String.valueOf(Settings.IAPlayingVsPLayer));

        TeamsPlaying.setFont(font.deriveFont(Font.BOLD, 13));
        TeamPlaying.setFont(font.deriveFont(Font.BOLD, 13));

        GameStartController gameStartController = new GameStartController(frame, startPanel);

        startPanel.setFocusable(true);
        startPanel.requestFocus();

        bIAvsIA.addActionListener(gameStartController);

        bIAvsIA.setPreferredSize(Settings.BUTTON_DIMENSION);
        bIAvsIA.setSize(Settings.BUTTON_DIMENSION);

        bPvsIA.addActionListener(gameStartController);

        bPvsIA.setPreferredSize(Settings.BUTTON_DIMENSION);
        bPvsIA.setSize(Settings.BUTTON_DIMENSION);

        bIAvsIA.setBorder(new RoundedBorder(Settings.BORDER_RADIUS));
        bPvsIA.setBorder(new RoundedBorder(Settings.BORDER_RADIUS));
        bIAvsIA.setBackground(new Color(250, 250, 250));
        bPvsIA.setBackground(new Color(250, 250, 250));
        bIAvsIA.setFocusPainted(false);
        bPvsIA.setFocusPainted(false);


        startPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margini tra i componenti

        gbc.gridx = 0;
        gbc.gridy = 0;
        startPanel.add(bIAvsIA, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        startPanel.add(bPvsIA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        startPanel.add(TeamsPlaying, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        startPanel.add(TeamPlaying, gbc);

        frame.add(startPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        startPanel.setFocusable(true);
        startPanel.requestFocus();
    }

    public static void launch() throws IOException, FontFormatException {
        JFrame frame = new JFrame();
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
