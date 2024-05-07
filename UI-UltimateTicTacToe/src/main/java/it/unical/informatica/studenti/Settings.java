package org.example;

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
        LITTLE_LINES_COLOR(Color.LIGHT_GRAY),
        BIG_LINES_COLOR(Color.DARK_GRAY);



        private final Color color;
        State(Color c) { this.color = c; }
        public Color getColor() { return color; }

    }

}
