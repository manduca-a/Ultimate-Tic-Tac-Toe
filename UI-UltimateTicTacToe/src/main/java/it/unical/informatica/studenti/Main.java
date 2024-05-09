package it.unical.informatica.studenti;

import it.unical.informatica.studenti.View.GameView;
import it.unical.informatica.studenti.Model.WorldGame;

public class Main
{
    public static void main( String[] args )
    {
        GameView.launch();
        WorldGame.getInstance().avviaPlayervsCPU();
    }
}
