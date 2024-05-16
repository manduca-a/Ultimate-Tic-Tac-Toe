package it.unical.informatica.studenti;

import it.unical.informatica.studenti.Controller.GameController;
import it.unical.informatica.studenti.Controller.WinnerListener;
import it.unical.informatica.studenti.Model.BigBoard;
import it.unical.informatica.studenti.Model.EmbaspManager;
import it.unical.informatica.studenti.Model.InfoGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WorldGame {

    private WinnerListener winnerListener;

    private static WorldGame instance = null;

    private boolean status;

    private JFrame frame;

    public static WorldGame getInstance(){
        if(instance == null){
            instance = new WorldGame();
        }
        return instance;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private BigBoard bigBoard;

    public BigBoard getBigBoard() {
        return bigBoard;
    }

    private WorldGame(){
        bigBoard =new BigBoard();
    }

    public void avviaIAvsIA(){
        boolean ia = chiInizia();// true è la prima, false è la seconda
        int i = 0;
        while(bigBoard.getBigBoardWinner() == InfoGame.Winner.NOWINNER){
            if(ia){
                //ArrayList<Integer> coordinate = EmbaspManager.avviaASP(Teams.CHATCM); //da cambiare il team
                /*
                Mario perchè l'hai fatto!!!
                (i == 0){
                    for(Component e : component){
                        System.out.println(e.getName());
                        JPanel panel = (JPanel) e.getComponentAt( board/3, board%3);
                        System.out.println("Nome pannello: " + panel.getName());
                        JButton btn = (JButton) panel.getComponents()[0].getComponentAt(0,0);
                        System.out.println("Nome bottone: " + btn.getName());
                    }
                    i++;
                }*/

            }else{
                //ArrayList<Integer> coordinate = EmbaspManager.avviaASP(Teams.GM); //da cambiare il team

            }
            ia = !ia;
        }
    }

    public void avviaPlayervsCPU(){
        Random random = new Random();
        int starting = random.nextInt(0,2);
        if( starting == 0)
            EmbaspManager.avviaASP(Teams.GM); //da cambiare in base a quale team deve giocare come IA

    }

    public boolean chiInizia (){
        Random random = new Random();
        int starting = random.nextInt(0,2);
        if( starting == 0)
            return true;
        return false;
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

    public void setFrame(JFrame frame){
        this.frame = frame;
    }

    public JFrame getFrame(){
        if(frame == null){
            frame = new JFrame();
        }
        return frame;
    }
}
