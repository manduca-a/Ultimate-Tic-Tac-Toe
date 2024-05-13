package it.unical.informatica.studenti.View;

import it.unical.informatica.studenti.Controller.GameController;
import it.unical.informatica.studenti.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.Objects;

public class GameView extends JPanel {

    private JFrame frame;
    private static GameView gameView;

    private ImageIcon iconX, iconO;

    public GameView(JFrame frame) throws IOException {
        setBackground(Color.DARK_GRAY);
        this.frame = frame;

        Image imgX = ImageIO.read(Objects.requireNonNull(GameView.class.getResource(Settings.Img.X.getPath())));
        Image imgXScaled = imgX.getScaledInstance((((frame.getWidth()) / 3) / 3 - 10), (((frame.getWidth()) / 3) / 3 - 10), java.awt.Image.SCALE_SMOOTH);

        Image imgO = ImageIO.read(Objects.requireNonNull(GameView.class.getResource(Settings.Img.O.getPath())));
        Image imgOScaled = imgO.getScaledInstance((((frame.getWidth()) / 3) / 3 - 10), (((frame.getWidth()) / 3) / 3 - 10), java.awt.Image.SCALE_SMOOTH);

        iconX = new ImageIcon(imgXScaled);
        iconO = new ImageIcon(imgOScaled);
    }

    public static void launch(JFrame frame, JPanel oldView) throws IOException {
        frame.remove(oldView);
        frame.setSize(Settings.WINDOWS_GAMEVIEW_SIZE, Settings.WINDOWS_GAMEVIEW_SIZE);

        GameView view = new GameView(frame);
        gameView = view;

        GameController controller = new GameController(frame, view);

        view.addKeyListener(controller);

        frame.add(view);

        GameFrame.frameSettings(frame);

        JRootPane rootPane = frame.getRootPane();
        rootPane.putClientProperty("apple.awt.fullWindowContent", true);
        rootPane.putClientProperty("apple.awt.transparentTitleBar", true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        try {

            for (int i = 0; i < 9; i++) {

                GridLayout miniGrid = new GridLayout(3, 3);
                JPanel p = new JPanel();
                p.setLayout(miniGrid);
                p.setBorder(BorderFactory.createLineBorder(Settings.State.BIG_LINES_COLOR.getColor(), 5));

                for (int j = 0; j < 9; j++) {
                    JButton button = new JButton();
                    button.setName(String.valueOf(i) + " " + String.valueOf(j));
                    button.addActionListener(controller);
                    ImageIcon icon;
/*
                    if (j % 2 == 0) {
                        button.setIcon(iconX);
                    } else {
                        button.setIcon(iconO);
                    }
*/

                    button.setBorder(BorderFactory.createLineBorder(Settings.State.LITTLE_LINES_COLOR.getColor(), 3)); // Set border color to red and thickness to 5
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
    }
    public void setIconO(JButton o) {
        o.setIcon(iconO);
    }
/*
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //todo singleton of World to take the matrix -> World.getMatrix();
        //todo take from World the isGameEnded()



        int start = 1; int end = 3;

        int worldSpace = frame.getWidth();

        if(frame.getWidth() >= frame.getHeight()) {
            worldSpace = frame.getHeight();
        }



        int startBigXLine = worldSpace/3;
        int startBigYLine = 50;

        int wBigLine = 10;
        int hBigLine = worldSpace - startBigYLine*2;

        int wMiniLine = 2;
        int hMiniLine = ((worldSpace-100) / 3);

        int arcRound = 8;


        g.setColor(Settings.State.BIG_LINES_COLOR.getColor());

        for(int i = start; i < end; i++) {
            //verticale big
            g.fillRoundRect(startBigXLine*i + wBigLine*(i-1), startBigYLine, wBigLine, hBigLine, arcRound, arcRound);

            //orizzontale big
            g.fillRoundRect(startBigYLine, startBigXLine*i + wBigLine*(i-1), hBigLine, wBigLine, arcRound, arcRound);

            System.out.println("grandezza world: " + worldSpace);
            System.out.println("fillRoundRect: " + (int)(startBigXLine*i + wBigLine*(i-1)));
            System.out.println("di cui startBixXLine*i: " + startBigXLine*i);
            System.out.println("di cui wBigline*i: " + wBigLine*(i-1));
            System.out.println("-------------------------------------");
            //for(int j = start; j < end; j++)
                //verticale mini


                //orizzontale mini

            //}
        }


    }

*/
}
