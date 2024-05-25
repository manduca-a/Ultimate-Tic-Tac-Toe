package it.unical.informatica.studenti.View;

import it.unical.informatica.studenti.Controller.GameWinController;
import it.unical.informatica.studenti.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Objects;


public class GameWinView extends JPanel {

    final private Image winnerImage;
    private static JFrame frame;
    private static GameWinView gameWinView;


    public GameWinView(JFrame frame, Settings.Img winner) throws IOException {
        GameWinView.frame = frame;
        this.winnerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(winner.getPath())));

        // Create the buttons
        JButton quitButton = new JButton("Quit");

        // Add action listeners to the buttons

        quitButton.addActionListener(e -> {
            System.exit(0); // Exit the application
        });

        // Create a panel for the buttons with FlowLayout centered
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(quitButton);

        // Set layout for GameWinView and add the button panel at the bottom
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the "Win" text
        String winText = "Win";
        Font winFont = new Font("Arial", Font.BOLD, 50);
        g.setFont(winFont);
        FontMetrics metrics = g.getFontMetrics(winFont);
        int xText = (getWidth() - metrics.stringWidth(winText)) / 2;
        int yText = metrics.getHeight();
        g.drawString(winText, xText, yText);

        // Draw the winner image scaled to 2/3 of the frame
        if (winnerImage != null) {
            int newWidth = getWidth() * 2 / 3;
            int newHeight = getHeight() * 2 / 3;
            Image scaledImage = winnerImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            int xImage = (getWidth() - newWidth) / 2;
            int yImage = (getHeight() - newHeight) / 2 + yText;

            g.drawImage(scaledImage, xImage, yImage, this);
        }

    }

    public static void launch(JFrame frame, JPanel oldView, Settings.Img imgPath) throws IOException {
        frame.remove(oldView);
        frame.setSize(Settings.WINDOWS_GAMEVIEW_SIZE, Settings.WINDOWS_GAMEVIEW_SIZE);
        GameFrame.frameSettings(frame);

        GameWinView view = new GameWinView(frame, imgPath);
        gameWinView = view;

        GameWinController controller = new GameWinController(frame, gameWinView);

        frame.add(view);
        frame.setVisible(true);
        view.setFocusable(true);
        view.requestFocus();
    }
}