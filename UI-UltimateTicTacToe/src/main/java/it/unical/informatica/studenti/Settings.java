package it.unical.informatica.studenti;

import java.awt.*;

public class Settings {

    public static final String APP_NAME = "Ultimate Tic Tac Toe";


    public static final int WORLD_SIZE = 20;
    public static final int WINDOWS_GAMEVIEW_SIZE = 800;

    public static final int BORDER_OFFSET = (WINDOWS_GAMEVIEW_SIZE / WORLD_SIZE) - 10;
    public static final int CELL_SIZE = (WINDOWS_GAMEVIEW_SIZE - (BORDER_OFFSET*2)) / WORLD_SIZE;


    public static final int WINDOWS_GAMESTART_WIDTH = 800;
    public static final int WINDOWS_GAMESTART_HEIGHT = 600;

    public static final Dimension BUTTON_DIMENSION = new Dimension(200, 50);



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
        Draw("-.png");


        private final String basepath = "/img/";
        private final String path;
        Img(String path){
            this.path = path;
        }

        public String getPath() {
            System.out.println(basepath + path);
            return basepath + path;
        }


    }

}
