package it.unical.informatica.studenti;

import it.unical.informatica.studenti.Controller.WinnerListener;
import it.unical.informatica.studenti.Model.BigBoard;
import it.unical.informatica.studenti.Model.EmbaspManager;
import it.unical.informatica.studenti.View.GameView;

import java.util.ArrayList;
import java.util.Random;

public class WorldGame {

    private WinnerListener winnerListener;

    private static WorldGame instance = null;


    private Settings.GameMode CurrentGameMode;

    private boolean IACalling = false;

    public static WorldGame getInstance(){
        if(instance == null){
            instance = new WorldGame();
        }
        return instance;
    }

    private BigBoard bigBoard;

    public boolean isIACalling() {
        return IACalling;
    }

    public void setIACalling(boolean IACalling) {
        this.IACalling = IACalling;
    }

    public BigBoard getBigBoard() {
        return bigBoard;
    }

    public Settings.GameMode getCurrentGameMode() {
        return CurrentGameMode;
    }

    private WorldGame(){
        bigBoard =new BigBoard();
    }

    public void avviaIAvsIA(){
        CurrentGameMode = Settings.GameMode.IAVsIA;
        if(chiInizia()){

        }
    }

    public void avviaPlayervsIA(){
        CurrentGameMode = Settings.GameMode.PlayerVsIA;
        if(chiInizia()) {
            IACalling= true;
            ArrayList<Integer> coords = EmbaspManager.avviaASP(Settings.IAPlayingVsPLayer); //da cambiare in base a quale team deve giocare come IA
            GameView.getButton(coords.get(0),coords.get(1),coords.get(2)).doClick();
        }
    }

    public boolean chiInizia (){
        Random random = new Random();
        int starting = random.nextInt(0,2);
        System.out.println(starting);
        return starting == 0;
    }

    public void resetBoard(){
        bigBoard = new BigBoard();
    }

    public void addWinnerListener(WinnerListener listener){
        winnerListener = listener;
    }

    public WinnerListener getWinnerListener() {
        return winnerListener;
    }

}
