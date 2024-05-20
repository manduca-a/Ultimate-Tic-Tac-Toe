package it.unical.informatica.studenti.Model;

import it.unical.informatica.studenti.WorldGame;

import java.io.IOException;
import java.util.ArrayList;

import static it.unical.informatica.studenti.Model.InfoGame.Winner.*;

public class BigBoard {

    private final ArrayList<SmallBoard> smallBoards =new ArrayList<>();

    private InfoGame.Winner BigBoardWinner = InfoGame.Winner.NOWINNER;

    private final int[][] gameBoard = new int[3][3];

    private final int[][] winnerBoard = new int[3][3];

    private int nextBoard = -1;

    public ArrayList<SmallBoard> getSmallBoards() {
        return smallBoards;
    }

    public InfoGame.Winner getBigBoardWinner() {
        return BigBoardWinner;
    }

    public BigBoard(){
        for(int i =0; i<9; i++){
            smallBoards.add(new SmallBoard(this,i));
        }
        for(int i =0; i <3; i++){
            for (int j =0; j<3; j++){
                gameBoard[i][j]=0;
            }
        }
    }

    public void UpdateBigBoard(int value, int id) {
        if(gameBoard[id/3][id%3] == 0){
            gameBoard[id/3][id%3] = value;
//            PrintGameBoard();
            switch (InfoGame.checkWinner(gameBoard)){
                case CROSS -> {
                    BigBoardWinner = CROSS;
                    WorldGame.getInstance().getWinnerListener().onGameWinner(CROSS);
                }
                case CIRCLE -> {
                    BigBoardWinner = CIRCLE;
                    WorldGame.getInstance().getWinnerListener().onGameWinner(CIRCLE);
                }
                case DRAW -> {
                    BigBoardWinner = DRAW;
                    WorldGame.getInstance().getWinnerListener().onGameWinner(DRAW);
                }
            }
        }
        else
            throw new RuntimeException("Invalid board");
    }

    private void PrintGameBoard() {
        for(int i =0; i<3; i++){
            for(int j=0; j<3; j++){
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void PrintSmallBoard() {
        for( int k=0; k<9; k++){
            System.out.println("Matrix di:"+k);
            for(int i =0; i<3; i++){
                for(int j=0; j<3; j++){
                    System.out.print(smallBoards.get(k).getSubBoard()[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public boolean UpdateBoardStatus(int i, int j, int boardIndex, int value) {
        if((nextBoard == -1 || nextBoard == boardIndex) && smallBoards.get(boardIndex).getSubBoard()[i][j] == 0 ){
            smallBoards.get(boardIndex).SetCell(i, j, value);
            if(smallBoards.get(3*i+j).GetWinner() == InfoGame.Winner.NOWINNER)
                nextBoard = 3*i+j;
            else
                nextBoard = -1;
            return true;
        }
        else
            //throw new RuntimeException("Invalid Board Move");
            return false;

    }

    public int getNextBoard() {
        return nextBoard;
    }

}
