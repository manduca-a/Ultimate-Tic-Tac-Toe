package it.unical.informatica.studenti;

import it.unical.informatica.studenti.View.GameStartView;
import it.unical.informatica.studenti.View.GameView;

public class Main {
    public static void main( String[] args )
    {
        GameStartView.launch();
        //WorldGame.getInstance().avviaPlayervsCPU(); //da togliere quando si potrà selezionare dall UI
    }
}
