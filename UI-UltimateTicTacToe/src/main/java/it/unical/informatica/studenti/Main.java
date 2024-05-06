package it.unical.informatica.studenti;

import it.unical.informatica.studenti.Model.BigBoard;
import it.unical.informatica.studenti.View.MatrixFrame;

public class Main
{
    public static void main( String[] args )
    {
        BigBoard bigBoard =new BigBoard();
        bigBoard.PrintMatrix();

        java.awt.EventQueue.invokeLater(() -> {
            new MatrixFrame().setVisible(true);
        });
    }
}
