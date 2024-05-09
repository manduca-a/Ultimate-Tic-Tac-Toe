package it.unical.informatica.studenti.Model;

import java.util.Scanner;

public class WorldGame {

    private static WorldGame instance = null;

    public static WorldGame getInstance(){
        if(instance == null){
            instance = new WorldGame();
        }
        return instance;
    }

    private BigBoard bigBoard;

    public BigBoard getBigBoard() {
        return bigBoard;
    }

    private WorldGame(){
        bigBoard =new BigBoard();
    }

    public void avviaCPU1vsCPU2(){
        bigBoard.PrintGameBoard();
        /*
        java.awt.EventQueue.invokeLater(() -> {
            new MatrixFrame().setVisible(true);
        });

         */
        Scanner in = new Scanner(System.in);
        int i = 0;
        int board = -1, x = -1, y = -1;
        while(bigBoard.getBigBoardWinner() == InfoGame.Winner.NOWINNER){
            if( i % 2 == 0){
                //funzione che esegue ASP (Team1) e fa doClick sul button corrispettivo
            }
            else {
                //funzione che esegue ASP (Team2) e fa doClick sul button corrispettivo
            }
            /*System.out.println("Turno: " +i);
            System.out.println("Griglia:" + board);
            if (i == 0 || board == -1){
                System.out.println("Decidi la cella");
                board = in.nextInt();
            }
            System.out.println("X:");
            x = in.nextInt();
            System.out.println("Y:");
            y = in.nextInt();
            if(i%2 == 0){
                bigBoard.UpdateBoardStatus(x, y, board, 1);
            }else{
                bigBoard.UpdateBoardStatus(x, y, board, -1);
            }
            i+=1;
            bigBoard.PrintGameBoard();
            board = bigBoard.getNextBoard();*/
        }
    }

    public void avviaPlayervsCPU(){

    }

    public void resetBoard(){
        bigBoard = new BigBoard();
    }

}
