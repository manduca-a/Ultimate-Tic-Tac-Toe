package it.unical.informatica.studenti;

import java.awt.*;

public class Settings {

    public static final String APP_NAME = "Ultimate Tic Tac Toe";

    public static final int WINDOWS_GAMEVIEW_SIZE = 800;

    public static final int BORDER_RADIUS = 30;


    public static final int WINDOWS_GAMESTART_WIDTH = 800;
    public static final int WINDOWS_GAMESTART_HEIGHT = 600;

    public static final Dimension BUTTON_DIMENSION = new Dimension(200, 50);

    public static Teams[] TeamsPlaying = {Teams.CHATCM,Teams.QueryQueens};

    public static Teams IAPlayingVsPLayer = Teams.QueryQueens;

    public enum  GameMode {PlayerVsIA,IAVsIA}

    public enum State {
        X(Color.BLUE),
        O(Color.RED),
        CURRENT_PLAYING(Color.GREEN),
        LITTLE_LINES_COLOR(new Color(192,192,192)),
        BIG_LINES_COLOR(Color.DARK_GRAY),
        BUTTON_BACKGROUND(new Color(238,238,238));



        private final Color color;
        State(Color c) { this.color = c; }
        public Color getColor() { return color; }
    }




    public enum Img {
        X(  "X.png"),
        O("O.png"),
        Draw("-.png"),
        Home("home.png");


        private final String path;
        Img(String path){
            this.path = path;
        }

        public String getPath() {
            String basepath = "/img/";
            System.out.println(basepath + path);
            return basepath + path;
        }


    }

}
