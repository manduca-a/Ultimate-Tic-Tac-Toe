package it.unical.informatica.studenti;

import it.unical.informatica.studenti.Controller.WinnerListener;
import it.unical.informatica.studenti.Model.BigBoard;
import it.unical.informatica.studenti.Model.EmbaspManager;
import it.unical.informatica.studenti.View.GameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class WorldGame {

    private WinnerListener winnerListener;

    private static WorldGame instance = null;

    private int UserToPlay = 1; // X = 1, O = -1

    private Settings.GameMode CurrentGameMode;

    private boolean IACalling = false;

    private boolean[] IAStartingPlaying = new boolean[2];

    public static WorldGame getInstance(){
        if(instance == null){
            instance = new WorldGame();
        }
        return instance;
    }

    private BigBoard bigBoard;

    public int getUserToPlay() {
        return UserToPlay;
    }

    public void AlternateUserToPlay() {
        UserToPlay *= -1;
    }

    public boolean[] getIAStartingPlaying() {
        return IAStartingPlaying;
    }

    public void SwapIAPlaying() {
        IAStartingPlaying[0] = !IAStartingPlaying[0];
        IAStartingPlaying[1] = !IAStartingPlaying[1];
    }

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
        bigBoard = new BigBoard();
    }

    public void IAvsIA(){
        CurrentGameMode = Settings.GameMode.IAVsIA;
    }
    public void PlayervsIA(){
        CurrentGameMode = Settings.GameMode.PlayerVsIA;
    }
    public void avviaIAvsIA(){
        if(chiInizia()){
            System.out.println("Sta iniziando: "+Settings.TeamsPlaying[0]);
            IAStartingPlaying[0] = true;
            IAStartingPlaying[1] = false;
            ArrayList<Integer> coords = EmbaspManager.avviaASP(Settings.TeamsPlaying[0]);
            assert coords != null;
            GameView.getButton(coords.get(0),coords.get(1),coords.get(2)).doClick();
        }
        else{
            System.out.println("Sta iniziando: "+Settings.TeamsPlaying[1]);
            IAStartingPlaying[0] = false;
            IAStartingPlaying[1] = true;
            ArrayList<Integer> coords = EmbaspManager.avviaASP(Settings.TeamsPlaying[1]);
            assert coords != null;
            GameView.getButton(coords.get(0),coords.get(1),coords.get(2)).doClick();
        }
    }

    public void avviaPlayervsIA(){
        if(chiInizia()) {
            System.out.println("\n\tIA");
            IACalling= true;
            ArrayList<Integer> coords = EmbaspManager.avviaASP(Settings.IAPlayingVsPLayer); //da cambiare in base a quale team deve giocare come IA
            assert coords != null;
            GameView.getButton(coords.get(0),coords.get(1),coords.get(2)).doClick();
        }
        else
            System.out.println("\n\n\tPlayer!");
    }

    private boolean chiInizia(){
        Random random = new Random();
        int starting = random.nextInt(0,2);
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
