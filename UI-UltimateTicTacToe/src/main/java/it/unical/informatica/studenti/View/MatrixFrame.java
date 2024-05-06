package it.unical.informatica.studenti.View;

import javax.swing.JFrame;

public class MatrixFrame extends JFrame {
    public MatrixFrame() {
        setTitle("Matrice Grafica");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new MatrixPanel());
    }

}
