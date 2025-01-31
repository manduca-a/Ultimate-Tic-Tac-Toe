package it.unical.informatica.studenti.View;

import it.unical.informatica.studenti.Controller.GameController;
import it.unical.informatica.studenti.Settings;
import it.unical.informatica.studenti.WorldGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class GameView extends JPanel {

    private static GameView gameView;

    private final ImageIcon iconX, iconO, iconDraw;

    private static LinkedList<JPanel> jPanels;
    private JLabel teamXLabel;

    public void setTeamsLabelColors(boolean isXPlaing) {
        if (isXPlaing) {
            teamXLabel.setBackground(Color.GREEN);
            teamOLabel.setBackground(Color.LIGHT_GRAY);
        } else {
            teamXLabel.setBackground(Color.LIGHT_GRAY);
            teamOLabel.setBackground(Color.GREEN);
        }
    }

    private JLabel teamOLabel;
    private JPanel topPanel, gamePanel;

    public GameView(JFrame frame) throws IOException {
        setBackground(Color.DARK_GRAY);

        Image imgX = ImageIO.read(Objects.requireNonNull(GameView.class.getResource(Settings.Img.X.getPath())));
        Image imgXScaled = imgX.getScaledInstance((((frame.getWidth()) / 3) / 3 - 10), (((frame.getWidth()) / 3) / 3 - 10), java.awt.Image.SCALE_SMOOTH);

        Image imgO = ImageIO.read(Objects.requireNonNull(GameView.class.getResource(Settings.Img.O.getPath())));
        Image imgOScaled = imgO.getScaledInstance((((frame.getWidth()) / 3) / 3 - 10), (((frame.getWidth()) / 3) / 3 - 10), java.awt.Image.SCALE_SMOOTH);

        Image imgDraw = ImageIO.read(Objects.requireNonNull(GameView.class.getResource(Settings.Img.Draw.getPath())));
        Image imgDrawScaled = imgDraw.getScaledInstance((((frame.getWidth()) / 3) / 3 - 10), (((frame.getWidth()) / 3) / 3 - 10), java.awt.Image.SCALE_SMOOTH);

        iconX = new ImageIcon(imgXScaled);
        iconO = new ImageIcon(imgOScaled);
        iconDraw = new ImageIcon(imgDrawScaled);
    }

    public void initializeTeamLabels() {

        String player1 = "";
        String player2 = "";

        String space = "  ";

        switch (WorldGame.getInstance().getCurrentGameMode()) {
            case IAVsIA -> {
                if (WorldGame.getInstance().getIAStartingPlaying()[0]) {
                    player1 = Settings.TeamsPlaying[0].toString();
                    player2 = Settings.TeamsPlaying[1].toString();
                } else {
                    player1 = Settings.TeamsPlaying[1].toString();
                    player2 = Settings.TeamsPlaying[0].toString();
                }
            }
            case PlayerVsIA -> {
                if (WorldGame.getInstance().isIACalling()) {
//                    System.out.println("isAICalling=True");
                    player1 = Settings.IAPlayingVsPLayer.toString();
                    player2 = "Player";
                } else {
//                    System.out.println("isAICalling=False");
                    player1 = "Player";
                    player2 = Settings.IAPlayingVsPLayer.toString();
                }
            }
        }


        teamXLabel = new JLabel(space + player1, iconX, JLabel.CENTER);
        teamOLabel = new JLabel(space + player2, iconO, JLabel.CENTER);

        teamXLabel.setOpaque(true);
        teamOLabel.setOpaque(true);

        teamXLabel.setFont(new Font("Arial", Font.BOLD, 25));
        teamOLabel.setFont(new Font("Arial", Font.BOLD, 25));

        setTeamsLabelColors(WorldGame.getInstance().getUserToPlay()==1);

        setLayout(new BorderLayout());
        topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.setPreferredSize(new Dimension(getWidth(), 100));
        topPanel.add(teamXLabel);
        topPanel.add(teamOLabel);
        add(topPanel, BorderLayout.NORTH);
    }

    public static void launch(JFrame frame, JPanel oldView) throws IOException {
        frame.remove(oldView);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ottieni le nuove dimensioni del frame
                int newWidth = frame.getWidth();
                int newHeight = frame.getHeight();

                // Assicurati che larghezza e altezza siano sempre uguali
                int newSize = Math.min(newWidth, newHeight);

                // Imposta le dimensioni del frame in modo che siano sempre uguali
                frame.setSize(newSize, newSize+100);
            }
        });

        frame.setSize(Settings.WINDOWS_GAMEVIEW_SIZE, Settings.WINDOWS_GAMEVIEW_SIZE+100);

        switch (WorldGame.getInstance().getCurrentGameMode()){
            case IAVsIA -> frame.setTitle(Settings.APP_NAME + " | " + Settings.TeamsPlaying[0] + " VS " + Settings.TeamsPlaying[1]);
            case PlayerVsIA -> frame.setTitle(Settings.APP_NAME + " | " + Settings.IAPlayingVsPLayer);
        }

        GameView view = new GameView(frame);
        gameView = view;
        view.initializeTeamLabels();

        GameController controller = new GameController(frame, view);
        view.addKeyListener(controller);
        frame.add(view);

        JRootPane rootPane = frame.getRootPane();
        rootPane.putClientProperty("apple.awt.fullWindowContent", true);
        rootPane.putClientProperty("apple.awt.transparentTitleBar", true);

        frame.setLayout(new BorderLayout());
        frame.add(view, BorderLayout.CENTER);

        int topMargin = 25;
        view.setBorder(BorderFactory.createEmptyBorder(topMargin, 0, 0, 0));

        GameFrame.frameSettings(frame);

        view.initializeGamePanel(controller);

        frame.setVisible(true);
        view.setFocusable(true);
        view.requestFocus();
    }

    private void initializeGamePanel(GameController controller) {
        GridLayout bigGrid = new GridLayout(3, 3);
        gamePanel = new JPanel(bigGrid);
        jPanels = new LinkedList<>();


        try {
            for (int i = 0; i < 9; i++) {
                GridLayout miniGrid = new GridLayout(3, 3);
                JPanel p = new JPanel();
                p.setName(String.valueOf(i));
                p.setLayout(miniGrid);
                p.setBorder(BorderFactory.createLineBorder(Settings.State.CURRENT_PLAYING.getColor(), 5));
                jPanels.add(p);

                for (int j = 0; j < 9; j++) {
                    JButton button = new JButton();
                    button.setName(i + " " + j);
                    button.addActionListener(controller);
                    button.setBorder(BorderFactory.createLineBorder(Settings.State.LITTLE_LINES_COLOR.getColor(), 3)); // Set border color to red and thickness to 5
                    button.setBackground(Settings.State.BUTTON_BACKGROUND.getColor());
                    p.add(button);
                }
                gamePanel.add(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        add(gamePanel, BorderLayout.CENTER);
    }

    public static GameView getGameview() {
        return gameView;
    }

    public void setIconX(JButton o) {
        o.setIcon(iconX);
        o.setDisabledIcon(iconX);
    }

    public void setIconO(JButton o) {
        o.setIcon(iconO);
        o.setDisabledIcon(iconO);
    }

    public static LinkedList<JPanel> getjPanels() {
        return jPanels;
    }

    public static JButton getButton(int i, int j, int id) {
        return (JButton) jPanels.get(id).getComponent(i * 3 + j);
    }

    public void setJPanel(JPanel jPanel, int index) {
        GameView.jPanels.set(index, jPanel);
        gamePanel.removeAll();
        for (JPanel p : jPanels) {
            gamePanel.add(p);
        }
    }

    public ImageIcon getIconX() {
        return iconX;
    }

    public ImageIcon getIconO() {
        return iconO;
    }

    public ImageIcon getIconDraw() {
        return iconDraw;
    }

    public void disableAll() {
        for (JPanel p : jPanels) {
            for (Component c : p.getComponents()) {
                c.setEnabled(false);
            }
        }
    }

    public void updateTurnDisplay(boolean isXTurn) {
        if (isXTurn) {
            teamXLabel.setBackground(Color.LIGHT_GRAY);
            teamOLabel.setBackground(Color.DARK_GRAY);
        } else {
            teamXLabel.setBackground(Color.DARK_GRAY);
            teamOLabel.setBackground(Color.LIGHT_GRAY);
        }
    }
}
