package it.unical.informatica.studenti.View;

import javax.swing.JPanel;
import java.awt.*;

public class MatrixPanel extends JPanel {
    private static final int ROWS = 9;
    private static final int COLS = 9;
    private static final int CELL_SIZE = 20;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int x = j * CELL_SIZE;
                int y = i * CELL_SIZE;
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COLS * CELL_SIZE, ROWS * CELL_SIZE);
    }
}