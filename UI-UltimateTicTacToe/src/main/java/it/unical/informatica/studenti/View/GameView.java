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

    private JFrame frame;
    private static GameView gameView;

    private ImageIcon iconX, iconO, iconDraw;

    private GridLayout bigGrid;
    private static LinkedList<JPanel> jPanels;

    public GameView(JFrame frame) throws IOException {
        setBackground(Color.DARK_GRAY);
        this.frame = frame;

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

    public static void launch(JFrame frame, JPanel oldView) throws IOException {
        frame.remove(oldView);
        frame.setSize(Settings.WINDOWS_GAMEVIEW_SIZE, Settings.WINDOWS_GAMEVIEW_SIZE);

        GameView view = new GameView(frame);
        gameView = view;

        GameController controller = new GameController(frame, view);

        frame.add(view);

        GameFrame.frameSettings(frame);

        JRootPane rootPane = frame.getRootPane();
        rootPane.putClientProperty("apple.awt.fullWindowContent", true);
        rootPane.putClientProperty("apple.awt.transparentTitleBar", true);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Ottieni le nuove dimensioni del frame
                int newWidth = frame.getWidth();
                int newHeight = frame.getHeight();

                // Assicurati che larghezza e altezza siano sempre uguali
                int newSize = Math.min(newWidth, newHeight);

                // Imposta le dimensioni del frame in modo che siano sempre uguali
                frame.setSize(newSize, newSize);
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(view, BorderLayout.CENTER);

        int topMargin = 25;
        view.setBorder(BorderFactory.createEmptyBorder(topMargin, 0, 0, 0));

        GameFrame.frameSettings(frame);

        GridLayout bigGrid = new GridLayout(3, 3);
        gameView.setLayout(bigGrid);


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
                    button.setName(String.valueOf(i) + " " + String.valueOf(j));
                    button.addActionListener(controller);
                    ImageIcon icon;

                    button.setBorder(BorderFactory.createLineBorder(Settings.State.LITTLE_LINES_COLOR.getColor(), 3)); // Set border color to red and thickness to 5
                    button.setBackground(Settings.State.BUTTON_BACKGROUND.getColor());
                    p.add(button);
                }
                gameView.add(p);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        frame.setVisible(true);
        view.setFocusable(true);
        view.requestFocus();
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

    public static JButton getButton(int i, int j, int id){
        return (JButton) jPanels.get(id).getComponent(i*3+j);
    }

    public static void setJPanel(JPanel jPanel, int index) {
        GameView.jPanels.set(index, jPanel);
        gameView.removeAll();
        for(JPanel p : jPanels) {
            gameView.add(p);
        }
    }

    public ImageIcon getIconX(){
        return iconX;
    }

    public ImageIcon getIconO(){
        return iconO;
    }

    public ImageIcon getIconDraw(){
        return iconDraw;
    }

    public void disableAll() {
        for(JPanel p : jPanels){
            for(Component c : p.getComponents()){
                c.setEnabled(false);
            }
        }
    }
}

