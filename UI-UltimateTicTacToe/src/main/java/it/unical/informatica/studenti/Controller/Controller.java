package it.unical.informatica.studenti.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller extends MouseAdapter {

    private int PreviousMove = -1;

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }

    public void setPreviousMove(int previousMove) {
        PreviousMove = previousMove;
    }


}
